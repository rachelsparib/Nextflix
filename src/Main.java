import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import enums.CommandEnum;
import enums.MessageEnum;
import netflix.Netflix;
import netflix.NetflixClass;

/**
 * A program to manage a streaming service.
 * 
 * @author Antonio Santos 49055 MIEI
 *
 */
public class Main {

	/**
	 * Auxiliary method for obtaining the command option.
	 * 
	 * @param in
	 *            standard input.
	 * @return command obtained from input.
	 */
	private static CommandEnum getOption(Scanner in) {
		CommandEnum comm = CommandEnum.getValue(in.nextLine());
		if (comm == null)
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

		while (!c.equals(CommandEnum.EXIT)) {
			switch (c) {
			case UPLOAD:
				upload(in, n);
				break;

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
	
	//
	private static void upload(Scanner in, Netflix n) {
		
		int entryCount = in.nextInt();
		in.nextLine();
		for(int i = 0; i < entryCount; i++){
			String title = in.nextLine();
			String director = in.nextLine();
			int temp1 = in.nextInt();
			in.nextLine();
			String temp2 = in.nextLine();
			
			// verificar se se trata de um filme ou serie
			if(temp2.indexOf('+') != -1){
				// trata se de um filme
				
				int movDuration = temp1;
				String movAgeRate = temp2;
				int movYear = in.nextInt();
				in.nextLine();
				String movGenre = in.nextLine();
				
				int nCastMov = in.nextInt();
				in.nextLine();
				
				List<String> movCast = new LinkedList<String>();
				for (int j = 0; j < nCastMov; j++)
					movCast.add(in.nextLine());
				
				n.uploadMovie(title, director, movDuration, movAgeRate, movYear, movGenre, movCast);
				
			} else{
				//trata se de uma serie
				
				int seasons = temp1;
				int epsPerSeason = Integer.parseInt(temp2);
				String seriesAgeRate = in.nextLine();
				int seriesYear = in.nextInt();
				in.nextLine();
				String seriesGenre = in.nextLine();
				
				int nCastSeries = in.nextInt();
				in.nextLine();
				
				List<String> seriesCast = new LinkedList<String>();
				for (int j = 0; j < nCastSeries; j++)
					seriesCast.add(in.nextLine());
				
				n.uploadTvShow(title, director, seasons, epsPerSeason, seriesAgeRate, seriesYear, seriesGenre, seriesCast);
			}
		}
	}

	/**
	 * Auxiliary method for printing iterable collections.
	 * 
	 * @param it
	 *            an iterator of a generic type.
	 */
	private static <E> void printIterator(Iterator<E> it) {
		while (it.hasNext())
			System.out.println(it.next().toString());
	}

	/**
	 * Main program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main.interpreter();

	}

}
