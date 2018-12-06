/*
 * boleias individuais
 */
public class Ride {
	private String date;
	private String origin;
	private String destination;
	private int time;
	private double duration;
	private int seats;
	private int availableSeats;

	public Ride(String origin, String destination, String date, int time, double duration, int seats) {
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		this.time = time;
		this.duration = duration;
		this.seats = seats;
		availableSeats = seats;
	}

	public String getDate() {
		return date;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public int getTime() {
		return time;
	}

	public double getDuration() {
		return duration;
	}

	public int getSeats() {
		return seats;
	}

	public void seatsDec() {
		availableSeats--;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public int getSeatsTaken() {
		return seats - availableSeats;
	}

}
