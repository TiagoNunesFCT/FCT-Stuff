/*
 * iterador de boleias
 */
public class Iterator {
	private int countUser;
	private int countRide;
	private int currentUser;
	private int currentRide;
	private User[] user;
	private Ride[] ride;
	

	public Iterator(Ride[] ride, int count) {
		currentRide = 0;
		this.ride = ride;
		countRide = count;
	}
	
	public Iterator(User[] user, int count) {
		currentUser = 0;
		this.user = user;
		countUser = count;
	}

	public boolean hasNext(String type) {
		if(type.equals("ride")) {
			return currentRide<countRide;
		}else {
			return currentUser<countUser;
		}
	}

	// Pre: hasNext()
	public Ride nextRide() {
		return ride[currentRide++];
	}
	// Pre: hasNext()
	public User nextUser() {
		return user[currentUser++];
	}
}