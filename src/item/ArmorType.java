package item;

public enum ArmorType {
	
	NormalArmor,
	RareArmor,
	LegendArmor;
	
	public int getHpBonus() {
		switch(this) {
		case NormalArmor:
			return 15;
		case RareArmor:
			return 30;
		case LegendArmor:
			return 60;
		default:
			return 0;
		}
	}
	
	public String getImage() {
		switch(this) {
		case NormalArmor:
			return ClassLoader.getSystemResource("ItemImage/NormalArmor.png").toString();
		case RareArmor:
			return ClassLoader.getSystemResource("ItemImage/RareArmor.png").toString();
		case LegendArmor:
			return ClassLoader.getSystemResource("ItemImage/LegendArmor.png").toString();
		default:
			return "";
		}
	}

}
