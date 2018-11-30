/*
 * colecao de utilizadores
 */
public class UserData {
	private static final int GROWTH = 2;
	private int count;
	private static int NUM = 1;
	private User[] user;

	public UserData() {
		count = 0;
		user = new User[NUM];
	}
	
	public String getEmail(String email) {
		return email;
	}
	
	public String getName(String email) {
		return user[searchIndex(email)].getEmail();
	}
	
	
	public void addUser(String email) {
		
	}
	
	private boolean hasUser(String email) {
		//verificacoes de password numa biblioteca
		return (searchIndex(email)>=0);
	}

	private void check() {
		if (isFull()) {
			resize();
		}
	}
	
	private int searchIndex(String email) {
		boolean found = false;
		int i=0;
		int result = -1;
		while((i<count)&&(!found)) {
			if(user[i].getEmail().equals(email)) {
				found = true;
			}
			else i++;
		}
		if(found) {
			result = i;
		}
		return result;
	}

	private boolean isFull() {
		return count == user.length;
	}

	private void resize() {
		if (user.length != 0) {
			User[] temp = new User[GROWTH * user.length];
			for (int i = 0; i < user.length; i++) {
				temp[i] = user[i];
				user = temp;
			}
		} else {
			User[] temp = new User[1];
			for (int i = 0; i < user.length; i++) {
				temp[i] = user[i];
				user = temp;
			}
		}
		
	}
}
