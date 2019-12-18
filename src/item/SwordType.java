package item;

public enum SwordType {
	NormalSword,
	RareSword,
	GoldenSword;
	
	public int getAttackDamage() {
		switch(this) {
		case NormalSword:
			return 10;
		case RareSword:
			return 15;
		case GoldenSword:
			return 20;
		default:
			return 0;
		}
	}
	
	public String getImage() {
		switch(this) {
		case NormalSword:
			return ClassLoader.getSystemResource("ItemImage/NormalSword.png").toString();
		case RareSword:
			return ClassLoader.getSystemResource("ItemImage/RareSword.png").toString();
		case GoldenSword:
			return ClassLoader.getSystemResource("ItemImage/GoldenSword.png").toString();
		default:
			return "";
		}
	}
	
	public String getName() {
		switch(this) {
		case NormalSword :
			return "Normal Sword" ;
		case RareSword :
			return "Rare Sword" ;
		case GoldenSword :
			return "Golden Sword" ;
		default:
			return "unknow Item";
		}
	}
	
}
