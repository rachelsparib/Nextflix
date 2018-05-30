package content;

import java.util.List;
/**
 * An implemention of a tv show in the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class TvShowClass extends AbstractContentClass implements TvShow {
	/**
	 * Tv show's number of seasons.
	 */
	private int seasons;
	
	/**
	 * Tv show's number of episodes per season.
	 */
	private int epsPerSeason;
	
	/**
	 * Creates a tv show in the streaming service.
	 * @param title tv show's title.
	 * @param creator tv show's creator name.
	 * @param seasons number of seasons.
	 * @param epsPerSeason number of episodes per season.
	 * @param ageRate tv show's age rate.
	 * @param year tv show's year of production.
	 * @param genre tv show's genre.
	 * @param cast tv show's cast members names.
	 */
	public TvShowClass(String title, String creator, int seasons, int epsPerSeason, int ageRate, int year, String genre, List<String> cast) {
		super(title, creator, ageRate, year, genre, cast);
		this.seasons = seasons;
		this.epsPerSeason = epsPerSeason;
	}

	@Override
	public String toString() {
		return super.getTitle() + "; " + super.getHeadOfContent() + "; " + seasons + "; " + epsPerSeason + "; " 
	+ super.getAgeRate() + "+" + "; " + super.getYear() + "; " + super.getGenre();
	}
	// toString tvShow : title + creator + seasons + epsPerSeason + ageRate + year + genre


}
