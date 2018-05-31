package account;

import java.util.Iterator;

import content.Content;
import content.RatedContent;
import netflix.exceptions.AlreadyRatedException;
import netflix.exceptions.NotInRecentlyWatchedException;

/**
 * A customizable profile of an account.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public interface Profile {
	/**
	 * Returns the name of the profile.
	 * @return profile's name.
	 */
	String getName();
	
	/**
	 * Returns an iterator for the collection of recently watched content.
	 * @return an iterator for the collection of recently watched content.
	 */
	Iterator<RatedContent> listRecentlyWatched();
	
	/**
	 * Ads a content to the collection of recently watched content.
	 * @param cont a content of the streaming service.
	 */
	void addToWatched(Content cont);
	
	/**
	 * Rate a content of the collection of recently watched contents.
	 * @param title content's title.
	 * @param rating rating given to a content with title <code>title</code>.
	 * @throws NotInRecentlyWatchedException if the content isn't in the collection of recently watched content.
	 * @throws AlreadyRatedException if the content was already rated.
	 */
	void rate(String title, int rating) throws NotInRecentlyWatchedException, AlreadyRatedException;
	
	/**
	 * Returns an iterator for the collection of rated content.
	 * @return  an iterator for the collection of rated content.
	 */
	Iterator<RatedContent> listRated();
}
