package netflix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import account.Account;
import account.AccountClass;
import account.ChildrenProfile;
import account.Profile;
import content.ComparatorByAverageRate;
import content.ComparatorByTitle;
import content.ComparatorByTitleInRatedContent;
import content.ComparatorByYear;
import content.Content;
import content.MovieClass;
import content.RatedContent;
import content.TvShowClass;
import enums.MembershipPlanEnum;
import javafx.collections.transformation.SortedList;
import netflix.exceptions.AccountDoesNotExistException;
import netflix.exceptions.AlreadyRatedException;
import netflix.exceptions.ClientAlreadyLoggedInException;
import netflix.exceptions.DeviceAlreadyExistsException;
import netflix.exceptions.DowngradeMembershipException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.ExistentAccountException;
import netflix.exceptions.InapropriateContentException;
import netflix.exceptions.MembershipUnchangedException;
import netflix.exceptions.NoClientLoggedInException;
import netflix.exceptions.NoProfileSelectedException;
import netflix.exceptions.NoShowFoundException;
import netflix.exceptions.NonExistentContentException;
import netflix.exceptions.NotInRecentlyWatchedException;
import netflix.exceptions.OccupiedServiceException;
import netflix.exceptions.ProfileAlreadyExistException;
import netflix.exceptions.ProfileDoesNotExistException;
import netflix.exceptions.ProfileNumberExceededException;
import netflix.exceptions.WrongPasswordException;

/**
 * An implementation of the streaming service.
 * 
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class NetflixClass implements Netflix {

	/**
	 * Map of contents, mapped by title.
	 */
	private Map<String, Content> contents; // String is naturally ordered

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
		contents = new HashMap<String, Content>(); // title -> Content
		lastAdded = new TreeSet<>(new ComparatorByTitle()); // SortedSet<Content>
		contentByGenre = new HashMap<>(); // genre -> SortedSet<Content>
											// (comparatorByTitle)
		contentByCast = new HashMap<>(); // castName -> SortedSet<Content>
											// (comparatorByYear)
		accounts = new HashMap<>(); // email -> Account
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
	public void register(String client, String email, String password, String deviceID)
			throws OccupiedServiceException, ExistentAccountException {
		Account acc = new AccountClass(client, email, password, deviceID);
		// Map<String, Account> accounts; email -> Account
		if (this.isClientLogged())
			throw new OccupiedServiceException();
		if (accounts.containsKey(email))
			throw new ExistentAccountException();
		accounts.put(email, acc);

		try {
			login(email, password, deviceID); // in this case exceptions won't
												// be thrown but are caught
												// anyway.
		} catch (ClientAlreadyLoggedInException e) {
		} catch (OccupiedServiceException e) {
		} catch (WrongPasswordException e) {
		} catch (ExceededMaxNumberDevicesException e) {
		}

	}

	@Override
	public String login(String email, String password, String deviceID)
			throws ClientAlreadyLoggedInException, OccupiedServiceException, AccountDoesNotExistException,
			WrongPasswordException, ExceededMaxNumberDevicesException {
		if (activeAcc != null && activeAcc.getEmail().equalsIgnoreCase(email))
			throw new ClientAlreadyLoggedInException();
		if (this.isClientLogged())
			throw new OccupiedServiceException();
		if (!accounts.containsKey(email))
			throw new AccountDoesNotExistException();
		Account acc = accounts.get(email);
		if (!acc.getPassword().equals(password))
			throw new WrongPasswordException();
		try {
			acc.addDevice(deviceID); // propagates
										// ExceededMaxNumberDevicesException
		} catch (DeviceAlreadyExistsException e) {
			// device already exists, doesn't get added
		}
		activeAcc = acc; // references the account being used
		activeAcc.setActiveDevice(deviceID);
		return activeAcc.getName();
	}

	@Override
	public Account getActiveAccount() throws NoClientLoggedInException {
		if (!isClientLogged())
			throw new NoClientLoggedInException();
		return activeAcc;
	}

	@Override
	public void disconnect() throws NoClientLoggedInException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		activeAcc.removeDevice(activeAcc.getActiveDevice()); // get active
																// device
		logout();
	}

	@Override
	public void logout() {
		activeAcc.logoutProfile();
		activeAcc = null;
	}

	@Override
	public void setMembershipPlan(String plan)
			throws NoClientLoggedInException, MembershipUnchangedException, DowngradeMembershipException {
		MembershipPlanEnum p = MembershipPlanEnum.getValue(plan);
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		activeAcc.setPlan(p); // can throw MembershipUnchangedException or
								// DowngradeMembershipException
	}

	@Override
	public void profile(String profileName, int ageRate, boolean isKids)
			throws NoClientLoggedInException, ProfileAlreadyExistException, ProfileNumberExceededException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		activeAcc.addProfile(profileName, ageRate, isKids); // can throw a
															// ProfileAlreadyExistException
															// or
															// ProfileNumberExceededException
	}

	@Override
	public void select(String profileName) throws NoClientLoggedInException, ProfileDoesNotExistException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		activeAcc.setActiveProfile(profileName); // can throw a
													// ProfileDoesNotExistException
	}

	@Override
	public void watch(String title) throws NoClientLoggedInException, NoProfileSelectedException,
			NonExistentContentException, InapropriateContentException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		if (!activeAcc.isProfileActive())
			throw new NoProfileSelectedException();
		if (!contents.containsKey(title))
			throw new NonExistentContentException();
		// Map<String, Content> contents
		Content c = contents.get(title);
		activeAcc.watchContent(c); // can throw an InapropriateContentException
	}

	@Override
	public void rate(String title, int rating) throws NoClientLoggedInException, NoProfileSelectedException,
			NonExistentContentException, NotInRecentlyWatchedException, AlreadyRatedException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		if (!activeAcc.isProfileActive())
			throw new NoProfileSelectedException();
		if (!contents.containsKey(title))
			throw new NonExistentContentException();
		activeAcc.rateContent(title, rating); // may throw
												// NotInRecentlyWatchedException
												// or AlreadyRatedException
		storeInContentByRate(title, rating);
	}

	@Override
	public Iterator<Account> infoAccount() {
		// TODO Auto-generated method stub
		// uses active account
		return null;
	}

	/**
	 * Private method to add a content to the existing collections.
	 * 
	 * @param cont
	 *            content to be added.
	 */
	private void loadInStorage(Content cont) {
		// SortedSet<Content> lastAdded;
		lastAdded.add(cont);

		// Map<String, Content> contents
		contents.put(cont.getTitle(), cont);

		storeInContentByGenre(cont);
		storeInContentByCast(cont);
	}

	/**
	 * Private method to add a content to the collection of content by genre.
	 * 
	 * @param cont
	 *            content to be added to the collection.
	 */
	private void storeInContentByGenre(Content cont) {
		SortedSet<Content> contentWithGenre;
		String genre = cont.getGenre();
		if (!contentByGenre.containsKey(genre)) {
			contentWithGenre = new TreeSet<>(new ComparatorByTitle()); // ordered
																		// by
																		// title
			contentByGenre.put(genre, contentWithGenre);
		} else
			contentWithGenre = contentByGenre.get(genre); // returns set of
															// contents
															// associated to the
															// genre
		// Map<String, SortedSet<Content>> contentByGenre;
		contentWithGenre.add(cont); // remove mapping to this key
	}

	/**
	 * Private method to add a content to the collection of content by cast.
	 * 
	 * @param cont
	 *            content to be added to the collection.
	 */
	private void storeInContentByCast(Content cont) {
		List<String> completeCast = new LinkedList<String>(); // copy of cast
																// plus the
																// director/creator
		Iterator<String> it = cont.listCast();
		while (it.hasNext())
			completeCast.add(it.next());
		completeCast.add(cont.getHeadOfContent()); // adds creator/director

		SortedSet<Content> contentWithCast; // collection of contents with a
											// member of a cast
		for (String castName : completeCast) { // for(each) cast name
			if (!contentByCast.containsKey(castName)) {
				contentWithCast = new TreeSet<Content>(new ComparatorByYear());
				contentByCast.put(castName, contentWithCast);
			} else
				contentWithCast = contentByCast.get(castName);
			contentWithCast.add(cont);
			// Map<String, SortedSet<Content>> contentByCast;
		}
	}

	/**
	 * Private method to verify if there's an account logged in the service.
	 * 
	 * @return <code>true</code> if there's an account logged in the service or
	 *         <code>false</code> otherwise.
	 */
	private boolean isClientLogged() {
		return activeAcc != null;
	}

	/**
	 * Private method to store a content in the collection of ratings.
	 * 
	 * @param title
	 *            content's title.
	 * @param rating
	 *            rating given to a content with title <code>title</code>.
	 */
	private void storeInContentByRate(String title, int rating) {
		// SortedMap<String, Integer> ratings; // TODO not the right structure
		// ratings.put(title, Integer.valueOf(rating));
	}

	@Override
	public Iterator<Content> searchByGenre(String genre) throws NoClientLoggedInException, NoProfileSelectedException, NoShowFoundException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		if (!activeAcc.isProfileActive())
			throw new NoProfileSelectedException();
		
		if (!contentByGenre.containsKey(genre))
			throw new NoShowFoundException();

		return contentByGenre.get(genre).iterator();
	}

	@Override
	public Iterator<Content> searchByName(String name) throws NoClientLoggedInException, NoProfileSelectedException, NoShowFoundException {
		Account activeAcc = getActiveAccount(); // can throw a
												// NoClientLoggedInException
		if (!activeAcc.isProfileActive())
			throw new NoProfileSelectedException();

		if (!contentByCast.containsKey(name))
			throw new NoShowFoundException();
		
		return contentByCast.get(name).iterator();
	}

	@Override
	public Iterator<RatedContent> searchByRate(int rate) throws NoClientLoggedInException, NoProfileSelectedException {
		Account activeAcc = getActiveAccount(); // can throw a NoClientLoggedInException
		if (!activeAcc.isProfileActive())
			throw new NoProfileSelectedException();
		
		Profile profile = activeAcc.getActiveProfile();
		
		Map<Content, List<Integer>> contentRateAvg = new HashMap<Content, List<Integer>>();

		int minAge = 200;
		if (profile instanceof ChildrenProfile) {
			ChildrenProfile children = (ChildrenProfile) profile;
			minAge = children.getAgeRate();
		}

		for (Account a : accounts.values()) {
			Iterator<Profile> it = a.listProfiles();
			while (it.hasNext()) {
				Profile p = it.next();

				Iterator<RatedContent> it2 = p.listRated();
				while (it2.hasNext()) {
					RatedContent rc = it2.next();

					if (rc.getRating() >= rate) {
						Content c = rc.getContent();

						if (c.getAgeRate() <= minAge) {
							List<Integer> ratings;
							if (!contentRateAvg.containsKey(c)) {
								ratings = new ArrayList<>();
								contentRateAvg.put(c, ratings);
							} else {
								ratings = contentRateAvg.get(c);
							}

							ratings.add(rc.getRating());
						}
					}
				}
			}
		}
		
		// calculate average by content.
		TreeMap<String, List<RatedContent>> contentSortedByRate = new TreeMap<>();
		
		for (Content c : contentRateAvg.keySet()) {
			List<Integer> rates = contentRateAvg.get(c);
			
			float sum = 0.0f;
			for (Integer rateValue : rates) {
				sum += rateValue;
			}
			
			sum /= rates.size();

			RatedContent rc = new RatedContent(c);
			rc.setAverage(sum);
			
			String rateStr = Float.toString(sum).substring(0, 3);
			
			List<RatedContent> sortedRates;
			if (!contentSortedByRate.containsKey(rateStr)) {
				sortedRates = new ArrayList<>();
				contentSortedByRate.put(rateStr, sortedRates);
			} else
				sortedRates = contentSortedByRate.get(rateStr);
			
			sortedRates.add(rc);
		}
		
		List<RatedContent> result = new ArrayList<>();
		
		ComparatorByTitleInRatedContent compByTitle = new ComparatorByTitleInRatedContent();
		for (String rateStr : contentSortedByRate.keySet()) {
			List<RatedContent> rates = contentSortedByRate.get(rateStr);
			rates.sort(compByTitle);
			
			result.addAll(rates);
		}
		result.sort(new ComparatorByAverageRate());
		
		return result.iterator();
	}

}
