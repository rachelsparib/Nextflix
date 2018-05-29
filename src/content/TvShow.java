package content;

/**
 * A tv show in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface TvShow extends Content {
	/**
	 * Returns name of the creator of the tv show.
	 * @return tv show creator's name.
	 */
	String getCreator();
}
