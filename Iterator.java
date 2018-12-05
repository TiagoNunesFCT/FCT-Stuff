/*
 * iterador de boleias
 */
public class Iterator {
	private int count;
	private int current;
	private Ride[] ride;

	public Iterator(Ride[] ride, int count) {
		current = 0;
		this.ride = ride;
		this.count = count;
	}

	public boolean hasNext() {
		return current < count;
	}

	// Pre: hasNext()
	public Ride next() {
		return ride[current++];
	}
}