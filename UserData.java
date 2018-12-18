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
	//devolve password de email especifico
	//Pre: hasUser(email) 
	public String getPassword(String email) {
		return user[searchIndex(email)].getPassword();
	}
	//devolve utilizador associado a email especifico
	//Pre: hasUser(email) 
	public User getUser(String email) {
		return user[searchIndex(email)];
	}
	//adiciona um novo utilizador a base de dados
	//Pre: !hasUser(email) 
	public void addUser(User user) {
		insertAt(user);
		count++;
	}
	//verifica se utilizador com este email ja existe
	//Pre: email!=null
	public boolean hasUser(String email) {
		return (searchIndex(email) >= 0);
	}

	private boolean greaterThan(User user, User u) {
		return user.getEmail().compareTo(u.getEmail()) < 0;
	}

	private void insertAt(User user) {
		if (isFull()) {
			resize();
		}
		int pos = searchPos(user);
		openSpace(pos);
		this.user[pos] = user;
	}
	//procura no vetor onde se pode colocar o novo utilizador
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
	//abre espaco para novo utilizador
	//Pre: pos<count
	private void openSpace(int pos) {
		for (int i = count - 1; i >= pos; i--) {
			user[i + 1] = user[i];
		}
	}
	//verifica se determinado utilizador ja existe
	//Pre: email!=null
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
	//verifica se vetor esta cheio
	private boolean isFull() {
		return (count == user.length);
	}
	//duplica o tamanho do vetor de utilizadores
	private void resize() {
		User[] temp = new User[GROWTH * user.length];
		for (int i = 0; i < user.length; i++) {
			temp[i] = user[i];
		}
		user = temp;
	}
}
