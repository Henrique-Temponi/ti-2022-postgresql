public class Computer {
	
	private int id;
	private String cpu;
	private String gpu;
	private int ram;
	private int hdd;
	
	public Computer() {
		this.id = -1;
		this.cpu = "";
		this.gpu = "";
		this.ram = 0;
		this.hdd = 0;
	}
	
	public Computer(int id, String cpu, String gpu, int ram, int hdd) {
		this.id = id;
		this.cpu = cpu;
		this.gpu = gpu;
		this.ram = ram;
		this.hdd = hdd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getHdd() {
		return hdd;
	}

	public void setHdd(int hdd) {
		this.hdd = hdd;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", cpu=" + cpu + ", gpu=" + gpu + ", ram=" + ram + ", hdd=" + hdd + "]";
	}
	
}