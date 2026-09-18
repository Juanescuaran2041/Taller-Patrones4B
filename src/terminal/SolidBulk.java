public class SolidBulk implements TerminalFactory {
	private String loadingEquipment
	private int rate;
	private int imbalance;
	private float openwork;
	private Map<String, Container> unitiesList; 

	public Container (String loadingEquipment, int rate, int imbalance, float openwork){
		this.loadingEquipment = loadingEquipment;
		this.rate = rate;
		this.imbalance = imbalance;
		this.openwork = openwork;
	}

	@Override
	public void createLoadTeam(){
		this.loadignEquipment = "GRANEL_SOLIDO"
	}

	@Overrride 
	public boolean createStabilityValidator(){
		if (this.imbalance <= 5 && this.openwork <= 2,10){
			return True;
		}else{
			return False;
		}
	}

	@Override
	public boolean createStabilityValidator(){
		if (!unitiesList.isEmpty()){
			return True
		}
		return False
	}  
}