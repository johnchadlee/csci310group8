package csci310.weatherplanner.weathersource;

public class MonthTemp {
	private final Month month;
	private final int avgLow;
	private final int avgHigh;
	
	public MonthTemp(Month month, int avgLow, int avgHigh) {
		this.month = month;
		this.avgLow = avgLow;
		this.avgHigh = avgHigh;
	}
	
	public Month getMonth() {
		return month;
	}
	
	public int getAvgLow() {
		return avgLow;
	}
	
	public int getAvgHigh() {
		return avgHigh;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonthTemp other = (MonthTemp) obj;
		if (avgHigh != other.avgHigh)
			return false;
		if (avgLow != other.avgLow)
			return false;
		if (month != other.month)
			return false;
		return true;
	}
}
