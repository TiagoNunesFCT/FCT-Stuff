/*
 * boleias individuais
 */
public class Ride {
	private int date;
	private String origin;
	private String destination;
	private double time;
	private double duration;
	private int seats;

	public Ride(String origin, String destination, BasicDate basicDate, double time, double duration, int seats) {
		this.origin = origin;
		this.destination = destination;
		this.time = time;
		this.duration = duration;
		this.seats = seats;
	}
}
