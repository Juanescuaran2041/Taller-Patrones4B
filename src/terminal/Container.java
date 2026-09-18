public class Container implements TerminalFactory (){
	private String loadingEquipment
	private int rate;
	private int imbalance;
	private float openwork;
	private boolean loadingKnwoledge;
	private boolean humidityCertificate;

	public Container (String loadingEquipment, int rate, int imbalance, float openwork, boolean loadingKnowledge, boolean humidtyCertificate){
		this.loadingEquipment = loadingEquipment;
		this.rate = rate;
		this.imbalance = imbalance;
		this.openwork = openwork;
		this.loadingKnowledge = loadingKnowledge;
		this.loadingEquipment = loadingEquipment;
	}

	@Override
	public void createLoadTeam(){
		this.loadignEquipment = "CONTENEDORES"
	}

	@Overrride 
	public boolean createStabilityValidator(){
		if (this.imbalance <= 8 && this.openwork <= 1.8){
			return True;
		}else{
			return False;
		}
	}

	@Override
	public boolean createStabilityValidator(){
		if (this.loadingKnowledge == True && this.loadingEquipment == True ){
			return True;
		}
		return False
	}    

}