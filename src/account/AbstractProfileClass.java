package account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import content.Content;
import content.RatedContent;
import netflix.exceptions.AlreadyRatedException;
import netflix.exceptions.NotInRecentlyWatchedException;

/**
 * An implementation of a customizable profile of an account.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public abstract class AbstractProfileClass implements Profile {
	private static final int MAX_WATCHED_SIZE = 10;
	/**
	 * The name of the profile.
	 */
	private String name;
	
	/**
	 * A collection of the recently watched contents.
	 */
	private LinkedList<RatedContent> recentlyWatched;
	private LinkedList<RatedContent> recentlyRated;
	
	/**
	 * Creates a profile with name <code>name</code>.
	 * @param name name of the profile.
	 */
	public AbstractProfileClass(String name) {
		this.name = name;
		this.recentlyWatched = new LinkedList<>();	// queue
		this.recentlyRated = new LinkedList<>();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Iterator<RatedContent> listRecentlyWatched() {
		return this.recentlyWatched.iterator();
	}
	
	@Override
	public void addToWatched(Content cont) {
		RatedContent ratedContent = new RatedContent(cont);
		
		recentlyWatched.addFirst(ratedContent);	// enqueue
		if (recentlyWatched.size() > MAX_WATCHED_SIZE)
			recentlyWatched.removeLast();	// dequeue
	}
	
	@Override
	public void rate(String title, int rating) throws NotInRecentlyWatchedException, AlreadyRatedException {
		for(RatedContent c : recentlyWatched) {
			if(c.getContent().getTitle().equals(title)) {
				if (c.getRating() > 0)
					throw new AlreadyRatedException();
				
				c.setRating(rating);
				recentlyRated.add(c);
				
				return;
			}
		}
		
		throw new NotInRecentlyWatchedException();
	}
	
	@Override
	public Iterator<RatedContent> listRated() {
		return recentlyRated.iterator();
	}
}
