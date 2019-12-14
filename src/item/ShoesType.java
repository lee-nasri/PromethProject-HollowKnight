package item;

public enum ShoesType {
	NormalShoes,
	RareShoes,
	GoldenShoes;
	
	public int getSpeedBonus() {
		switch(this) {
		case NormalShoes: 
			return 2 ;
		case RareShoes:
			return 4 ;
		case GoldenShoes :
			return 6 ;
		default:
			return 0;
		}	
	}
	
	public int getDashBonus() {
		switch(this) {
		case NormalShoes: 
			return 5 ;
		case RareShoes:
			return 8 ;
		case GoldenShoes :
			return 15 ;
		default:
			return 0;
		}	
	}
	
	public String getImage() {
		switch(this) {
		case NormalShoes:
			return ClassLoader.getSystemResource("ItemImage/NormalShoes.png").toString();
		case RareShoes:
			return ClassLoader.getSystemResource("ItemImage/RareShoes.png").toString();
		case GoldenShoes:
			return ClassLoader.getSystemResource("ItemImage/GoldenShoes.png").toString();
		default:
			return "";
		}
	}
	
	
	
	
}
