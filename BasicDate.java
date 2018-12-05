public class BasicDate {

	private static final int NUM_FIELDS = 3;

	private int[] rawDate;

	/***
	 * Builds a new raw date object.
	 * 
	 * @param date -- a string of the form N1-N2-N3, where N1,N2,N3 are positive
	 *             numbers representable as integers.
	 */
	public BasicDate(String date) {
		String[] split = date.split("-");
		rawDate = new int[NUM_FIELDS];

		for (int i = 0; i < split.length; i++) {
			rawDate[i] = Integer.parseInt(split[i].trim());
		}
	}

	/*
	 * verificacoes da data
	 */
	public boolean isValid() {
        boolean valid = true;
        if (getYear() < 2018
                || (getDay() > 30 && (getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11))
                || (getDay() > 31 && (getMonth() == 1 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7
                        || getMonth() == 8 || getMonth() == 10 || getMonth() == 12))
                || ((isLeap()) && (getDay() > 29 && getMonth() == 2))
                || ((!isLeap()) && (getDay() > 28 && getMonth() == 2))) {
            valid = false;
        }
        return valid;
    }
    
	private boolean isLeap() {
        boolean Leapness;
        if (getYear() % 4 !=0) {
        Leapness = false;
        }else if (getYear() % 400 ==0) {
            Leapness = true;
        }else if (getYear() % 100 ==0) {
            Leapness = false;
        }else {Leapness = true;}
        return Leapness;
    }

	/**
	 * Returns the year field of this date, assuming the string used in the
	 * constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getYear() {
		return rawDate[2];
	}

	/**
	 * Returns the day field of this date, assuming the string used in the
	 * constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getDay() {
		return rawDate[0];
	}

	/**
	 * Returns the month field of this date, assuming the string used in the
	 * constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getMonth() {
		return rawDate[1];
	}

}
