package item;

import application.Main;
import javafx.scene.image.Image;

public class Sword extends Item {
	
	private int attackDamageBonus;
	private Image itemImage;
	private String itemName;
	
	public Sword(SwordType sword) {
		super(sword.getImage());
		attackDamageBonus = sword.getAttackDamage();
		itemImage = new Image(sword.getImage(), 50, 50, false, true);
		itemName = sword.getName();
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
	
	@Override
	public String getName() {
		return itemName;
	}
	
	@Override
	public int getBonus() {
		return attackDamageBonus;
	}
	
	@Override
	public Image getImage() {
		return itemImage;
	}
	
}
