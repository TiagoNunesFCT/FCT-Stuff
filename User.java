/*
 * utilizador individual
 */
public class User {
	private String email;
	private String name;
	private String password;

	// pre: email != null && name != null || password != null
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		RideData rideData = new RideData();
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public boolean equals(User otherUser) {// verificar se e preciso
		return email.equals(otherUser.getEmail());
	}
}
