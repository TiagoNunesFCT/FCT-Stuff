
public class BasicDate {

	private static final int NUM_FIELDS = 3;

	private int[] rawDate;

	/***
	 * Builds a new raw date object. 
	 * @param date -- a string of the form N1-N2-N3, 
	 * where N1,N2,N3 are positive numbers representable as integers.
	 */
	public BasicDate(String date) {
		String[] split = date.split("-");
		rawDate = new int[NUM_FIELDS];

		for (int i = 0; i < split.length; i++) {
			rawDate[i] = Integer.parseInt(split[i].trim());
		}

	}
	
	/*
	 * Date validations
	 */
	public boolean isValid(BasicDate date) {
		boolean valid;
		if (date.getYear()<2018 ||
				(date.getDay()>30 && (date.getMonth()==4||date.getMonth()==6||date.getMonth()==9||date.getMonth()==11))||
				(date.getDay()>31 && (date.getMonth()==1||date.getMonth()==3||date.getMonth()==5||date.getMonth()==7||date.getMonth()==8||date.getMonth()==10||date.getMonth()==12))||
				(((date.getYear()%4) ==0) && (date.getDay()>29 && date.getMonth()==2))||
				(((date.getYear()%4) !=0) && (date.getDay()>28 && date.getMonth()==2))) {
			valid = false;
		}else valid = true;
		return valid;
	}

	/**
	 * Returns the year field of this date, assuming the string used
	 * in the constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getYear() {
		return rawDate[2];
	}
	
	/**
	 * Returns the day field of this date, assuming the string used
	 * in the constructor was a valid date (i.e., isValid() ).
	 *  
	 */
	public int getDay() {
		return rawDate[0];
	}
	
	/**
	 * Returns the month field of this date, assuming the string used
	 * in the constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getMonth() {
		return rawDate[1];
	}
	


}
