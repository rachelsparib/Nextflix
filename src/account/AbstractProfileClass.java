package account;
/**
 * An implementation of a customizable profile of an account.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public abstract class AbstractProfileClass implements Profile {
	
	private String name;
	
	public AbstractProfileClass(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
