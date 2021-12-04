package model;

public class Tower {
	//Constants
	public final int CORRIDORS=8;
	public final int ROOMS_PER_CORRIDOR=50;
	//Relations
	private Room[][] rooms;
	//Methods
	public Tower()
	{
		rooms=new Room[CORRIDORS][ROOMS_PER_CORRIDOR];
	}
}
