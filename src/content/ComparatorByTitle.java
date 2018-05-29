package content;

import java.util.Comparator;
/**
 * Implementation of a comparator of Content by its title.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public class ComparatorByTitle implements Comparator<Content> {

	@Override
	public int compare(Content cont1, Content cont2) {
		// compare by title
		return cont1.getTitle().compareTo(cont2.getTitle());
	}
	
}
