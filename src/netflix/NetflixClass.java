package netflix;

import java.util.Iterator;
import java.util.List;

import netflix.account.Account;
import netflix.content.Content;

public class NetflixClass implements Netflix {

	@Override
	public void uploadMovie(String title, String director, int duration, String ageRate, int year, String genre,
			List<String> cast) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadTvShow(String title, String creator, int seasons, int epsPerSeason, String ageRate, int year,
			String genre, List<String> cast) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Content> lastUploaded(Content lastContent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(String client, String email, String password, String deviceID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void login(String client, String passoword, String deviceID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMembershipPlan(String plan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void profile(String profileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void profileKids(String profileName, String ageRate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(String profileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void watch(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rate(String title, int rating) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Account> infoAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
