/*
 * boleias individuais
 */
public class Ride {
	private BasicDate basicDate;
	private String origin;
	private String destination;
	private int time;
	private double duration;
	private int seats;
	private int availableSeats;

	public Ride(String origin, String destination, BasicDate basicDate, int time, double duration, int seats) {
		this.basicDate = basicDate;
		this.origin = origin;
		this.destination = destination;
		this.time = time;
		this.duration = duration;
		this.seats = seats;
		this.availableSeats = seats;
	}
	//devolve data da boleia
	public BasicDate getDate() {
		return basicDate;
	}
	//devolve origem da boleia
	public String getOrigin() {
		return origin;
	}
	//devolve destino da boleia
	public String getDestination() {
		return destination;
	}
	//devolve hora da boleia
	public int getTime() {
		return time;
	}
	//devolve duracao da boleia
	public double getDuration() {
		return duration;
	}
	//devolve lotacao da boleia
	public int getSeats() {
		return seats;
	}
	//decrementa o numero de lugares da boleia
	public void seatsDec() {
		availableSeats--;
	}
	//devolve lugares livres
	public int getAvailableSeats() {
		return availableSeats;
	}
	//devolve lurages ocupados
	public int getSeatsTaken() {
		return seats - availableSeats;
	}

}
