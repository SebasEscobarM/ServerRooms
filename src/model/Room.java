package model;
import java.util.*;
public class Room {
	//Constants
	public final double SERVERS_ADDITIONAL=0.15;
	//Attributes
	private String roomNum;
	private double basicValue;
	private double finalRentValue;
	private Date rentDate;
	private boolean on;
	//Relations
	private List<Server> roomServers;
	private Company renter;
	//Methods
	public Room(String roomNum,double basicValue)
	{
		roomServers=new ArrayList<Server>();
		renter=null;
		this.roomNum=roomNum;
		this.basicValue=basicValue;
		this.finalRentValue=basicValue;
	}
	public void rent(String name, String nit)
	{
		renter=new Company(name,nit);
		rentDate=new Date();
		setOn(true);
	}
	public void rent(String name, String nit, String projectNum)
	{
		renter=new Company(name,nit, projectNum);
		rentDate=new Date();
		setOn(true);
	}
	public void addServer(double cacheMemory, int processorsNum, ProcessorBrand processorBrand, int ramMemory, int diskNum, double[] disksCapacity)
	{
		roomServers.add(new Server(cacheMemory, processorsNum, processorBrand, ramMemory, diskNum, disksCapacity));
	}
	public double calculateFinalValue(double rent)
	{
		double serversAdditional=1;
		if(roomServers.size()<4)
			serversAdditional+=SERVERS_ADDITIONAL;
		rent*=serversAdditional;
		setFinalRentValue(rent);
		return rent;
	}
	public String toStringServer()
	{
		String message="";
		for(int i=0;i<roomServers.size();i++)
		{
			message+="Server #"+(i+1)+"\nTotal disk capacity: "+roomServers.get(i).totalDiskCapacity()+"Tb.\nRAM memory: "+roomServers.get(i).getRamMemory()+"GB.\n";
		}
		return message;
	}
	public void cancelRent()
	{
		setRenter(null);
		roomServers.clear();
		setFinalRentValue(basicValue);
		rentDate=null;
		setOn(false);
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public double getBasicValue() {
		return basicValue;
	}
	public void setBasicValue(double basicValue) {
		this.basicValue = basicValue;
	}
	public double getFinalRentValue() {
		return finalRentValue;
	}
	public void setFinalRentValue(double finalRentValue) {
		this.finalRentValue = finalRentValue;
	}
	public List<Server> getRoomServers() {
		return roomServers;
	}
	public void setRoomServers(List<Server> roomServers) {
		this.roomServers = roomServers;
	}
	public Company getRenter() {
		return renter;
	}
	public void setRenter(Company renter) {
		this.renter = renter;
	}
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	
}
