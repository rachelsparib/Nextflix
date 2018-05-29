package netflix;

import java.util.Iterator;
import java.util.List;

import account.Account;
import content.Content;
import netflix.exceptions.AccountDoesNotExistsException;
import netflix.exceptions.ClientAlreadyLoggedInException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.ExistentAccountException;
import netflix.exceptions.NoClientLoggedInException;
import netflix.exceptions.WrongPasswordException;


/**
 * A streaming service with user account management and audiovisual contents (movies and tv shows).
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
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
	void uploadMovie(String title, String director, int duration, String ageRate, int year, String genre, List<String> cast);
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
	void uploadTvShow(String title, String creator, int seasons, int epsPerSeason, String ageRate, int year, String genre, List<String> cast);
	
	/**
	 * Returns an iterator for the map of last added content, sorted by title.
	 * @return an iterator for the map of last added content, sorted by title.
	 */
	Iterator<Content> lastUploaded();
	//toString of content will have all the information except for the cast members
	
	/**
	 * Returns the collection of cast members of the content <code>cont</code>.
	 * @param cont content whose cast members will be retrieved.
	 * @return list of cast members of the content <code>cont</code>.
	 */
	List<String> castMembers(Content cont);
	
	/**
	 * Adds a client to the account database.
	 * @param client client's name.
	 * @param email client's email.
	 * @param password account's password.
	 * @param deviceID client's device ID of access.
	 * @throws OccupiedServiceException if an account is logged in the service,
	 * 		ExistentAccountException if the account to be added is equal to an existing account.
	 */
	void register(String client, String email, String password, String deviceID);
	
	/**
	 * 
	 * @param client
	 * @param passoword
	 * @param deviceID
	 * @throws ClientAlreadyLoggedInException 
	 * @throws AccountDoesNotExistsException 
	 * @throws WrongPasswordException 
	 * @throws ExceededMaxNumberDevicesException 
	 */
	Account login(String client, String passoword, String deviceID) throws ClientAlreadyLoggedInException, AccountDoesNotExistsException, WrongPasswordException, ExceededMaxNumberDevicesException;	// may add new device if is the first time used. Sets active account (temporary object)
		
	LogoutResult disconnect() throws NoClientLoggedInException;	// terminates session. Has to know active account. Removes the device used.
	
	LogoutResult logout() throws NoClientLoggedInException;		// terminates session. Does not remove device used.
	
	void setMembershipPlan(String plan);	// use active session. isClientLogged?
	
	void profile(String profileName);
	
	void profileKids(String profileName, String ageRate);
	
	void select(String profileName);	//profile selection in a logged account
	
	void watch(String title);	// V get(Object key) -> Content get(title) -> Map<String, Content> contents
	
	void rate(String title, int rating);	// update contentByRate? ratedMovies(String, Set<Content>)
	
	Iterator<Account> infoAccount();		// uses active account. gets toString of Account and connected devices and profiles, both by order of insertion
							// define iterators to use in this method
							// partir este metodo em varios iteradores	
}
