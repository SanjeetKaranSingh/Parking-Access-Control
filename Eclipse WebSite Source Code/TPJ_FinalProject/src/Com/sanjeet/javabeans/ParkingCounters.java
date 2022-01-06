package Com.sanjeet.javabeans;

public class ParkingCounters {
	private int VehicalCounter;
	private int parkingLimit;
	private String name;
	public int getVehicalCounter(){
		return VehicalCounter;
	}
	public int getParkingLimit(){
		return parkingLimit;
	}
	
	public ParkingCounters(int vehicalCounter , int parkinglimit){
		this.parkingLimit = parkinglimit;
		this.VehicalCounter = vehicalCounter;		
	}
	public String getUsername(){
		return name;
	}
	
	
}
