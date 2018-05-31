package content;

import java.util.Iterator;
import java.util.List;
/**
 * A content in the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public abstract class AbstractContentClass implements Content, Comparable<Content> {
	/**
	 * The content's title.
	 */
	private String title;
	
	/**
	 * The content's head name.
	 */
	private String head;
	
	/**
	 * The content's age rate.
	 */
	private int ageRate;
	
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
	private List<String> cast;
	
	/**
	 * Creates a content in the streaming service.
	 * @param title content's title.
	 * @param head content's head name.
	 * @param ageRate content's age rate.
	 * @param year content's year of production.
	 * @param genre content's genre.
	 * @param cast names of the content's cast members.
	 */
	protected AbstractContentClass(String title, String head, int ageRate, int year, String genre, List<String> cast) {
		this.title = title;
		this.head = head;
		this.ageRate = ageRate;
		this.year = year;
		this.genre = genre;
		this.cast = cast;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	public String getHeadOfContent() {
		return head;
	}
	
	public int getAgeRate() {
		return ageRate;
	}
	
	@Override
	public int getYear() {
		return year;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public Iterator<String> listCast(){
		return cast.iterator();
	}
	
	@Override
	public int compareTo(Content o) {
		return this.getTitle().compareToIgnoreCase(o.getTitle());
	}
	
	public abstract String toString();

}
