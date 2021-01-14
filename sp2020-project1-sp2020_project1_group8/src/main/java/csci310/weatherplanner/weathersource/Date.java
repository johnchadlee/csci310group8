package csci310.weatherplanner.weathersource;

public class Date {
	private final Month month;
	private final int day;
	
	public Date(Month month, int day) {
		this.month = month;
		this.day = day;
	}
	
	public Month getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}

	@Override
	public String toString() {
		return "Date [month=" + month + ", day=" + day + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		return true;
	}
}
