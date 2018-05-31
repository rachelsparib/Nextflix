package content;

/**
 * Auxiliary class that stores content with rating.
 *
 */
public class RatedContent {
	private Content content;
	private int rating;
	
	public RatedContent(Content content) {
		this.content = content;
		this.setRating(-1);  // -1 it is not rated yet.
	}
	
	public Content getContent() {
		return content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		if (rating > 0)
			return content.getTitle() + " (" + rating + ")";
		else
			return content.getTitle();
	}
}
