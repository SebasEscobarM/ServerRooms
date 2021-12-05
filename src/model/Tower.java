package model;
import java.util.*;
//import java.io.*;
//import java.net.*;
public class Tower {
	//Constants
	public final int CORRIDORS=8;
	public final int ROOMS_PER_CORRIDOR=50;
	public final double WINDOW_DISCOUNT=0.10;
	public final double SECOND_TO_SIXTH_CORRIDOR_DISCOUNT=0.15;
	public final double SEVENTH_CORRIDOR_DISCOUNT=0.25;
	//Relations
	private Room[][] rooms;
	//Methods
	public Tower(double basicValue)
	{
		rooms=new Room[CORRIDORS][ROOMS_PER_CORRIDOR];
		initializeRooms(basicValue);
	}
	public void initializeRooms(double basicValue)
	{
		for(int i=0;i<rooms.length;i++)
		{
			for(int j=0;j<rooms[0].length;j++)
			{
				String roomNum="";
				switch(i)
				{
					case 0: roomNum="A"+j;
							break;
					case 1: roomNum="B"+j;
							break;
					case 2: roomNum="C"+j;
							break;
					case 3: roomNum="D"+j;
							break;
					case 4: roomNum="E"+j;
							break;
					case 5: roomNum="F"+j;
							break;
					case 6: roomNum="G"+j;
							break;
					case 7: roomNum="H"+j;
							break;
				}
				rooms[i][j]=new Room(roomNum,basicValue);
			}
		}
	}
	public String printAvailableRooms()
	{
		String message="";
		for(int i=0;i<rooms[0].length+1;i++)
		{
			if(i==0)
			{
				message+="|------";
			}else if(i<10)
			{
				message+="|--0"+i+"--";
			}else
			{
				message+="|--"+i+"--";
			}
		}
		message+="|\n";
		for(int i=0;i<rooms.length;i++)
		{
			if(i+1<10)
			{
				message+="|--0"+(i+1)+"--";
			}else
			{
				message+="|--"+(i+1)+"--";
			}
			for(int j=0;j<rooms[0].length;j++)
			{
				if(rooms[i][j].getRenter()!=null)
				{
					message+="|Rented";
				}else{
					message+="|IsFree";
				}
			}
			message+="|\n";
		}
		return message;
	}
	public String rentRoom(String roomChoose, String name, String nit)
	{
		String message;
		int colmn, row;
		StringTokenizer positions=new StringTokenizer(roomChoose,",");
		row=Integer.parseInt(positions.nextToken());
		colmn=Integer.parseInt(positions.nextToken());
		if(rooms[row-1][colmn-1].getRenter()==null)
		{
			rooms[row-1][colmn-1].rent(name,nit);
			message="Room  rented succesfully.";
		}
		else
			message="Error. Choose a free room for rent.";
		return message;
	}
	public String rentRoom(String roomChoose, String name, String nit, String projectName)
	{
		String message;
		int colmn, row;
		StringTokenizer positions=new StringTokenizer(roomChoose,",");
		row=Integer.parseInt(positions.nextToken());
		colmn=Integer.parseInt(positions.nextToken());
		if(rooms[row-1][colmn-1].getRenter()==null)
		{
			rooms[row-1][colmn-1].rent(name,nit,projectName);
			message="Room rented succesfully.";
		}
		else
			message="Error. Choose a free room for rent.";
		return message;
	}
	public void addServer(String roomChoose, double cacheMemory, int processorsNum, ProcessorBrand processorBrand, int ramMemory, int diskNum, double[] disksCapacity)
	{
		int colmn, row;
		StringTokenizer positions=new StringTokenizer(roomChoose,",");
		row=Integer.parseInt(positions.nextToken());
		colmn=Integer.parseInt(positions.nextToken());
		rooms[row-1][colmn-1].addServer(cacheMemory, processorsNum, processorBrand, ramMemory, diskNum, disksCapacity);
	}
	public String calculateFinalRent(String roomChoose)
	{
		double ubicationDiscount=1, rent;
		int colmn, row;
		StringTokenizer positions=new StringTokenizer(roomChoose,",");
		row=Integer.parseInt(positions.nextToken());
		colmn=Integer.parseInt(positions.nextToken());
		String message="Your room was named with the number "+rooms[row-1][colmn-1].getRoomNum()+" and rent value is $";
		rent=rooms[row-1][colmn-1].getBasicValue();
		if((row-1)==0 || (row-1)==CORRIDORS-1 || (colmn-1)==0 || (colmn-1)==ROOMS_PER_CORRIDOR-1)
			ubicationDiscount-=WINDOW_DISCOUNT;
		if((row-1)>=1 && (row-1)<=5)
			ubicationDiscount-=SECOND_TO_SIXTH_CORRIDOR_DISCOUNT;
		if((row-1)==6)
			ubicationDiscount-=SEVENTH_CORRIDOR_DISCOUNT;
		rent*=ubicationDiscount;
		rent=rooms[row-1][colmn-1].calculateFinalValue(rent);
		message+=rent+".";
		return message;
	}
}
