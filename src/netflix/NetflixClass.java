package netflix;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import account.Account;
import account.AccountClass;
import account.Profile;
import content.ComparatorByTitle;
import content.ComparatorByYear;
import content.Content;
import content.MovieClass;
import content.TvShowClass;
import enums.MembershipPlanEnum;
import netflix.exceptions.*;
/**
 * An implementation of the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class NetflixClass implements Netflix {
	
	/**
	 * Map of contents, mapped by title. 
	 */
	private Map<String, Content> contents;	// String is naturally ordered
	
	/**
	 * Collection of last added contents, sorted by title.
	 */
	private SortedSet<Content> lastAdded;
	/**
	 * Map of a sorted set of contents mapped by genre.
	 */
	private Map<String, SortedSet<Content>> contentByGenre;
	
	/**
	 * Map of a sorted set of contents mapped by cast name.
	 */
	private Map<String, SortedSet<Content>> contentByCast;
	
	/**
	 * Map of accounts mapped by email.
	 */
	private Map<String, Account> accounts;
	
	/**
	 * Active account in the streaming service.
	 */
	private Account activeAcc;
	
	/**
	 * Creates a streaming service.
	 */
	public NetflixClass() {
		contents = new HashMap<String, Content>(); // title -> content
		lastAdded = new TreeSet<>(new ComparatorByTitle());
		contentByGenre = new HashMap<>();	// genre -> SortedSet<Content> (comparatorByTitle)
		contentByCast = new HashMap<>();	// castName -> SortedSet<Content> (comparatorByYear)
		accounts = new HashMap<>();
		activeAcc = null;
	}
	
	@Override
	public void uploadMovie(String title, String director, int duration, int ageRate, int year, String genre,
			List<String> cast) {
		Content m = new MovieClass(title, director, duration, ageRate, year, genre, cast);
		loadInStorage(m);
	}

	@Override
	public void uploadTvShow(String title, String creator, int seasons, int epsPerSeason, int ageRate, int year,
			String genre, List<String> cast) {
		Content m = new TvShowClass(title, creator, seasons, epsPerSeason, ageRate, year, genre, cast);
		loadInStorage(m);
	}

	@Override
	public Iterator<Content> listLastUploaded() {
		return lastAdded.iterator();
	}

	@Override
	public Iterator<String> listCastMembers(Content cont) {
		return cont.listCast();
	}

	@Override
	public void register(String client, String email, String password, String deviceID) throws OccupiedServiceException, ExistentAccountException {
		Account acc = new AccountClass(client, email, password, deviceID);
		// Map<String, Account> accounts; email -> Account
		if(this.isClientLogged())
			throw new OccupiedServiceException();
		if(accounts.containsKey(email))
			throw new ExistentAccountException();
		accounts.put(email, acc);
		
		try {
			login(email, password, deviceID);		// in this case exceptions won't be thrown but are caught anyway.
		} catch (ClientAlreadyLoggedInException e) {
		} catch(OccupiedServiceException e) {
		} catch (WrongPasswordException e) {
		} catch (ExceededMaxNumberDevicesException e) {
		}
		
	}

	@Override
	public String login(String email, String password, String deviceID) throws 
	ClientAlreadyLoggedInException, OccupiedServiceException, AccountDoesNotExistException, WrongPasswordException, ExceededMaxNumberDevicesException {
		if(activeAcc != null && activeAcc.getEmail().equalsIgnoreCase(email))
			throw new ClientAlreadyLoggedInException();
		
		if(this.isClientLogged())
			throw new OccupiedServiceException();
		
		if(!accounts.containsKey(email))
			throw new AccountDoesNotExistException();
		
		Account acc = accounts.get(email);
		if(!acc.getPassword().equals(password))
			throw new WrongPasswordException();
		try {
			acc.addDevice(deviceID);		// propagates ExceededMaxNumberDevicesException
		} catch(DeviceAlreadyExistsException e) {
			// device already exists, doesn't get added
		} 

		activeAcc = acc;
		try {
			activeAcc.setActiveDevice(deviceID);
		} catch (DeviceNotRegistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return acc.getName();
	}
	
	@Override
	public Account getActiveAccount() throws NoClientLoggedInException {
		if(!isClientLogged())
			throw new NoClientLoggedInException();
		
		return activeAcc;
	}
	
	@Override
	public void disconnect() throws NoClientLoggedInException{
		Account activeAcc = getActiveAccount();				// can throw a NoClientLoggedInException
		activeAcc.removeDevice(activeAcc.listDevices().next());
		logout();
	}

	@Override
	public void logout() {
		activeAcc = null;
	}

	@Override
	public void setMembershipPlan(String plan) throws NoClientLoggedInException, MembershipUnchangedException, DowngradeMembershipException {
		MembershipPlanEnum p = MembershipPlanEnum.getValue(plan);
		Account activeAcc = getActiveAccount();				// can throw a NoClientLoggedInException
		activeAcc.setPlan(p);		// can throw MembershipUnchangedException or DowngradeMembershipException
	}

	@Override
	public void profile(String profileName) throws NoClientLoggedInException, ProfileAlreadyExistException, ProfileNumberExccededException {
		Account activeAcc = getActiveAccount();				// can throw a NoClientLoggedInException
		activeAcc.addProfile(profileName);		// can throw an exception
	}

	@Override
	public void profileKids(String profileName, int ageRate) throws NoClientLoggedInException, ProfileAlreadyExistException, ProfileNumberExccededException {
		Account activeAcc = getActiveAccount();				// can throw a NoClientLoggedInException
		activeAcc.addChildrenProfile(profileName, ageRate);		// can throw an exception
	}

	@Override
	public void select(String profileName) throws NoClientLoggedInException, ProfileDoesNotExistException {
		Account activeAcc = getActiveAccount();		// can throw a NoClientLoggedInException
		activeAcc.setActiveProfile(profileName);
	}

	@Override
	public void watch(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rate(String title, int rating) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Account> infoAccount() {
		// TODO Auto-generated method stub
		// uses active account
		return null;
	}
	
	/**
	 * Private method to add a content to the existing collections.
	 * @param cont content to be added.
	 */
	private void loadInStorage(Content cont) {
		//SortedSet<Content> lastAdded;
		lastAdded.add(cont);
		
		// Map<String, Content> contents
		contents.put(cont.getTitle(), cont);
		
		storeInContentByGenre(cont);
		storeInContentByCast(cont);
	}
	
	/**
	 * Private method to add a content to the collection of content by genre.
	 * @param cont content to be added to the collection.
	 */
	private void storeInContentByGenre(Content cont) {
		SortedSet<Content> contentWithGenre;
		String genre = cont.getGenre();
		if(!contentByGenre.containsKey(genre)) {
			contentWithGenre = new TreeSet<>(new ComparatorByTitle()); 	// ordered by title	
			contentByGenre.put(genre, contentWithGenre);
		}else
			contentWithGenre = contentByGenre.get(genre);	// returns set of contents associated to the genre					
		// Map<String, SortedSet<Content>> contentByGenre;
		contentWithGenre.add(cont); // remove mapping to this key
	}
	
	/**
	 * Private method to add a content to the collection of content by cast.
	 * @param cont content to be added to the collection.
	 */
	private void storeInContentByCast(Content cont) {
		List<String> completeCast = new LinkedList<String>();	// copy of cast plus the director/creator
		Iterator<String> it = cont.listCast();
		while(it.hasNext())
			completeCast.add(it.next());
		completeCast.add(cont.getHeadOfContent());		// adds creator/director
		
		SortedSet <Content> contentWithCast;	// collection of contents with a member of a cast
		for(String castName : completeCast) {	// for(each) cast name
			if(!contentByCast.containsKey(castName)) {
				contentWithCast = new TreeSet<Content>(new ComparatorByYear());
				contentByCast.put(castName, contentWithCast);
			}else
				contentWithCast = contentByCast.get(castName);
			contentWithCast.add(cont);
			// Map<String, SortedSet<Content>> contentByCast;
		}	
	}
	
	/**
	 * Private method to verify if there's an account logged in the service.
	 * @return <code>true</code> if there's an account logged in the service or <code>false</code> otherwise.
	 */
	private boolean isClientLogged() {
		return activeAcc != null;
	}

	
}
