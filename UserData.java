/*
 * colecao de utilizadores
 */
public class UserData {
	private static final int GROWTH = 2;
	private static final int START = 1000;
	private int count;
	private User[] user;

	public UserData() {
		count = 0;
		user = new User[START];
		// Iterator it = new Iterator(user, count);
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
			if (user[i].getEmail().equals(email)) {
				found = true;
			} else {
				i++;
			}
		}
		if (found) {
			result = i;
		}
		return result;
	}

	private boolean isFull() {
		System.out.println(count);
		System.out.println(user.length);
		return count == user.length;
	}

	private void resize() {
		User[] temp = new User[GROWTH * user.length];
		for (int i = 0; i < user.length; i++) {
			temp[i] = user[i];
			user = temp;
		}
	}
}
