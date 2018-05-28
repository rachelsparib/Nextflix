package netflix;

import java.util.Comparator;
/**
 * Implementation of a comparator of Content by year of production.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 * 
 */
public class ComparatorByYear implements Comparator<Content> {

	@Override
	public int compare(Content cont1, Content cont2) {
		int year1 = cont1.getYear();
		int year2 = cont2.getYear();
		if(year1 < year2)
			return -1;
		else if(year1 > year2)
			return 1;
		// tie breaker by title
		return cont1.getTitle().compareTo(cont2.getTitle());
	}

}
