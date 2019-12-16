package item;

import application.Main;
import javafx.scene.image.Image;

public class Armor extends Item {
	
	private int maxHpBonus;
	private Image itemImage;
	private String itemName;
	
	public Armor(ArmorType armor) {
		super(armor.getImage());
		maxHpBonus = armor.getHpBonus();
		itemName = armor.getName();
		itemImage = new Image(armor.getImage(), 50, 50, false, true);
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
	
	@Override
	public String getName() {
		return itemName;
	}
	
	@Override
	public int getBonus() {
		return maxHpBonus;
	}
	
	@Override
	public Image getImage() {
		return itemImage;
	}

}
