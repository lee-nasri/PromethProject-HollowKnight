package item;

import application.Main;

public class Shoes extends Item {
	
	private int speedBonus;
	private int dashBonus;
	
	public Shoes(ShoesType shoes) {
		super(shoes.getImage());
		speedBonus = shoes.getSpeedBonus();
		dashBonus = shoes.getDashBonus();
	}
	
	@Override
	public void applyBonuses() {
		Main.hero.setSpeed(Main.hero.getSpeed() + speedBonus);
		Main.hero.setDashPower(Main.hero.getDashPower() + dashBonus);
	}
	
	@Override
	public void unapplyBonuses() {
		Main.hero.setSpeed(Main.hero.getSpeed() - speedBonus);
		Main.hero.setDashPower(Main.hero.getDashPower() - dashBonus);
	}
	
	@Override
	public String getTypeOfItem() {
		return "Shoes";
	}
}
