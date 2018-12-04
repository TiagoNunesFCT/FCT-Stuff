/*
 * colecao de boleias
 */
public class RideData {
	private static final int GROWTH = 2;
	private static final int START = 1;
	private int count;
	private Ride[] ride;

	public RideData() {
		count = 0;
		ride = new Ride[START];
		// Iterator
	}

	public void addRide(Ride ride) {
		if (isFull()) {
			resize();
		}
		this.ride[count++] = ride;
	}

	public boolean hasRide(String date) {
		return (searchIndex(date) >= 0);
	}
	
	public Ride getRide(String date) {
		return ride[searchIndex(date)];
	}

	private int searchIndex(String date) {
		boolean found = false;
		int i = 0;
		int result = -1;
		/*while ((i < count) && (!found)) {
			if (ride[i].getDate().equals(date)) {
				found = true;
			} else {
				i++;
			}
		}*/
		if (found) {
			result = i;
		}
		return result;
	}

	private boolean isFull() {
		return count == ride.length;
	}

	private void resize() {
		Ride[] temp = new Ride[GROWTH * ride.length];
		for (int i = 0; i < ride.length; i++) {
			temp[i] = ride[i];
			ride = temp;
		}
	}
}
