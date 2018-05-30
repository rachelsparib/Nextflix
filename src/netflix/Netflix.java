package netflix;

import java.util.Iterator;
import java.util.List;

import account.Account;
import content.Content;
import netflix.exceptions.AccountDoesNotExistException;
import netflix.exceptions.ClientAlreadyLoggedInException;
import netflix.exceptions.DowngradeMembershipException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.ExistentAccountException;
import netflix.exceptions.MembershipUnchangedException;
import netflix.exceptions.NoClientLoggedInException;
import netflix.exceptions.OccupiedServiceException;
import netflix.exceptions.ProfileAlreadyExistException;
import netflix.exceptions.ProfileDoesNotExistException;
import netflix.exceptions.ProfileNumberExccededException;
import netflix.exceptions.WrongPasswordException;

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
	// sortedMap has to define (always) the comparator used. It is the structure chosen to allow to obtain certain elements
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
	 * @throws OccupiedServiceException if an account is logged in the service,
	 * 		ExistentAccountException if the account to be added is equal to an existing account.
	 */
	void register(String name, String email, String password, String deviceID) throws OccupiedServiceException, ExistentAccountException;
	
	/**
	 * Logins an account with email <code>email</code>, password <code>password</code>, in the device with ID <code>deviceID</code>.
	 * @param email account's email.
	 * @param password account's password.
	 * @param deviceID ID of the device used to access the service.
	 * @return name of the client of the account logged.
	 * @throws ClientAlreadyLoggedInException if the client is already logged in the service,
	 *  OccupiedServiceException if there's another account logged,
	 *   AccountDoesNotExistException if the account used to login doesn't exist,
	 *    WrongPasswordException if the password used to login is incorrect, 
	 *    ExceededMaxNumberDevicesException if the number of connected devices allowed is exceeded. 
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
	 * @throws NoClientLoggedInException if there ins't an account logged,
	 * 	MembershipUnchangedException if the new plan is equal to the current one,
	 * 	 DowngradeMembershipException if the maximum number of devices allowed in the new plan
	 * 		 is inferior than the number of the already connected ones.
	 */
	void setMembershipPlan(String plan) throws NoClientLoggedInException, MembershipUnchangedException, DowngradeMembershipException;
	
	/**
	 * Creates a new profile for adults.
	 * @param profileName name of the profile.
	 * @throws ProfileNumberExccededException 
	 * @throws ProfileAlreadyExistException 
	 */
	void profile(String profileName) throws ProfileAlreadyExistException, ProfileNumberExccededException;
	
	/**
	 * Creates a new profile for children.
	 * @param profileName name of the profile.
	 * @param ageRate age rate of the profile.
	 * @throws ProfileNumberExccededException 
	 * @throws ProfileAlreadyExistException 
	 */
	void profileKids(String profileName, int ageRate) throws ProfileAlreadyExistException, ProfileNumberExccededException;
	
	void select(String profileName) throws ProfileDoesNotExistException;	//profile selection in a logged account
	
	void watch(String title);	// V get(Object key) -> Content get(title) -> Map<String, Content> contents
	
	void rate(String title, int rating);	// update contentByRate? ratedMovies(String, Set<Content>)
	
	Iterator<Account> infoAccount();		// uses active account. gets toString of Account and connected devices and profiles, both by order of insertion
							// define iterators to use in this method
							// partir este metodo em varios iteradores	
}
