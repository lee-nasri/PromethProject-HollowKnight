package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;
import item.Armor;
import item.ArmorType;
import item.Shoes;
import item.ShoesType;
import item.Sword;
import item.SwordType;
import object.MoveableEnemy;

public class Vengefly extends MoveableEnemy {
	
	private double distance;
	private double distanceX;
	private double distanceY;
	
	private static final double vision = 500;
	
	public Vengefly(double x, double y) {
		super(x, y, 130, 80);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Vengefly.png").toString(), 130, 80, false, true)));
		friction = 0.05;
		speed = 7;
		maxHp = 40;
		attackDamage = 20;
	}
	
	public void setMovement() {
		distanceX = Main.hero.getCenterX() - getCenterX();
		distanceY = Main.hero.getCenterY() - getCenterY();
		distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		switch(cerrentState) {
		case "idle":
			dx -= dx*friction;
			dy -= dy*friction;
			if (distance < vision) {
				cerrentState = "normal";
			}
			break;
		case "normal":
			//fly straight to the hero
			dx += (speed*(distanceX/distance) - dx)*friction;
			dy += (speed*(distanceY/distance) - dy)*friction;
			turn(Main.hero.getCenterX() < getCenterX());
			break;
		}
	}

	protected void reset() {
		super.reset();
		cerrentState = "idle";
	}
	
	public void die() {
		super.die();
		dropItem(new Sword(SwordType.NormalSword), 0.2);
		dropItem(new Armor(ArmorType.NormalArmor), 0.2);
		dropItem(new Shoes(ShoesType.NormalShoes), 0.2);
	}
}