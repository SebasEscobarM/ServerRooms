package model;

public class Server {
	//Attributes
	private double cacheMemory;
	private int processorsNum;
	private int ramMemory;
	private int diskNum;
	private double[] disksCapacity;
	//Relations
	private ProcessorBrand processorBrand;
	//Methods
	public Server(double cacheMemory, int processorsNum, ProcessorBrand processorBrand,int ramMemory, int diskNum,double[] disksCapacity)
	{
		this.cacheMemory=cacheMemory;
		this.processorsNum=processorsNum;
		this.processorBrand=processorBrand;
		this.ramMemory=ramMemory;
		this.diskNum=diskNum;
		this.disksCapacity=new double[disksCapacity.length];
		for(int i=0;i<this.disksCapacity.length;i++)
		{
			this.disksCapacity[i]=disksCapacity[i];
		}
	}
	public double totalDiskCapacity()
	{
		double total=0;
		for(int i=0;i<disksCapacity.length;i++)
		{
			total+=disksCapacity[i];
		}
		return total;
	}
	public double getCacheMemory()
	{
		return cacheMemory;
	}
	public void setCacheMemory(double cacheMemory)
	{
		this.cacheMemory = cacheMemory;
	}
	public int getProcessorsNum()
	{
		return processorsNum;
	}
 	public void setProcessorsNum(int processorsNum)
	{
		this.processorsNum = processorsNum;
	}
	public int getRamMemory()
	{
		return ramMemory;
	}
	public void setRamMemory(int ramMemory)
	{
		this.ramMemory = ramMemory;
	}
	public int getDiskNum()
	{
		return diskNum;
	}
	public void setDiskNum(int diskNum)
	{
		this.diskNum = diskNum;
	}
	public double[] getDisksCapacity()
	{
		return disksCapacity;
	}
	public void setDisksCapacity(double[] disksCapacity)
	{
		this.disksCapacity = disksCapacity;
	}
	public ProcessorBrand getProcessorBrand()
	{
		return processorBrand;
	}
	public void setProcessorBrand(ProcessorBrand processorBrand)
	{
		this.processorBrand=processorBrand;
	}
}
