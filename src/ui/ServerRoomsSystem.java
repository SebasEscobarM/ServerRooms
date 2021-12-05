package ui;
import java.util.*;
//import java.io.*;
//import java.net.*;
import model.*;
public class ServerRoomsSystem {
	public static Scanner reader;
	//Constants
	public static String UNIVERSITY_NAME="ICESI";
	public static String UNIVERSITY_NIT="890.316.745-5";
	//Relations
	public static Tower twr1;
	//Methods
	public static void main(String[] args)
	{
		reader=new Scanner(System.in);
		System.out.println("Enter the basic value for all the rooms:");
		double basicValue=reader.nextDouble();
		reader.nextLine();
		twr1=new Tower(basicValue);
		showMenu();
	}
	public static void showMenu()
	{
		boolean exit=false;
		int menu=0;
		do{
			System.out.println("Select an option from the menu:");
			System.out.println("1. Rent a room.");
			System.out.println("2. Calcel a rent.");
			System.out.println("9. Exit.");
			menu=reader.nextInt();
			reader.nextLine();
			switch(menu)
			{
				case 1:	rentRoom();
						break;
				case 2:
						break;
				case 3:
						break;
				case 9:	exit=true;
						break;
				default:
			}
		}while(!exit);
	}
	public static void rentRoom()
	{
		System.out.println(twr1.printAvailableRooms());
		int renterType=0;
		String name, nit, projectNum="", roomChoose, message="";
		System.out.println("Select the room that is going to be rented:");
		System.out.println("Enter your selection in the format ROWS, COLUMNS:");
		roomChoose=reader.nextLine();
		System.out.println("Select the entity that is going to be the renter of the room:");
		System.out.println("1. Company.");
		System.out.println("2. Investigation project.");
		renterType=reader.nextInt();
		reader.nextLine();
		if(renterType==1)
		{
			System.out.println("Enter the company name:");
			name=reader.nextLine();
			System.out.println("Enter the company NIT:");
			nit=reader.nextLine();
			message=twr1.rentRoom(roomChoose,name,nit);
			addServers(roomChoose);
			System.out.println(twr1.calculateFinalRent(roomChoose));
		}else if(renterType==2)
		{
			name=UNIVERSITY_NAME;
			nit=UNIVERSITY_NIT;
			System.out.println("Enter the register number of the project:");
			projectNum=reader.nextLine();
			message=twr1.rentRoom(roomChoose,name,nit,projectNum);
			addServers(roomChoose);
			System.out.println(twr1.calculateFinalRent(roomChoose));
		}else
		{
			message="Error. Select a correct renter option.";
		}
		System.out.println(message);
	}
	public static void addServers(String roomChoose)
	{
		int serversNum, processorsNum, ramMemory, diskNum, pos;
		double cacheMemory;
		double[] disksCapacity;
		ProcessorBrand processorBrand=null;
		System.out.println("Enter the number of servers to add to the room:");
		serversNum=reader.nextInt();
		reader.nextLine();
		for(int i=0;i<serversNum;i++)
		{
			System.out.println("Server #"+(i+1));
			System.out.println("Enter the cache memory of the server in MB:");
			cacheMemory=reader.nextDouble();
			reader.nextLine();
			System.out.println("Enter the number of processors of the server:");
			processorsNum=reader.nextInt();
			reader.nextLine();
			do{
				System.out.println("Select the processor brand of the server. Select:");
				ProcessorBrand brnds[]= ProcessorBrand.values();
				for(int j=0;j<brnds.length;j++)
				{
					System.out.println((j+1)+". "+brnds[j]+".");
				}
				pos=reader.nextInt();
				reader.nextLine();
				processorBrand=brnds[pos-1];
			}while(processorBrand==null);
			System.out.println("Enter the RAM memory capacity of the server in GB:");
			ramMemory=reader.nextInt();
			reader.nextLine();
			System.out.println("Enter the number of data disks of the server:");
			diskNum=reader.nextInt();
			reader.nextLine();
			disksCapacity=new double[diskNum];
			for(int j=0;j<disksCapacity.length;j++)
			{
				System.out.println("Enter the capacity of the disk #"+(j+1)+":");
				disksCapacity[j]=reader.nextDouble();
				reader.nextLine();
			}
			twr1.addServer(roomChoose, cacheMemory, processorsNum, processorBrand, ramMemory, diskNum, disksCapacity);
		}
		System.out.println("Servers added succesfully.");
	}
	public static void cancelRent()
	{
		int selection=0;
		String roomNum="", nit="";
		System.out.println("Select the cancel option that is going to be processed:");
		System.out.println("1. Cancel an specific room.");
		System.out.println("2. Cancel all rooms of a company.");
		selection=reader.nextInt();
		reader.nextLine();
		if(selection==1)
		{
			System.out.println("Enter the number of the room to eliminate:");
			roomNum=reader.nextLine();
			twr1.cancelRentRoom(roomNum);
		}else if(selection==2)
		{
			System.out.println("Enter the NIT of the company:");
			nit=reader.nextLine();
			twr1.cancelRentCompany(nit);
		}
	}
}
