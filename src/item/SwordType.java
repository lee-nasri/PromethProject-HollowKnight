package item;

public enum SwordType {
	NormalSword,
	RareSword,
	GoldenSword;
	
	public int getAttackDamage() {
		switch(this) {
		case NormalSword:
			return 5;
		case RareSword:
			return 10;
		case GoldenSword:
			return 200;
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
	
}
