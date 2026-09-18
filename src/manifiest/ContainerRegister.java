public class ContainerRegister extends ManifiestRegiser{

	private String initials;
	private String registration;
	private ChargeUnity chargeUnity;
	private double charge;
	private String seal;

	public ContainerRegister (String initials, String registration, double charge, String seal){
		this.initials = initials;
		this.registration = registration;
		this.charge = charge;
		this.seal = seal;

	}

	public ChargeUnity createUnity(String line){
		if (line != ChargeUnity.FEET){
			throw new RuntimeException ("Charge unity must be feet")
		}
		this.line = line

	}

	public double grossWeight(double tare){
		double total = this.charge + tare;
		return total;
	}

	

}