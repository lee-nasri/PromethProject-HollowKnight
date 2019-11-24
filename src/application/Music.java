package application;

public enum Music {
	
	Friend_shitai,
	Guren_no_Yumiya,
	Get_Goal;
	
	public String getPath() {
		switch(this) {
		case Friend_shitai:
			return ClassLoader.getSystemResource("music/Friend_shitai.mp3").toString();
		case Guren_no_Yumiya:
			return ClassLoader.getSystemResource("music/Guren_no_Yumiya.mp3").toString();
		case Get_Goal:
			return ClassLoader.getSystemResource("music/Get_Goal.mp3").toString();
		default:
			return "";
		}
	}
}
