public class LiquidRegister extends ManifiestRegiser {

	private String initials;
	private String product;
	private ChargeUnity chargeUnity;
	private double charge;
	private double density

	public ContainerRegister (String initials, String product, double charge, String density){
		this.initials = initials;
		this.product = product;
		this.charge = charge;
		this.density = density;

	}

	public ChargeUnity createUnity(String line){
		if (line != ChargeUnity.LITERS){
			throw new RuntimeException ("Charge unity must be LITERS")
		}
		this.line = line

	}

	public double grossWeight(double tare){
		double total = this.charge * density/1000 ;
		return total;
	}


}