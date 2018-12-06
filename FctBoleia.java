public class FctBoleia {
	public User user;

	public FctBoleia() {

	}
        //define utilizador atual
	public void setCurrentUser(User user) {
		this.user = user;
	}
        //devolve utilizador atual
	public User getCurrentUser() {
		return user;
	}
        //logout
	public void logout() {
		user = null;
	}
}
