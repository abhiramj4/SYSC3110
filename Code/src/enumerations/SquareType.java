package enumerations;

/**
 * @author Sai Vikranth Desu
 *
 */
public enum SquareType {
	LAWN("Lawn"), SPAWN("Spawn");

	private final String name;

	SquareType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
