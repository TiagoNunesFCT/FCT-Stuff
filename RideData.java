/*
 * colecao de boleias
 */
public class RideData {
	private static final int GROWTH = 2;
	private static final int START = 4;
	private int count;
	private Ride[] ride;

	public RideData() {
		count = 0;
		ride = new Ride[START];
	}

	public Iterator iterator() {
		return new Iterator(ride, count);
	}
	//adiciona boleia a lista
	//Pre: !hasRide(date) 
	public void addRide(Ride ride) {
		insertAt(ride);
		count++;
	}
	//verifica se boleia existe na lista
	//Pre: basicDate.isValid()
	public boolean hasRide(BasicDate basicDate) {
		return (searchIndex(basicDate.dateToString()) >= 0);
	}
	//devolve boleia especifica baseada em data
	//Pre: hasRide(date) && basicDate.isValid()
	public Ride getRide(BasicDate basicDate) {
		if (searchIndex(basicDate.dateToString()) == -1) {
			return null;
		} else {
			return ride[searchIndex(basicDate.dateToString())];
		}
	}
	
	//remove uma certa boleia
	//Pre: hasRide(date) && basicDate.isValid()
	public void remove(BasicDate basicDate) {
		int pos = searchIndex(basicDate.dateToString());
		ride[searchIndex(basicDate.dateToString())] = null;
		for (int i = pos; i < count - 1; i++) {
			ride[i] = ride[i + 1];
		}
		count--;
	}
	//pesquisa se existe boleia igual na mesma data
	private int searchIndex(String date) {
		boolean found = false;
		int i = 0;
		int result = -1;
		while ((i < count) && (!found)) {
			if (ride[i].getDate().dateToString().equals(date)) {
				found = true;
			} else {
				i++;
			}
		}
		if (found) {
			result = i;
		}
		return result;
	}
	//insere boleia em lugar encontrado
	private void insertAt(Ride ride) {
		if (isFull()) {
			resize();
		}
		int pos = searchPos(ride);
		openSpace(pos);
		this.ride[pos] = ride;
	}
	//abre espaco para colocar boleia
	//Pre: pos<count
	private void openSpace(int pos) {
		for (int i = count - 1; i >= pos; i--) {
			ride[i + 1] = ride[i];
		}
	}
	//pesquisa um lugar no vetor onde colocar a boleia
	private int searchPos(Ride ride) {
		int pos = count;
		int i = 0;
		BasicDate d = ride.getDate();
		while (i < count && pos == count) {
			BasicDate dCompare = this.ride[i].getDate();
			if (d.getYear() < dCompare.getYear()) {
				pos = i;
			} else if (d.getMonth() < dCompare.getMonth() && d.getYear() == dCompare.getYear()) {
				pos = i;
			} else if (d.getDay() < dCompare.getDay() && d.getMonth() == dCompare.getMonth()
					&& d.getYear() == dCompare.getYear()) {
				pos = i;
			}
			i++;
		}
		return pos;
	}
	//verifica se o vetor esta cheio
	private boolean isFull() {
		return count == ride.length;
	}
	//aumenta o tamanho do vetor
	private void resize() {
		Ride[] temp = new Ride[GROWTH * ride.length];
		for (int i = 0; i < ride.length; i++) {
			temp[i] = ride[i];
		}
		ride = temp;
	}
}
