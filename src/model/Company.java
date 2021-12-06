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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	
}