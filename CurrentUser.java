public class CurrentUser {
	public User user;

	public CurrentUser() {

	}

	//define o utilizador atual
	//Pre: user!=null
	public void setCurrentUser(User user) {
		this.user = user;
	}
	//devolve o utilizador da sessao atual
	public User getCurrentUser() {
		return user;
	}
	//termina a sessao
	//Pre: user!=null
	public void logout() {
		user = null;
	}
}
