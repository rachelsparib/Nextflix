package content;

import java.util.LinkedList;
import java.util.List;
/**
 * A content in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public abstract class AbstractContentClass implements Content {
	/**
	 * The content's title.
	 */
	private String title;
	
	/**
	 * The content's age rate.
	 */
	private String ageRate;
	
	/**
	 * The contents year of production.
	 */
	private int year;
	
	/**
	 * The content's genre.
	 */
	private String genre;
	
	/**
	 * The names of the content's cast members.
	 */
	List<String> cast;
	
	/**
	 * Creates a content in the streaming service.
	 * @param title content's title.
	 * @param ageRate content's age rate.
	 * @param year content's year of production.
	 * @param genre content's genre.
	 * @param cast names of the content's cast members.
	 */
	protected AbstractContentClass(String title, String ageRate, int year, String genre, List<String> cast) {
		this.title = title;
		this.ageRate = ageRate;
		this.year = year;
		this.genre = genre;
		this.cast = cast;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	public String getAgeRate() {
		return ageRate;
	}
	
	@Override
	public int getYear() {
		return year;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public List<String> getCast(){
		return cast;
	}
	
	public abstract String toString();

}
