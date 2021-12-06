package model;
import java.text.DecimalFormat;
import java.util.*;
public class Tower {
	DecimalFormat dec2=new DecimalFormat("#.00");
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
					case 0: roomNum="A"+(j+1);
							break;
					case 1: roomNum="B"+(j+1);
							break;
					case 2: roomNum="C"+(j+1);
							break;
					case 3: roomNum="D"+(j+1);
							break;
					case 4: roomNum="E"+(j+1);
							break;
					case 5: roomNum="F"+(j+1);
							break;
					case 6: roomNum="G"+(j+1);
							break;
					case 7: roomNum="H"+(j+1);
							break;
				}
				rooms[i][j]=new Room(roomNum,basicValue);
				rooms[i][j].setOn(false);
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
		message+=dec2.format(rent)+".";
		return message;
	}
	public String showServersRoomCapacity(String roomNum)
	{
		String message="The processing capacity of the room to eliminate is:\n";
		boolean exit=false;
		for(int i=0; i<rooms.length && !exit;i++)
		{
			for(int j=0;j<rooms[0].length && !exit;j++)
			{
				if(rooms[i][j]!=null)
				{
					if(rooms[i][j].getRoomNum().equalsIgnoreCase(roomNum))
					{
						message+="Room "+rooms[i][j].getRoomNum()+"\n"+rooms[i][j].toStringServer();
						exit=true;
					}
				}
			}
		}
		return message;
	}
	public String showServersCompanyCapacity(String nit)
	{
		String message="The processing capacity of the room to eliminate is:\n";
		for(int i=0; i<rooms.length;i++)
		{
			for(int j=0;j<rooms[0].length;j++)
			{
				if(rooms[i][j]!=null && rooms[i][j].getRenter()!=null)
				{
					if(rooms[i][j].getRenter().getNit().equalsIgnoreCase(nit))
					{
						message+="Room "+rooms[i][j].getRoomNum()+"\n"+rooms[i][j].toStringServer();
					}
				}
			}
		}
		return message;
	}
	public String cancelRentRoom(String roomNum)
	{
		String message="";
		boolean exit=false;
		for(int i=0; i<rooms.length && !exit;i++)
		{
			for(int j=0;j<rooms[0].length && !exit;j++)
			{
				if(rooms[i][j]!=null)
				{
					if(rooms[i][j].getRoomNum().equalsIgnoreCase(roomNum))
					{
						rooms[i][j].cancelRent();
						message+="Rent cancelled succesfully.";
						exit=true;
					}
				}
			}
		}
		return message;
	}
	public String cancelRentCompany(String nit)
	{
		String message="";
		for(int i=0; i<rooms.length;i++)
		{
			for(int j=0;j<rooms[0].length;j++)
			{
				if(rooms[i][j]!=null && rooms[i][j].getRenter()!=null)
				{
					if(rooms[i][j].getRenter().getNit().equalsIgnoreCase(nit))
					{
						rooms[i][j].cancelRent();
						message+="Room "+rooms[i][j].getRoomNum()+" cancelled succesfully.\n";
					}
				}
			}
		}
		return message;
	}
	public void onAll()
	{
		for(int i=0; i<rooms.length;i++)
		{
			for(int j=0;j<rooms[0].length;j++)
			{
				rooms[i][j].setOn(true);
			}
		}
	}
	public String printOnMap()
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
				if(rooms[i][j].isOn())
				{
					message+="|######";
				}else{
					message+="|      ";
				}
			}
			message+="|\n";
		}
		return message;		
	}
	public void offL()
	{
		for(int i=0; i<rooms.length;i++)
		{
			for(int j=0;j<rooms[0].length;j++)
			{
				if(j==0 || i==0)
					rooms[i][j].setOn(false);
			}
		}
	}
	public void offZ()
	{
		int roomsToOffPerCorridor=(ROOMS_PER_CORRIDOR/(CORRIDORS-2)), restRooms=ROOMS_PER_CORRIDOR%(CORRIDORS-2), roomToTurnOff=ROOMS_PER_CORRIDOR-(restRooms/2);
		for(int i=0; i<rooms.length;i++)
		{
			
			if(i==0 || i==CORRIDORS-1)
				for(int j=0;j<rooms[0].length;j++)
				{
					rooms[i][j].setOn(false);
				}
			else
			{
				for(int k=0;k<roomsToOffPerCorridor && roomToTurnOff>=0;k++)
				{
					rooms[i][roomToTurnOff].setOn(false);
					roomToTurnOff--;
				}
			}

		}
	}
	public void offH()
	{
		for(int i=0; i<rooms.length;i++)
		{
			if((i+1)%2==1)
			{
				for(int j=0;j<rooms[0].length;j++)
				{
					rooms[i][j].setOn(false);
				}
			}
		}
	}
	public void offO()
	{
		for(int i=0; i<rooms.length;i++)
		{
			if(i==0 || i==CORRIDORS-1)
			{
				for(int j=0;j<rooms[0].length;j++)
				{
					rooms[i][j].setOn(false);
				}
			}else
			{
				rooms[i][0].setOn(false);
				rooms[i][ROOMS_PER_CORRIDOR-1].setOn(false);
			}
		}
	}
	public void offM(int colmn)
	{
		for(int i=0;i<CORRIDORS;i++)
		{
			rooms[i][colmn].setOn(false);
		}
	}
	public void offP(int row)
	{
		for(int i=0;i<ROOMS_PER_CORRIDOR-1;i++)
		{
			rooms[row][i].setOn(false);
		}
	}
	public void exitSimulation()
	{
		for(int i=0;i<CORRIDORS-1;i++)
		{
			for(int j=0;j<ROOMS_PER_CORRIDOR-1;j++)
			{
				if(rooms[i][j]!=null)
				{
					if(rooms[i][j].getRenter()==null)
					{
						rooms[i][j].setOn(false);
					}else
					{
						rooms[i][j].setOn(true);
					}
				}
			}
		}
	}
	public String generalRoomsMap()
	{
		String message="", price="";
		int rest=0;
		for(int i=0;i<rooms[0].length+1;i++)
		{
			message+=" __________";
		}
		message+="\n";
		for(int i=0;i<rooms.length;i++)
		{
			for(int j=0;j<rooms[0].length;j++)
			{
				if((i+1)<10 && (j+1)>=10)
					message+="|C:"+(j+1)+"  R:0"+(i+1);
				else if((j+1)<10 && (i+1)>=10)
					message+="|C:0"+(j+1)+"  R:"+(i+1);
				else
					message+="|C:0"+(j+1)+"  R:0"+(i+1);
			}
			message+="|\n";
			for(int j=0;j<rooms[0].length;j++)
			{
				if(rooms[i][j].getRenter()==null)
					message+="|  IsFree  ";
				else
					message+="|  Rented  ";
			}
			message+="|\n";
			for(int j=0;j<rooms[0].length;j++)
			{
				if(i==0 || i==CORRIDORS-1 || j==0 || j==ROOMS_PER_CORRIDOR-1)
					message+="|  Window  ";
				else
					message+="| NoWindow ";
			}
			message+="|\n";
			for(int j=0;j<rooms[0].length;j++)
			{
				price="$";
				price+=dec2.format(rooms[i][j].getFinalRentValue());
				rest=10-price.length();
				message+="|";
				if(rest%2==0)
				{
					for(int k=0;k<(rest/2);k++)
					{
						message+=" ";
					}
					message+=price;
					for(int k=0;k<(rest/2);k++)
					{
						message+=" ";
					}
				}else
				{
					message+=price;
					for(int k=0;k<rest;k++)
					{
						message+=" ";
					}
				}
			}
			message+="|\n";
			for(int j=0;j<rooms[0].length;j++)
			{
				message+="|__________";
			}
			message+="|\n";
		}
		return message;
	}
}
