import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import account.Account;
import content.Content;
import enums.CommandEnum;
import enums.MembershipPlanEnum;
import enums.MessageEnum;
import netflix.Netflix;
import netflix.NetflixClass;
import netflix.exceptions.AccountDoesNotExistException;
import netflix.exceptions.ClientAlreadyLoggedInException;
import netflix.exceptions.DowngradeMembershipException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.ExistentAccountException;
import netflix.exceptions.MembershipUnchangedException;
import netflix.exceptions.NoClientLoggedInException;
import netflix.exceptions.OccupiedServiceException;
import netflix.exceptions.ProfileAlreadyExistException;
import netflix.exceptions.ProfileDoesNotExistException;
import netflix.exceptions.ProfileNumberExccededException;
import netflix.exceptions.WrongPasswordException;

/**
 * A program to manage a streaming service.
 * 
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class Main {

	/**
	 * Auxiliary method for obtaining the command option.
	 * 
	 * @param in
	 *            standard input stream.
	 * @return command obtained from input.
	 */
	private static CommandEnum getOption(Scanner in) {
		String nextLine = in.nextLine().toUpperCase();
		
		return CommandEnum.valueOf(nextLine);
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
				membership(in, n);
				break;
			case PROFILE:
				profile(in, n);
				break;
			case SELECT:
				select(in, n);
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
		System.out.println(MessageEnum.EXIT_SUCCESS);
	}

	/**
	 * Auxiliary method for uploading content to the streaming service.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void upload(Scanner in, Netflix n) {
		boolean isMovie = true;
		uploadContent(in, n, isMovie);
		isMovie = false;
		uploadContent(in, n, isMovie);

		System.out.println(MessageEnum.UPLOAD_SUCCESS);
		Iterator<Content> it = n.listLastUploaded();
		while (it.hasNext()) {
			Content cont = it.next();
			System.out.print(cont);
			printCast(n.listCastMembers(cont));
			System.out.print(".\n");
		}
	}

	/**
	 * Auxiliary method for uploading content.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void uploadContent(Scanner in, Netflix n, boolean isMovie) {
		int nMovie = in.nextInt();
		in.nextLine();
		for (int i = 0; i < nMovie; i++) {
			String title = in.nextLine();
			String head = in.nextLine();
			int duration = 0;
			int seasons = 0;
			int epsPerSeason = 0;
			if (isMovie)
				duration = in.nextInt();
			else {
				seasons = in.nextInt();
				in.nextLine();
				epsPerSeason = in.nextInt();
			}
			in.nextLine();
			String s = in.nextLine();
			int ageRate = Integer.parseInt(s.substring(0, s.indexOf("+")));
			int year = in.nextInt();
			in.nextLine();
			String genre = in.nextLine();
			int nCast = in.nextInt();
			in.nextLine();

			List<String> cast = new LinkedList<String>();
			for (int j = 0; j < nCast; j++)
				cast.add(in.nextLine());
			if (isMovie)
				n.uploadMovie(title, head, duration, ageRate, year, genre, cast);
			else
				n.uploadTvShow(title, head, seasons, epsPerSeason, ageRate, year, genre, cast);
		}
	}

	/**
	 * Auxiliary method for printing a maximum of three cast members names.
	 * 
	 * @param cast
	 *            cast members of a content.
	 */
	private static void printCast(Iterator<String> listCast) {
		int i = 0;
		while (listCast.hasNext() && i < 3) {
			System.out.print("; " + listCast.next());
			i++;
		}
	}

	/**
	 * Auxiliary method to register a new account in the streaming service.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void register(Scanner in, Netflix n) {
		String name = in.nextLine();
		String email = in.nextLine();
		String pwd = in.nextLine();
		String deviceID = in.nextLine();
		try {
			n.register(name, email, pwd, deviceID);
			System.out.println(MessageEnum.LOGIN_SUCCESS + name + " (" + deviceID + ").");
		} catch (OccupiedServiceException e) {
			System.out.println(MessageEnum.SERVICE_BUSY);
		} catch (ExistentAccountException e) {
			System.out.println(MessageEnum.INVALID_REGISTRATION + email + ".");
		}
	}

	/**
	 * Auxiliary method to login in the streaming service.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void login(Scanner in, Netflix n) {
		String email = in.nextLine();
		String pwd = in.nextLine();
		String devID = in.nextLine();

		try {
			String name = n.login(email, pwd, devID);
			System.out.println(MessageEnum.LOGIN_SUCCESS + name + " (" + devID + ").");
		} catch (ClientAlreadyLoggedInException e) {
			System.out.println(MessageEnum.CLIENT_ALREADY_LOGGEDIN);
		} catch (OccupiedServiceException e) {
			System.out.println(MessageEnum.SERVICE_BUSY);
		} catch (AccountDoesNotExistException e) {
			System.out.println(MessageEnum.ACCOUNT_DOES_NOT_EXIST);
		} catch (WrongPasswordException e) {
			System.out.println(MessageEnum.WRONG_PASS);
		} catch (ExceededMaxNumberDevicesException e) {
			System.out.println(MessageEnum.NOT_POSSIBLE_CONNECT_MORE_DEVICES);
		}
	}

	/**
	 * Auxiliary method to terminate an active session and disconnect a device.
	 * 
	 * @param n
	 *            a streaming service.
	 */
	private static void disconnect(Netflix n) {
		try {
			Account c = n.getActiveAccount(); // may launch
												// NoClientLoggedException
			String name = c.getName();
			String deviceID = c.getActiveDevice();
			n.disconnect();
			System.out.println(MessageEnum.LOGOUT_SUCCESS + name + " (" + deviceID + MessageEnum.DEV_DISCONNECTED);
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGGED);
		}
	}

	/**
	 * Auxiliary method to terminate an active session.
	 * 
	 * @param n
	 *            a streaming service.
	 */
	private static void logout(Netflix n) {
		try {
			Account c = n.getActiveAccount(); // may launch
												// NoClientLoggedException
			
			String name = c.getName();
			String deviceID = c.getActiveDevice();
			n.logout();
			System.out.println(MessageEnum.LOGOUT_SUCCESS + name + " (" + deviceID + MessageEnum.DEV_STILL_CONNECTED);
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGGED);
		}
	}

	/**
	 * Auxiliary method for changing a membership plan.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void membership(Scanner in, Netflix n) {
		String newPlan = in.nextLine();
		try {
			Account c = n.getActiveAccount(); // may launch
												// NoClientLoggedException
			MembershipPlanEnum currentPlan = c.getPlan();
			n.setMembershipPlan(newPlan);
			System.out.println(MessageEnum.MEMBERSHIP_CHANGED_SUCESS + currentPlan.getName() + " to " + newPlan + ".");
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGGED);
		} catch (MembershipUnchangedException e) {
			System.out.println(MessageEnum.NO_MEMBERSHIP_CHANGE);
		} catch (DowngradeMembershipException e) {
			System.out.println(MessageEnum.CANNOT_DOWNGRADE_MEMBERSHIP);
		}
	}

	/**
	 * Auxiliary method to add a new profile to an account.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void profile(Scanner in, Netflix n) {
		String name = in.nextLine();
		String type = in.nextLine();

		try {
			int ageRate;
			if (type.equalsIgnoreCase("CHILDREN")) {
				ageRate = in.nextInt();
				in.nextLine();

				n.profileKids(name, ageRate);
			} else {
				n.profile(name);
			}
			
			System.out.println(MessageEnum.PROFILE_ADDED);
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGGED);
		} catch (ProfileAlreadyExistException e) {
			System.out.println(MessageEnum.PROFILE_ALREADY_EXIST + name + ".");
		} catch (ProfileNumberExccededException e) {
			System.out.println(MessageEnum.PROFILE_MAX_EXCCEDED);
		}
	}

	/**
	 * Auxiliary method to select a new profile to an account.
	 * 
	 * @param in
	 *            standard input stream.
	 * @param n
	 *            a streaming service.
	 */
	private static void select(Scanner in, Netflix n) {
		String name = in.nextLine();
		try {
			n.select(name);
			
			System.out.println(MessageEnum.LOGIN_SUCCESS + name + ".");
		} catch (NoClientLoggedInException e) {
			System.out.println(MessageEnum.NO_CLIENT_IS_LOGGED);
		} catch (ProfileDoesNotExistException e) {
			System.out.println(MessageEnum.PROFILE_DOES_NOT_EXIST);
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
