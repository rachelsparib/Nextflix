package netflix;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import account.Account;
import account.AccountClass;
import content.ComparatorByTitle;
import content.Content;
import content.Movie;
import content.MovieClass;
import content.TvShow;
import content.TvShowClass;
import netflix.exceptions.AccountDoesNotExistsException;
import netflix.exceptions.ClientAlreadyLoggedInException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.ExistentAccountException;
import netflix.exceptions.NoClientLoggedInException;
import netflix.exceptions.OccupiedServiceException;
import netflix.exceptions.WrongPasswordException;
/**
 * An implementation of the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public class NetflixClass implements Netflix {
	
	/**
	 * Collection of contents, sorted by title. 
	 */
	SortedMap<String, Content> contents;	// String is naturally ordered
	
	/**
	 * Collection of last added contents, sorted by title.
	 */
	SortedSet<Content> lastAdded;
	/**
	 * Collection of content mapped by genre.
	 */
	Map<String, SortedSet<Content>> contentByGenre;
	
	/**
	 * Collection of content mapped by cast name.
	 */
	Map<String, SortedSet<Content>> contentByCast;
	
	/**
	 * Collection of accounts mapped by email.
	 */
	Map<String, Account> accounts;
	
	/**
	 * Active account in the streaming service.
	 */
	Account activeAcc;
	
	String activeDeviceId;
	
	/**
	 * Creates a streaming service.
	 */
	public NetflixClass() {
		contents = new TreeMap<String, Content>(); // title -> content
		lastAdded = new TreeSet<>(new ComparatorByTitle());
		contentByGenre = new HashMap<>();	// genre -> SortedSet<Content> (comparatorByTitle)
		contentByCast = new HashMap<>();	// castName -> SortedSet<Content> (comparatorByYear)
		accounts = new HashMap<>();
		activeAcc = null;
	}
	
	@Override
	public void uploadMovie(String title, String director, int duration, String ageRate, int year, String genre,
			List<String> cast) {
		Content m = new MovieClass(title, director, duration, ageRate, year, genre, cast);
		loadInStorage(m);
	}

	@Override
	public void uploadTvShow(String title, String creator, int seasons, int epsPerSeason, String ageRate, int year,
			String genre, List<String> cast) {
		Content m = new TvShowClass(title, creator, seasons, epsPerSeason, ageRate, year, genre, cast);
		loadInStorage(m);
	}

	@Override
	public Iterator<Content> lastUploaded() {
		return lastAdded.iterator();
	}

	@Override
	public List<String> castMembers(Content cont) {
		return cont.getCast();
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
	}

	@Override
	public Account login(String email, String password, String deviceID) throws ClientAlreadyLoggedInException, AccountDoesNotExistsException, WrongPasswordException, ExceededMaxNumberDevicesException {
		
		if(activeAcc != null && activeAcc.getEmail().equalsIgnoreCase(email))
			throw new ClientAlreadyLoggedInException();
		
		if(this.isClientLogged())
			throw new OccupiedServiceException();

		if(!accounts.containsKey(email))
			throw new AccountDoesNotExistsException();
		
		Account acc = accounts.get(email);
		if(!acc.getPass().equals(password))
			throw new WrongPasswordException();
		
		if(!acc.hasDevice(deviceID)){
			//first time using device
			if(acc.getDeviceCount() + 1 > acc.getMaxDevices())
				throw new ExceededMaxNumberDevicesException();
			
			acc.addDevice(deviceID);
		}
		
		activeAcc = acc;
		activeDeviceId = deviceID;
		
		return activeAcc;
	}

	@Override
	public LogoutResult disconnect() throws NoClientLoggedInException {
		if(!isClientLogged())
			throw new NoClientLoggedInException();
		
		activeAcc.removeDevice(activeDeviceId);

		return logout();
	}

	@Override
	public LogoutResult logout() throws NoClientLoggedInException {
		if(!isClientLogged())
			throw new NoClientLoggedInException();
		
		LogoutResult res = new LogoutResult(activeAcc, activeDeviceId);
		
		activeAcc = null;
		activeDeviceId = null;
		
		return res;
	}

	@Override
	public void setMembershipPlan(String plan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void profile(String profileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void profileKids(String profileName, String ageRate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(String profileName) {
		// TODO Auto-generated method stub

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
		return null;
	}
	
	/**
	 * Private method to add a content to the existing collections.
	 * @param cont content to be added.
	 */
	private void loadInStorage(Content cont) {
		//SortedSet<Content> lastAdded;
		lastAdded.add(cont);
		
		// SortedMap<String, Content> contents
		contents.put(cont.getTitle(), cont);
		
		SortedSet<Content> contentWithGenre;
		String genre = cont.getGenre();
		if(!contentByGenre.containsKey(genre))
			contentWithGenre = new TreeSet<>(new ComparatorByTitle()); 	// ordered by title	
		else
			contentWithGenre = contentByGenre.get(genre);	// returns set of contents associated to the genre
		// Map<String, SortedSet<Content>> contentByGenre;
		contentByGenre.put(cont.getGenre(), contentWithGenre);
		
		String headOfContent;
		if(cont instanceof Movie)
			headOfContent = ((Movie) cont).getDirector();
		else
			headOfContent = ((TvShow) cont).getCreator();
		
		List<String> team = cont.getCast();	// tenho de fazer new para uma nova copia?
		//team.add(headOfContent);	TODO ESTAVA A INSERIR NO CAST EXISTENTE
		
		// add castNames to cast name collection <String, SortedSet <Content>> 
		// foreach cast name
		//for(List<String> team : values())
			
		// SearchByName junta criador ou diretor ao cast
		// ContentByName: adicionar diretor, criador e membros do cast
		
		// instanceOf(movie), add creator
	}
	
	private boolean isClientLogged() {
		return activeAcc != null;
	}
}
