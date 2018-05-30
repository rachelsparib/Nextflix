package content;

import java.util.List;
/**
 * An implementation of a movie in the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class MovieClass extends AbstractContentClass implements Movie {
	/**
	 * The movie's duration.
	 */
	private int duration;
	
	/**
	 * Creates a movie in the streaming service.
	 * @param title movie's title.
	 * @param director director's name.
	 * @param duration movie's duration.
	 * @param ageRate movie's age rate.
	 * @param year movie's year of production.
	 * @param genre movie's genre.
	 * @param cast names of the movie's cast members.
	 */
	public MovieClass(String title, String director, int duration, int ageRate, int year, String genre, List<String> cast) {
		super(title, director, ageRate, year, genre, cast);
		this.duration = duration;
	}

	@Override
	public String toString() {
		return super.getTitle() + "; " + super.getHeadOfContent() + "; " + duration + "; " + super.getAgeRate() + "+" + "; "
		+ super.getYear() + "; " + super.getGenre();
	}
	// toString movie: title + director + duration + ageRate + year + genre 
	
}
