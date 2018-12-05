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
	}

	public Iterator iterator() {
		return new Iterator(ride, count);
	}
	public void addRide(Ride ride) {
		if (isFull()) {
			resize();
		}
		insertAt(ride, searchPos(ride));
		count++;/////
	}

	public boolean hasRide(String date) {
		return (searchIndex(date) >= 0);
	}

	public Ride getRide(String date) {
		if (searchIndex(date) == -1) {
			return null;
		} else {
			return ride[searchIndex(date)];
		}
	}
	
	public void remove(String date) {
		int pos = searchIndex(date);
		for(int i = pos; i<count-1; i++) {
			ride[i] = ride[i+1];
			count--;
		}
	}

	private int searchIndex(String date) {
		boolean found = false;
		int i = 0;
		int result = -1;
		while ((i < count) && (!found)) {
			if (ride[i].getDate().equals(date)) {
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

	private void insertAt(Ride ride, int pos) {
		openSpace(pos);
		this.ride[pos] = ride;
		if(isFull()) {
			resize();
		}
	}

	private void openSpace(int pos) {
		for (int i = count - 1; i >= pos; i--) {
			ride[i + 1] = ride[i];
		}
	}

	private int searchPos(Ride ride) {
		int pos = count;
		int i = 0;
		BasicDate d  = new BasicDate(ride.getDate());
		while (i < count && pos == count) {
			Ride rideCompare = this.ride[i];
			BasicDate dCompare  = new BasicDate(rideCompare.getDate());
			if (d.getYear() > dCompare.getYear()) {
				pos = i;
			}else {
				if(d.getMonth()>dCompare.getMonth()) {
					pos = i;
				}else {
					if(d.getDay()>dCompare.getDay()) {
						pos = i;
					}
				}
			}
			i++;
		}
		return pos;
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
