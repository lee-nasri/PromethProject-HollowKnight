package item;

import application.Main;

public class Shoes extends Item {
	
	private int speedBonus ;
	
	public Shoes(ShoesType shoes) {
		super(shoes.getImage());
		speedBonus = shoes.getSpeedBonus();
	}
	
	@Override
	public void applyBonuses() {
		Main.hero.setSpeed(Main.hero.getSpeed() + speedBonus);
	}
	
	@Override
	public void unapplyBonuses() {
		Main.hero.setSpeed(Main.hero.getSpeed() - speedBonus);
	}
	
	@Override
	public String getTypeOfItem() {
		return "Shoes";
	}
}
