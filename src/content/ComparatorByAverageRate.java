package content;

import java.util.Comparator;

public class ComparatorByAverageRate implements Comparator<RatedContent> {

	@Override
	public int compare(RatedContent o1, RatedContent o2) {
		if (o1.getAverage() < o2.getAverage())
			return 1;
		else if (o1.getAverage() > o2.getAverage())
			return -1;
		else
			return 0;
	}

}
