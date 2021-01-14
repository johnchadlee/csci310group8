package csci310.weatherplanner.weathersource;

public enum Month {
	Jan(1),
	Feb(2),
	Mar(3),
	Apr(4), 
	May(5),
	Jun(6),
	Jul(7),
	Aug(8),
	Sep(9),
	Oct(10),
	Nov(11),
	Dec(12);
	
	private final int dateNum;
	
	Month(int dateNum){
		this.dateNum = dateNum;
	}
	
	public int getDateNum() {
		return dateNum;
	}
}
