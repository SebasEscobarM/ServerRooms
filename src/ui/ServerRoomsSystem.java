package ui;
import java.util.*;
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
			System.out.println("3. Enter ON/OFF simulation mode.");
			System.out.println("4. Show ON/OFF map.");
			System.out.println("5. Show general map.");
			System.out.println("9. Exit.");
			menu=reader.nextInt();
			reader.nextLine();
			switch(menu)
			{
				case 1:	rentRoom();
						break;
				case 2:	cancelRent();
						break;
				case 3:	onOffSimulation();
						break;
				case 4:	System.out.println(twr1.printOnMap());
						break;
				case 5: System.out.println(twr1.generalRoomsMap());
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
		String message="";
		int selection=0, confirm=0;
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
			System.out.print(twr1.showServersRoomCapacity(roomNum));
			System.out.println("Select:");
			System.out.println("1. Confirm.");
			System.out.println("2. Cancel.");
			confirm=reader.nextInt();
			reader.nextLine();
			if(confirm==1)
				message=twr1.cancelRentRoom(roomNum);
			else if(confirm==2)
				message="Action cancelled.";
			else
				message="Select a correct option.";
		}else if(selection==2)
		{
			System.out.println("Enter the NIT of the company:");
			nit=reader.nextLine();
			System.out.print(twr1.showServersCompanyCapacity(nit));
			System.out.println("Select:");
			System.out.println("1. Confirm.");
			System.out.println("2. Cancel.");
			confirm=reader.nextInt();
			reader.nextLine();
			if(confirm==1)
				message=twr1.cancelRentCompany(nit);
			else if(confirm==2)
				message="Action cancelled.";
			else
				message="Select a correct option.";
		}
		System.out.println(message);
	}
	public static void onOffSimulation()
	{
		boolean exit=false;
		String offLetter="";
		int colmn=-1, row=-1;
		twr1.onAll();
		do{
			System.out.println(twr1.printOnMap());
			System.out.println("Select a letter for turn off:");
			System.out.println("| L | Z | H | O | M | P |");
			System.out.println("Enter 'N' to turn on all the rooms.");
			System.out.println("Enter 'E' for exit of the simulation mode.");
			offLetter=reader.nextLine();
			switch(offLetter)
			{
				case "L":
				case "l":twr1.offL();
						 break;
				case "Z":
				case "z":twr1.offZ();
						 break;
				case "H":
				case "h":twr1.offH();
						 break;
				case "O":
				case "o":twr1.offO();
						 break;
				case "M":
				case "m":System.out.println("Enter the column num to turn off:");
						 colmn=reader.nextInt();
						 reader.nextLine();
						 if(colmn>=1 && colmn<=twr1.ROOMS_PER_CORRIDOR)
							 twr1.offM(colmn-1);
						 else
							 System.out.println("Select a valid column.");
						 break;
				case "P":
				case "p":System.out.println("Enter the corridor num to turn off:");
						 row=reader.nextInt();
						 reader.nextLine();
						 if(row>=1 && row<=twr1.CORRIDORS)
							 twr1.offP(row-1);
						 else
							 System.out.println("Select a valid corridor.");
						 break;
				case "N":
				case "n":twr1.onAll();
						 break;
				case "E":
				case "e":twr1.exitSimulation();
						 exit=true;
						 break;
			}
		}while(!exit);
	}
}
