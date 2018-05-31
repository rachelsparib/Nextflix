package netflix.exceptions;
/**
 * An exception caused by attempting to rate a content that wasn't recently watched.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class NotInRecentlyWatchedException extends RuntimeException {
	public NotInRecentlyWatchedException() {
		super();
	}
}
