import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import enums.CommandEnum;
import enums.MessageEnum;
import netflix.Netflix;

/**
 * A program to manage a streaming service.
 * @author Antonio Santos 49055 MIEI
 *
 */
public class Main {
	
	/**
	 * Auxiliary method for obtaining the command option.
	 * @param in standard input.
	 * @return command obtained from input.
	 */
	private static CommandEnum getOption(Scanner in) {
		CommandEnum comm = CommandEnum.getValue(in.nextLine());
		if(comm == null)
			return CommandEnum.UNKNOWN;
		return comm;
					
	}
	
	/**
	 * Auxiliary method for executing the program options (functionalities).
	 */
	private static void interpreter() {
		Scanner in = new Scanner(System.in);
		Netflix n = new NetflixClass();
		CommandEnum c = getOption(in);
		
		while(!c.equals(CommandEnum.EXIT)) {
			switch(c) {
			case UPLOAD:
				//
				break;
			case 
			
			
			
			case UNKNOWN:
				System.out.println(MessageEnum.INVALID_COMMAND);
				break;
			default:
				// do nothing
				break;
			}
			System.out.println();
			c = getOption(in);
		}
		System.out.print(MessageEnum.EXIT_SUCCESS);
		
	}
	
	
	private static void upload(Scanner in, Netflix n) {
		// movie upload
		int nMovie = in.nextInt(); in.nextLine();
		String movTitle = in.nextLine();
		String director = in.nextLine();
		int movDuration = in.nextInt(); in.nextLine();
		String movAgeRate = in.nextLine();
		int movYear = in.nextInt(); in.nextLine();
		String movGenre = in.nextLine();
		int nCastMov = in.nextInt();
		List<String> movCast = new LinkedList<String>();
		for(int i = 0; i < nCastMov; i++)
			movCast.add(in.nextLine());
		n.uploadMovie(movTitle, director, movDuration, movAgeRate, movYear, movGenre, movCast);
		// tvShow upload
		int nSeries = in.nextInt(); in.nextLine();
		String seriesTitle = in.nextLine();
		String creator = in.nextLine();
		int seasons = in.nextInt(); in.nextLine();
		int epsPerSeason = in.nextInt(); in.nextLine();
		String seriesAgeRate = in.nextLine();
		int seriesYear = in.nextInt(); in.nextLine();
		String seriesGenre = in.nextLine();
		List<String> seriesCast = new LinkedList<String>();
		for(int i = 0; i < nCastMov; i++)
			movCast.add(in.nextLine());
		n.uploadTvShow(seriesTitle, creator, seasons, epsPerSeason, seriesAgeRate, seriesYear, seriesGenre, seriesCast);
		//Iterator<Content> it = n.get
		
		// toString movie : 
		
		//add genres to genre collection <String, SortedSet ? <Content>>
		
		
	}
	
	
	/**
	 * Auxiliary method for printing iterable collections.
	 * @param it an iterator of a generic type.
	 */
	private static <E> void printIterator(Iterator<E> it) {
		while (it.hasNext())
			System.out.println(it.next().toString());	
}
	
	
	/**
	 * Main program.
	 * @param args
	 */
	public static void main(String[] args) {
		Main.interpreter();
		
		
	}

}
