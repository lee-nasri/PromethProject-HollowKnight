package item;

import application.Main;

public class Armor extends Item {
	
	private int maxHpBonus;
	
	public Armor(ArmorType armor) {
		super(armor.getImage());
		maxHpBonus = armor.getHpBonus();
	}

	@Override
	public void applyBonuses() {
		Main.hero.setMaxHp(Main.hero.getMaxHp() + maxHpBonus);
	}
	
	@Override
	public void unapplyBonuses() {
		Main.hero.setMaxHp(Main.hero.getMaxHp() - maxHpBonus );
	}
	
	@Override
	public String getTypeOfItem() {
		return "Armor";
	}

}
