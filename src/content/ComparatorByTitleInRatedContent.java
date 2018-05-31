package content;

import java.util.Comparator;

public class ComparatorByTitleInRatedContent implements Comparator<RatedContent> {

	@Override
	public int compare(RatedContent o1, RatedContent o2) {
		return o1.getContent().getTitle().compareTo(o2.getContent().getTitle());
	}

}
