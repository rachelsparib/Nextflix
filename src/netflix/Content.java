package netflix;
/**
 * 
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Content extends Comparable<Content>{
	String getTitle();	// TODO check if its needed
	
	
	
	String toString();	//see searchByGenre/searchByName/SearchByRate command info
	
}
