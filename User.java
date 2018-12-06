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
        //devolve email
	public String getEmail() {
		return email;
	}
        //devolve password
	public String getPassword() {
		return password;
	}
        //devolve nome
	public String getName() {
		return name;
	}
        //utilizador ja existe
	public boolean equals(User otherUser) {// verificar se e preciso
		return email.equals(otherUser.getEmail());
	}
        //adiciona boleia
	public void addRide(Ride ride) {
		rideData.addRide(ride);
		rideNumber++;
	}
        //da lista de boleias
	public RideData getRideData() {
		return rideData;
	}
	//da numero de boleias
	public int getRideNumber() {
		return rideNumber;
	}
}
