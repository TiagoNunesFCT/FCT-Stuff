public class FctBoleia {
	public User user;

	public FctBoleia() {

	}

	public void setCurrentUser(User user) {
		this.user = user;
	}

	public User getCurrentUser() {
		return user;
	}

	public void logout() {
		user = null;
	}
}
