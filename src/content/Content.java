package content;

import java.util.List;

/**
 * An available content in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Content {
	
	/**
	 * Returns content's title.
	 * @return title of content.
	 */
	String getTitle(); // implemented in AbstractContent
	
	/**
	 * Returns content's age rate.
	 * @return content's age rate.
	 */
	String getAgeRate();
	
	/**
	 * Returns content's year of production.
	 * @return year of production of the content.
	 */
	int getYear();	// implemented in AbstractContent
	
	/**
	 * Returns content's genre.
	 * @return genre of the content.
	 */
	String getGenre();
	
	/**
	 * Returns collection of cast members names.
	 * @return collection of cast members names.
	 */
	List<String> getCast();
	
	/**
	 * Returns information of a content.
	 * @return information of a content.
	 */
	String toString(); 	// not implemented in AbstractContent
	
}
