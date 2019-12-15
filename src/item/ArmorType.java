package item;

public enum ArmorType {
	
	NormalArmor,
	RareArmor,
	LegendArmor;
	
	public int getHpBonus() {
		switch(this) {
		case NormalArmor:
			return 25;
		case RareArmor:
			return 50;
		case LegendArmor:
			return 100;
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

	public String getName() {
		switch(this) {
		case NormalArmor :
			return "Normal Armor" ;
		case RareArmor :
			return "Rare Armor" ;
		case LegendArmor :
			return "Legend Armor" ;
		default:
			return "unknow Item";
		}
	}
}
