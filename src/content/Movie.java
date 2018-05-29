package content;
/**
 * A movie in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Movie extends Content {
	/**
	 * Returns movie's director name.
	 * @return name of the director of the movie.
	 */
	String getDirector();
}
