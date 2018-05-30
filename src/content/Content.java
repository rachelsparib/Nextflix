package content;

import java.util.Iterator;

/**
 * An available content in the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public interface Content {
	
	/**
	 * Returns content's title.
	 * @return title of content.
	 */
	String getTitle(); // implemented in AbstractContent
	
	/**
	 * Returns the head of content's name (its creator or director).
	 * @return head of content's name.
	 */
	String getHeadOfContent();
	
	/**
	 * Returns content's age rate.
	 * @return content's age rate.
	 */
	int getAgeRate();
	
	/**
	 * Returns content's year of production.
	 * @return year of production of the content.
	 */
	int getYear();
	
	/**
	 * Returns content's genre.
	 * @return genre of the content.
	 */
	String getGenre();
	
	/**
	 * Returns an iterator for the collection of cast members names.
	 * @return an iterator for the collection of cast members names.
	 */
	Iterator<String> listCast();
	
	/**
	 * Returns information of a content.
	 * @return information of a content.
	 */
	String toString(); 	// not implemented in AbstractContent
	
}
