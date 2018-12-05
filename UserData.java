/*
 * colecao de utilizadores
 */
public class UserData {
	private static final int GROWTH = 2;
	private static final int START = 10000;
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
			//System.out.print("isFull: ");
			//System.out.println(isFull());
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
		System.out.print("comprimento do vetor de utilizadores: ");
		System.out.println(user.length);
		while ((i < count) && (!found)) {
			//System.out.print("found: ");
			//System.out.println(found);
			//System.out.print("count: ");
			//System.out.println(count);
			//System.out.print("i: ");
			//System.out.println(i);
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
		/*System.out.println(count);
		System.out.println(user.length);*/
		return (count == user.length);
	}

	private void resize() {
		//System.out.print("novo tamanho: ");
		//System.out.println(GROWTH * user.length);
		User[] temp = new User[GROWTH * user.length];
		//System.out.println();
		for (int i = 0; i < user.length; i++) {
			temp[i] = user[i];
			user = temp;
		}
	}
}
