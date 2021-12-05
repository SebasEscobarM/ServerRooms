package model;

public class Company {
	//Attributes
	private String name;
	private String nit;
	private String projectNum;
	//Methods
	public Company(String name, String nit)
	{
		this.name=name;
		this.nit=nit;
		this.projectNum="";
	}
	public Company(String name, String nit, String projectName)
	{
		this.name=name;
		this.nit=nit;
		this.projectNum=projectName;
	}
}