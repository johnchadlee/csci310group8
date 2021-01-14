package csci310.weatherplanner.weathersource;

public class DailyForecast {
	private final Date date;
	private final String weatherIcon;
	private final int low;
	private final int high;
	private final TempUnit tempUnit;
	
	public DailyForecast(Date date, String weatherIcon, int low, int high, TempUnit tempUnit) {
		this.date = date;
		this.weatherIcon = weatherIcon;
		this.low = low;
		this.high = high;
		this.tempUnit = tempUnit;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getWeatherIcon() {
		return weatherIcon;
	}
	
	public int getLow() {
		return low;
	}
	
	public int getHigh() {
		return high;
	}
	
	public TempUnit getTempUnit() {
		return tempUnit;
	}

	@Override
	public String toString() {
		return "DailyForecast [date=" + date + ", weatherIcon=" + weatherIcon + ", low=" + low + ", high=" + high
				+ ", tempUnit=" + tempUnit + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyForecast other = (DailyForecast) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (high != other.high)
			return false;
		if (low != other.low)
			return false;
		if (tempUnit != other.tempUnit)
			return false;
		if (weatherIcon == null) {
			if (other.weatherIcon != null)
				return false;
		} else if (!weatherIcon.equals(other.weatherIcon))
			return false;
		return true;
	}
}
