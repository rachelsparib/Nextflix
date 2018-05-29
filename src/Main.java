import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import account.Account;
import content.Content;
import enums.CommandEnum;
import enums.MessageEnum;
import netflix.LogoutResult;
import netflix.Netflix;
import netflix.NetflixClass;
import netflix.exceptions.AccountDoesNotExistsException;
import netflix.exceptions.ClientAlreadyLoggedInException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.ExistentAccountException;
import netflix.exceptions.NoClientLoggedInException;
import netflix.exceptions.OccupiedServiceException;
import netflix.exceptions.WrongPasswordException;

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
		return CommandEnum.valueOf(in.nextLine().toUpperCase());
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
			case REGISTER:
				register(in, n);
				break;
			case LOGIN:
				login(in, n);
				break;
			case DISCONNECT:
				disconnect(n);
				break;
			case LOGOUT:
				logout(n);
				break;
			case MEMBERSHIP:
				//
				break;
			case PROFILE:
				//
				break;
			case SELECT:
				//
				break;
			case WATCH:
				//
				break;
			case RATE:
				//
				break;
			case INFOACCOUNT:
				//
				break;
			case SEARCHBYGENRE:
				//
				break;
			case SEARCHBYNAME:
				//
				break;
			case SEARCHBYRATE:
				//
				break;
			default:
				System.out.println(MessageEnum.INVALID_COMMAND);
				break;
			}
			System.out.println();
			c = getOption(in);
		}
		System.out.print(MessageEnum.EXIT_SUCCESS);

	}

	/**
	 * Auxiliary method for uploading content to the streaming service.
	 * 
	 * @param in
	 *            standard input.
	 * @param n
	 *            a streaming service.
	 */
	private static void upload(Scanner in, Netflix n) {
		uploadMovies(in, n);
		uploadTvShows(in, n);

		System.out.println(MessageEnum.UPLOAD_SUCCESS);
		Iterator<Content> it = n.lastUploaded();
		while (it.hasNext()) {
			Content cont = it.next();
			System.out.print(cont);
			printCast(n.castMembers(cont));
			System.out.print(".\n");
		}
	}

	/**
	 * Auxiliary method for uploading movies.
	 * 
	 * @param in
	 *            standard input.
	 * @param n
	 *            a streaming service.
	 */
	private static void uploadMovies(Scanner in, Netflix n) {
		int nMovie = in.nextInt();
		in.nextLine();
		for (int i = 0; i < nMovie; i++) {
			String movTitle = in.nextLine();
			String director = in.nextLine();
			int duration = in.nextInt();
			in.nextLine();
			String movAgeRate = in.nextLine();
			int movYear = in.nextInt();
			in.nextLine();
			String movGenre = in.nextLine();
			int nCastMov = in.nextInt();
			in.nextLine();

			List<String> movCast = new LinkedList<String>();
			for (int j = 0; j < nCastMov; j++)
				movCast.add(in.nextLine());
			n.uploadMovie(movTitle, director, duration, movAgeRate, movYear, movGenre, movCast);
		}
	}

	/**
	 * Auxiliary method for uploading tv shows.
	 * 
	 * @param in
	 *            standard input.
	 * @param n
	 *            a streaming service.
	 */
	private static void uploadTvShows(Scanner in, Netflix n) {
		int nSeries = in.nextInt();
		in.nextLine();
		for (int i = 0; i < nSeries; i++) {
			String seriesTitle = in.nextLine();
			String creator = in.nextLine();
			int seasons = in.nextInt();
			in.nextLine();
			int epsPerSeason = in.nextInt();
			in.nextLine();
			String seriesAgeRate = in.nextLine();
			int seriesYear = in.nextInt();
			in.nextLine();
			String seriesGenre = in.nextLine();
			int nCastSeries = in.nextInt();
			in.nextLine();

			List<String> seriesCast = new LinkedList<String>();
			for (int j = 0; j < nCastSeries; j++)
				seriesCast.add(in.nextLine());
			n.uploadTvShow(seriesTitle, creator, seasons, epsPerSeason, seriesAgeRate, seriesYear, seriesGenre,
					seriesCast);
		}

	}

	/**
	 * Auxiliary method for printing a maximum of three cast members names.
	 * 
	 * @param cast
	 *            cast members of a content.
	 */
	private static void printCast(List<String> cast) {
		ListIterator<String> it = cast.listIterator();
		while (it.hasNext() && it.nextIndex() < 3) // condition wrong???
			System.out.print("; " + it.next());
	}

	private static void register(Scanner in, Netflix n) {
		String name = in.nextLine();
		String email = in.nextLine();
		String pwd = in.nextLine();
		String devID = in.nextLine();
		try {
			n.register(name, email, pwd, devID);
		} catch (OccupiedServiceException e) {

		} catch (ExistentAccountException e) {

		}
	}

	private static void login(Scanner in, Netflix n) {
		String email = in.nextLine();
		String pwd = in.nextLine();
		String devID = in.nextLine();

		try {
			Account acc = n.login(email, pwd, devID);

			System.out.format("Welcome %s (%s).\n", acc.getName(), devID);
		} catch (ClientAlreadyLoggedInException e) {
			System.out.println(MessageEnum.CLIENT_ALREADY_LOGGEDIN);
		} catch (AccountDoesNotExistsException e) {
			System.out.println(MessageEnum.ANOTHER_CLIENT_LOGGEDIN);
		} catch (WrongPasswordException e) {
			System.out.println(MessageEnum.ACCOUNT_DOES_NOT_EXIST);
		} catch (ExceededMaxNumberDevicesException e) {
			System.out.println(MessageEnum.NOT_POSSIBLE_CONNECT_MORE_DEVICES);
		}
	}

	private static void disconnect(Netflix n) {
		try {
			LogoutResult res = n.disconnect();
			
			System.out.format("Goodbye %s (%s was disconnected).\n", res.getAccount().getName(), res.getDeviceId());
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGIN);
		}
	}

	

	private static void logout(Netflix n) {
		try {
			LogoutResult res = n.logout();
			System.out.format("Goodbye %s (%s still connected).\n", res.getAccount().getName(), res.getDeviceId());
			
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGIN);
		}
		
	}

	/**
	 * Auxiliary method for printing iterable collections.
	 * 
	 * @param it
	 *            an iterator of a generic type.
	 */
	private static <E> void printIterator(Iterator<E> it) { // TODO check
															// utility by the
															// end
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
