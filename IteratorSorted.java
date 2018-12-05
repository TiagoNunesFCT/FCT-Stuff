/*
 * iterador de boleias
 */
public class IteratorSorted {
	private int count;
	private int current;
	private Ride[] rides;

	public IteratorSorted(Ride[] iterateThis, int count) {
		current = 0;
		this.rides = new Ride[count];
		this.count = 0;
		for (int i = 0; i < count; i++) {
			int pos = binSearchPos(iterateThis[i]);
			insertAt(iterateThis[i], pos);
		}
		current = 0;
	}

	public boolean hasNext() {
		return current < count;
	}

	// Pre: hasNext()
	public Ride next() {
		return rides[current++];
	}

	private void createGap(int pos) {
		for (int i = count - 1; i >= pos; i--)
			rides[i + 1] = rides[i];
	}

	private void insertAt(Ride ride, int pos) {
		createGap(pos);
		rides[pos] = ride;
		count++;
	}

	private int binSearchPos(Ride ride) {
		int pos = -1;
		if (count == 0) {
			pos = 0;
		} else {
			int lo = 0;
			int hi = count - 1;
			BasicDate d1 = new BasicDate(ride.getDate());
			while (pos == -1 && lo <= hi) {
				int mid = (lo + hi) / 2;
				BasicDate d2 = new BasicDate(rides[mid].getDate());
				if (d2.getYear() == (d1.getYear())) {
					//System.out.println("Year of this date is equal");
					if (d2.getMonth() == (d1.getMonth())) {
						//System.out.println("Month of this date is equal");
						if (d2.getDay() == (d1.getDay())) {
							//System.out.println("Day of this date is equal");
							pos = mid;
						} else if (d1.getDay() < d2.getDay()) {
							//System.out.println("Day of this date is lesser");
							hi = mid - 1;
						} else
							//System.out.println("Day of this date is greater");
						lo = mid + 1;
					} else if (d1.getMonth() < d2.getMonth()) {
						//System.out.println("Month of this date is lesser");
						hi = mid - 1;
					} else
						//System.out.println("Month of this date is greater");
					lo = mid + 1;
				} else if (d1.getYear() < d2.getYear()) {
					//System.out.println("Year of this date is lesser");
					hi = mid - 1;
				} else
					//System.out.println("Year of this date is greater");
				lo = mid + 1;
			}
			if (pos < 0)
				pos = hi + 1;
		}
		return pos;
	}
}