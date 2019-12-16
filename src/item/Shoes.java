package item;

import application.Main;
import javafx.scene.image.Image;

public class Shoes extends Item {
	
	private int speedBonus;
	private int dashBonus;
	private Image itemImage;
	private String itemName;;
	
	public Shoes(ShoesType shoes) {
		super(shoes.getImage());
		speedBonus = shoes.getSpeedBonus();
		dashBonus = shoes.getDashBonus();
		itemName = shoes.getName();
		itemImage = new Image(shoes.getImage(), 50, 50, false, true);
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
	
	@Override 
	public String getName() {
		return itemName;
	}
	
	@Override
	public int getBonus() {
		return speedBonus;
	}
	
	@Override
	public Image getImage() {
		return itemImage;
	}
}
