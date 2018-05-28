package netflix;
/**
 * An available content in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Content extends Comparable<Content>{
	
	/**
	 * Returns content's title.
	 * @return title of content.
	 */
	String getTitle(); // implemented in AbstractContent
	
	/**
	 * Returns content's year of production.
	 * @return year of production of the content.
	 */
	int getYear();	// implemented in AbstractContent
	
	/**
	 * Returns information of a content.
	 * @return information of a content.
	 */
	String toString(); 	// not implemented in AbstractContent
	
}
