public class Iterator {
	private int count;
	private int current;
	private User[] user;
	public Iterator(User[] user, int count) {
		current = 0;
		
	}
	
	public boolean hasNext() {
		return current<count;
	}
	
	public User next() {
		return user[current++];
	}
}
