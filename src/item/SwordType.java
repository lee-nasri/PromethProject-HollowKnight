package item;

public enum SwordType {
	NormalSword,
	GoldenSword;
	
	public int getAttackDamage() {
		switch(this) {
		case NormalSword:
			return 10;
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
		case GoldenSword:
			return ClassLoader.getSystemResource("ItemImage/GoldenSword.png").toString();
		default:
			return "";
		}
	}
	
}
