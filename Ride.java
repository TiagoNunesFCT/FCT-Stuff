/*
 * boleias individuais
 */
public class Ride {
	private String date;
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
	
	
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public double getTime() {
		return time;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public int getSeats() {
		return seats;
	}
	
}
