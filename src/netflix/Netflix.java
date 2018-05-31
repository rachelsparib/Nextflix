package netflix;

import java.util.Iterator;
import java.util.List;

import account.Account;
import content.Content;
import netflix.exceptions.*;

/**
 * A streaming service with user account management and audiovisual contents (movies and tv shows).
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public interface Netflix {
	
	// also add to other collections after creating the object!
	/**
	 * Adds a movie to the collection of content.
	 * @param title title of the movie.
	 * @param director director of the movie.
	 * @param duration movie's duration.
	 * @param ageRate movie's age rate.
	 * @param year movie's year of production.
	 * @param genre movie's genre.
	 * @param cast movie's cast members.
	 */
	void uploadMovie(String title, String director, int duration, int ageRate, int year, String genre, List<String> cast);
	// sortedMap has to define (always) the comparator used. It is the structure chosen to allow obtaining certain elements
	// in the future and list them according to a certain order.
	
	/**
	 * Adds a tv show to the collection of content.
	 * @param title tv show's title.
	 * @param creator tv show's creator.
	 * @param seasons number of seasons of the show.
	 * @param epsPerSeason number of episodes per season.
	 * @param ageRate age rate of the show.
	 * @param year year of production of the show.
	 * @param genre genre of the show.
	 * @param cast cast members of the show.
	 */
	void uploadTvShow(String title, String creator, int seasons, int epsPerSeason, int ageRate, int year, String genre, List<String> cast);
	
	/**
	 * Returns an iterator for the map of last added content, sorted by title.
	 * @return an iterator for the map of last added content, sorted by title.
	 */
	Iterator<Content> listLastUploaded();
	//toString of content will have all the information except for the cast members
	
	/**
	 * Returns an iterator for the collection of cast members of the content <code>cont</code>.
	 * @param cont content whose cast members will be retrieved.
	 * @return an iterator for the collection of cast members of the content <code>cont</code>.
	 */
	Iterator<String> listCastMembers(Content cont);
	
	/**
	 * Adds a client to the account database.
	 * @param name client's name.
	 * @param email client's email.
	 * @param password account's password.
	 * @param deviceID client's device ID of access.
	 * @throws OccupiedServiceException if an account is logged in the service.
	 * @throws ExistentAccountException if the account to be added is equal to an existing account.
	 */
	void register(String name, String email, String password, String deviceID) throws OccupiedServiceException, ExistentAccountException;
	
	/**
	 * Logins an account with email <code>email</code>, password <code>password</code>, in the device with ID <code>deviceID</code>.
	 * @param email account's email.
	 * @param password account's password.
	 * @param deviceID ID of the device used to access the service.
	 * @return name of the client of the account logged.
	 * @throws ClientAlreadyLoggedInException if the client is already logged in the service.
	 * @throws OccupiedServiceException if there's another account logged (service is busy).
	 * @throws AccountDoesNotExistException if the account used to login doesn't exist.
	 * @throws WrongPasswordException if the password used to login is incorrect.
	 * @throws ExceededMaxNumberDevicesException if the number of connected devices allowed is exceeded. 
	 */
	String login(String email, String password, String deviceID) throws ClientAlreadyLoggedInException, 
			OccupiedServiceException, AccountDoesNotExistException, WrongPasswordException, ExceededMaxNumberDevicesException;
	// may add new device if is the first time used. Sets active account (temporary object)
	
	/**
	 * Returns the active account, or null if it there isn't none.
	 * @return active account, or null if it there's none.
	 * @throws NoClientLoggedInException if it there isn't an active account.
	 */
	Account getActiveAccount() throws NoClientLoggedInException;
	
	/**
	 * Logout and disconnects the device used.
	 * @throws NoClientLoggedInException if it there isn't an active account.
	 * 
	 */
	void disconnect() throws NoClientLoggedInException;	
	
	/**
	 * Terminates an active session.
	 */
	void logout();
		
	/**
	 * Changes the account's membership plan to a new one.
	 * @param plan new membership plan.
	 * @throws NoClientLoggedInException if there isn't an account logged.
	 * @throws MembershipUnchangedException if the new plan is equal to the current one.
	 * @throws DowngradeMembershipException if the maximum number of devices allowed in the new plan
	 * 		 is inferior than the number of the already connected ones.
	 */
	void setMembershipPlan(String plan) throws NoClientLoggedInException, MembershipUnchangedException, 
	DowngradeMembershipException;
	
	/**
	 * Creates a new profile.
	 * @param profileName name of the profile.
	 * @param ageRate age rate of the profile.
	 * @param isKids <code>true</code> if its a profile for kids, false otherwise.
	 * @throws NoClientLoggedInException if there isn't an account logged.
	 * @throws ProfileAlreadyExistException if it's attempted to create a profile with the same name of an existent one
	 * @throws ProfileNumberExccededException if the number of profiles allowed is exceeded. 
	 */
	void profile(String profileName, int ageRate, boolean isKids) throws NoClientLoggedInException,
	ProfileAlreadyExistException, ProfileNumberExceededException;
	
	/**
	 * Selects a profile of the account.
	 * @param profileName name of the profile.
	 * @throws NoClientLoggedInException if there isn't an account logged.
	 * @throws ProfileDoesNotExistException if the profile that was attempted to access does not exist.
	 */
	void select(String profileName) throws NoClientLoggedInException, ProfileDoesNotExistException;
	
	/**
	 * Watch a content.
	 * @param title content's title.
	 * @throws NoClientLoggedInException if there isn't an account logged.
	 * @throws NoProfileSelectedException if there isn't a profile selected.
	 * @throws NonExistentContentException if the content to watch doesn't exist in the streaming service.
	 * @throws InnapropriateContentException if the content to watch is inappropriate to the profile in use.
	 */
	void watch(String title) throws NoClientLoggedInException, NoProfileSelectedException, NonExistentContentException,
	InapropriateContentException ;	
	
	/**
	 * Rates a watched content.
	 * @param title content's title.
	 * @param rating rating given to a content with title <code>title</code>.
	 * @throws NoClientLoggedInException if there isn't an account logged.
	 * @throws NoProfileSelectedException if there isn't a profile selected.
	 * @throws NonExistentContentException if the content to watch doesn't exist in the streaming service.
	 * @throws NotInRecentlyWatchedException if the content isn't in the collection of recently watched content.
	 * @throws AlreadyRatedException if the content was already rated.
	 */
	void rate(String title, int rating) throws NoClientLoggedInException, NoProfileSelectedException, NonExistentContentException,
	NotInRecentlyWatchedException, AlreadyRatedException;
	
	
	Iterator<Account> infoAccount();		// uses active account. gets toString of Account and connected devices and profiles, both by order of insertion
							// define iterators to use in this method
							// partir este metodo em varios iteradores
	
	/**
	 * 
	 * @param genre
	 * @return
	 */
	Iterator<Content> searchByGenre(String genre) throws NoClientLoggedInException, NoProfileSelectedException;
	
	Iterator<Content> searchByName(String name) throws NoClientLoggedInException, NoProfileSelectedException;
	
	Iterator<Content> searchByRate(String rate) throws NoClientLoggedInException, NoProfileSelectedException;
}
