package netflix;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

import netflix.account.Account;
import netflix.content.Content;

/**
 * A streaming service with user account management and audiovisual contents (movies and tv shows).
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Netflix {
	
	// also add to other collections after creating the object!
	/**
	 * 
	 * @param title
	 * @param director
	 * @param duration
	 * @param ageRate
	 * @param year
	 * @param genre
	 * @param cast
	 */
	void uploadMovie(String title, String director, int duration, String ageRate, int year, String genre, List<String> cast);
	// use lastUploaded
	
	void uploadTvShow(String title, String creator, int seasons, int epsPerSeason, String ageRate, int year, String genre, List<String> cast);
	// use lastUploaded
	// sortedMap has to define (always) the comparator used. It is the structure chosen to allow to obtain certain elements in the future
	// and list them according to a certain order.
	// title -> Content
	/**
	 * Returns an iterator for the map of last added content, sorted by title.
	 * @param lastContent content to be added to the map of last contents uploaded.
	 * @return an iterator for the map of last added content, sorted by title.
	 */
	Iterator<Content> lastUploaded(Content lastContent); // add argument to SortedMap<String, Content>
	//toString of content will have all the information plus the first 3 cast members
	
	
	void register(String client, String email, String password, String deviceID);
	
	void login(String client, String passoword, String deviceID);	// may add new device if is the first time used. Sets active account (temporary object)
	
	// private boolean isClientLogged(); in NetflixClass.  Account active != null ?
	
	void disconnect();	// terminates session. Has to know active account. Removes the device used.
	
	void logout();		// terminates session. Does not remove device used.
	
	
	void setMembershipPlan(String plan);	// use active session. isClientLogged?
	
	void profile(String profileName);
	
	void profileKids(String profileName, String ageRate);
	
	void select(String profileName);	//profile selection in a logged account
	
	void watch(String title);	// V get(Object key) -> Content get(title) -> Map<String, Content> contents
	
	void rate(String title, int rating);	// update contentByRate? ratedMovies(String, Set<Content>)
	
	Iterator<Account> infoAccount();		// uses active account. gets toString of Account and connected devices and profiles, both by order of insertion
							// define iterators to use in this method
							// partir este metodo em varios iteradores
	
	//private //metodo para inserir nas estruturas: permite depois alterar facilmente a estrutura utilizada para armazenamento					
	
}
