package application;

public enum Music {
	
	Proof_of_a_Hero,
	Friend_shitai,
	City_of_Tears,
	Guren_no_Yumiya,
	Get_Goal;
	
	public String getPath() {
		switch(this) {
		case Proof_of_a_Hero:
			return ClassLoader.getSystemResource("music/Proof_of_a_Hero.mp3").toString();
		case Friend_shitai:
			return ClassLoader.getSystemResource("music/Friend_shitai.mp3").toString();
		case City_of_Tears:
			return ClassLoader.getSystemResource("music/City_of_Tears.mp3").toString();
		case Guren_no_Yumiya:
			return ClassLoader.getSystemResource("music/Guren_no_Yumiya.mp3").toString();
		case Get_Goal:
			return ClassLoader.getSystemResource("music/Get_Goal.mp3").toString();
		default:
			return "";
		}
	}
	
	public double getVolume() {
		switch(this) {
		case Proof_of_a_Hero:
			return 0.6;
		case Friend_shitai:
			return 0.3;
		case City_of_Tears:
			return 0.6;
		case Guren_no_Yumiya:
			return 0.3;
		case Get_Goal:
			return 0.15;
		default:
			return 0;
		}
	}
	
}
