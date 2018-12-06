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
        //da data
	public String getDate() {
		return date;
	}
        //da origem
	public String getOrigin() {
		return origin;
	}
        //da destino
	public String getDestination() {
		return destination;
	}
        //da hora
	public double getTime() {
		return time;
	}
        //da duracao
	public int getDuration() {
		return duration;
	}
        //da lugares
	public int getSeats() {
		return seats;
	}
        //ocupa um lugar
	public void seatsDec() {
		availableSeats--;
	}
        //da lotacao atual
	public int getAvailableSeats() {
		return availableSeats;
	}
        //da lugares ocupados
	public int getSeatsTaken() {
		return seats - availableSeats;
	}

}
