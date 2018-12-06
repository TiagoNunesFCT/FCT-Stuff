/*
 * colecao de utilizadores
 */
public class UserData {
	private static final int GROWTH = 2;
	private static final int START = 1;
	private int count;
	private User[] user;

	public UserData() {
		count = 0;
		user = new User[START];
	}

	public String getPassword(String email) {
		return user[searchIndex(email)].getPassword();
	}

	public User getUser(String email) {
		return user[searchIndex(email)];
	}

	public void addUser(User user) {
		if (isFull()) {
			resize();
		}
		this.user[count++] = user;
	}

	public boolean hasUser(String email) {
		return (searchIndex(email) >= 0);
	}

	private int searchIndex(String email) {
		boolean found = false;
		int i = 0;
		int result = -1;
		while ((i < count) && (!found)) {
			found = user[i].getEmail().equals(email);
			if(!found) {
				i++;
			}
		}
		if (found) {
			result = i;
		}
		return result;
	}

	private boolean isFull() {
		return (count == user.length);
	}

	private void resize() {
		User[] temp = new User[GROWTH * user.length];
		for (int i = 0; i < user.length; i++) {
			temp[i] = user[i];
		}
		user = temp;
	}
}
