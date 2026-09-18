public class ContainerRegister extends ManifiestRegiser{

	private String initials;
	private String product;
	private ChargeUnity chargeUnity;
	private double charge;
	private double humidityPorcentage;

	public ContainerRegister (String initials, String product, double humidityPorcentage, double charge){
		this.initials = initials;
		this.product = product;
		this.humidityPorcentage = humidityPorcentage;
		this.charge = charge;
	}

	public ChargeUnity createUnity(String line){
		if (line != ChargeUnity.TONS){
			throw new RuntimeException ("Charge unity must be TONS")
		}
		this.line = line

	}

	public double grossWeight(){
		double total = (1 + (humidityPorcentage/100)) + quantity;

		return total;
	}

}