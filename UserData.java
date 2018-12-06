/*
 * colecao de utilizadores
 */
public class UserData {
	private static final int GROWTH = 2;
	private static final int START = 4;
	private int count;
	private User[] user;

	public UserData() {
		count = 0;
		user = new User[START];
	}

	public Iterator iterator() {
		return new Iterator(user, count);
	}

	public String getPassword(String email) {
		return user[searchIndex(email)].getPassword();
	}

	public User getUser(String email) {
		return user[searchIndex(email)];
	}

	public void addUser(User user) {
		insertAt(user);
		count++;
	}

	public boolean hasUser(String email) {
		return (searchIndex(email) >= 0);
	}

	public void sort() {
		for (int i = 1; i < count; i++) {
			for (int j = count - 1; j >= i; j--) {
				User temp = user[j - 1];
				user[j - 1] = user[j];
				user[j] = temp;
			}
		}
	}

	private boolean greaterThan(User user, User u) {
		return user.getEmail().compareTo(u.getEmail()) > 0;
	}

	private void insertAt(User user) {
		if (isFull()) {
			resize();
		}
		int pos = searchPos(user);
		openSpace(pos);
		this.user[pos] = user;
	}

	private int searchPos(User user) {
		int pos = count;
		int i = 0;
		while (i < count && pos == count) {
			if (greaterThan(user, this.user[i])) {
				pos = i;
			}
			i++;
		}
		return pos;
	}

	private void openSpace(int pos) {
		for (int i = count - 1; i >= pos; i--) {
			user[i + 1] = user[i];
		}
	}

	private int searchIndex(String email) {
		boolean found = false;
		int i = 0;
		int result = -1;
		while ((i < count) && (!found)) {
			found = user[i].getEmail().equals(email);
			if (!found) {
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
