/*
 * utilizador individual
 */
public class User {
	private String email;
	private String name;
	private String password;
	private int rideNumber;
	RideData rideData;

	// pre: email != null && name != null || password != null
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		rideNumber = 0;
		rideData = new RideData();
	}
	//devolve email do utilizador
	public String getEmail() {
		return email;
	}
	//devolve password do utilizador
	public String getPassword() {
		return password;
	}
	//devolve nome do utilizador
	public String getName() {
		return name;
	}
	//adiciona boleia a este utilizador
	public void addRide(Ride ride) {
		rideData.addRide(ride);
		rideNumber++;
	}
	//devolve lista de boleias
	public RideData getRideData() {
		return rideData;
	}
	//devolve numero de boleias
	public int getRideNumber() {
		return rideNumber;
	}
	//decrementa em 1 o numero de boleias
	//Pre: rideNumber>0
	public void rideNumberDec() {
		rideNumber--;
	}
}
