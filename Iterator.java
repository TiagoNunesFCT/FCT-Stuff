/*
 * iterador de utilizadores
 */
public class Iterator {
	private int count;
	private int current;
	private User[] user;
	public Iterator(User[] user, int count) {
		current = 0;
		this.user = user;
		this.count = count;
	}
	
	public boolean hasNext() {
		return current<count;
	}
	//Pre: hasNext()
	public User next() {
		return user[current++];
	}
}