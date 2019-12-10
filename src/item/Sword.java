package item;

import application.Main;

public class Sword extends Item {
	
	private int attackDamageBonus;
	
	public Sword(SwordType sword) {
		super(sword.getImage());
		attackDamageBonus = sword.getAttackDamage();
	}
	
	@Override
	public void applyBonuses() {
		Main.hero.setAttackDamage(Main.hero.getAttackDamage() + attackDamageBonus );
	}
	
	@Override
	public void unapplyBonuses() {
		Main.hero.setAttackDamage(Main.hero.getAttackDamage() - attackDamageBonus );
	}
	
	@Override
	public String getTypeOfItem() {
		return "Sword";
	}
	
}
