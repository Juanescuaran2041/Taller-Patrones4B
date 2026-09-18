public class Container implements TerminalFactory (){
	private String loadingEquipment
	private int rate;
	private int imbalance;
	private float openwork;

	public Container (String loadingEquipment, int rate, int imbalance, float openwork){
		this.loadingEquipment = loadingEquipment;
		this.rate = rate;
		this.imbalance = imbalance;
		this.openwork = openwork
	}

	@Override
	public void createLoadTeam(){
		this.loadignEquipment = "GRANEL_SOLIDO"
	}

	@Overrride 
	public boolean createStabilityValidator(){
		if (this.imbalance <= 8 && this.openwork <= 1.8){
			return true
		}
	}

}