/*
 * boleias individuais
 */
public class Ride {
	private String date;
	private String origin;
	private String destination;
	private double time;
	private int duration;
	private int seats;
	private int availableSeats;

	public Ride(String origin, String destination, String date, double time, int duration, int seats) {
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

	public double getTime() {
		return time;
	}

	public int getDuration() {
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
