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
        //tem seguinte
	public boolean hasNext() {
		return current < count;
	}
        //da seguinte
	// Pre: hasNext()
	public Ride next() {
		return ride[current++];
	}
}