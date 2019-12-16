package monster;

import application.Main;
import application.Music;
import item.Shoes;
import item.ShoesType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Boss;
import object.HitWallException;
import object.Projectile;

public class Illya extends Boss {
	
	private double distance;
	private double distanceX;
	private double distanceY;
	
	private static final double range = 500;
	private static final double highSpeed = 15;
	private static final double slashSpeed = 25;
	private static final double slashWidth = 200;
	private static final double slashHeight = 100;
	
	public Illya(double x, double y) {
		super(x, y, 60, 200);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Illya.png").toString(), 225, 200, false, true)));
		artList.add("normal");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Illya_Attack.png").toString(), 180, 180, false, true)));
		getChildren().get(1).setLayoutY(10);
		artList.add("attack");
		friction = 0.03;
		speed = 10;
		maxHp = 500;
		attackDamage = 20;
		bossTheme = Music.Get_Goal;
		name = "Illyasviel von Einzbern";
	}
	
	public void setMovement() {
		distanceX = Main.hero.getCenterX() - getCenterX();
		distanceY = Main.hero.getCenterY() - getCenterY();
		distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		switch(cerrentState) {
		case "idle":
			if (distance < range) {
				startBossFight();
				changeState();
			}
			break;
		case "normal":
			turn(Main.hero.getCenterX() < getCenterX());
			if (distance > range) {
				flyToHero();
			} else {
				//fly up and away from the hero
				dx += (speed*(distanceY/distance)*((distanceX*distanceY > 0) ? -1 : 1) - dx)*friction;
				dy += (speed*(distanceX/distance)*((distanceX*distanceY > 0) ? -1 : 1) - dy)*friction;
			}
			break;
		case "moveToLeft":
			turn(Main.hero.getCenterX() < getCenterX());
			if (distance > range) {
				flyToHero();
			} else {
				//fly anti-clockwise  to the left side of the hero
				dx += (-highSpeed*(distanceY/distance) - dx)*friction;
				dy += (speed*(distanceX/distance) - dy)*friction;
			}
			break;
		case "moveToRight":
			turn(Main.hero.getCenterX() < getCenterX());
			if (distance > range) {
				flyToHero();
			} else {
				//fly clockwise to the right side of the hero
				dx += (highSpeed*(distanceY/distance) - dx)*friction;
				dy += (-speed*(distanceX/distance) - dy)*friction;
			}
			break;
		case "attack":
			turn(Main.hero.getCenterX() < getCenterX());
			//fly to the same plane with the hero
			dx += (speed*(distanceY/distance)*((distanceX*distanceY > 0) ? -1 : 1) - dx)*friction;
			dy += (highSpeed*((distanceY > 0) ? 1 : -1) - dy)*friction;
			break;
		}
	}
	
	protected void changeState() {
		switch (cerrentState) {
		case "idle":
			changeSprite("normal");
			turn((Main.hero.getX() + Main.hero.getSize()[0]/2) < (x + size[0]/2));
			setVisible(true);
			holdStage(3000);
			break;
		//case normal or moveToLeft or moveToRight
		case "normal":
		case "moveToLeft":
		case "moveToRight":
			changeSprite("attack");
			holdStage(1500);
			break;
		case "attack":
			//slash attack
			Projectile slash = new Projectile(ClassLoader.getSystemResource("Effect/slash.png").toString(), 
					x + (turnLeft ? -200 : 60), y + 50, slashWidth, slashHeight, 
					(turnLeft ? -slashSpeed : slashSpeed), 0, attackDamage);
			slash.setScaleX(turnLeft ? -1 : 1);
			Main.world.addObject(slash);
			changeSprite("normal");
			holdStage(3000);
			break;
		}
	}
	
	protected void moveX() {
		//broken when hit the wall
		if (dx < 0) {
			try {
				leftWallCheck();
				x +=dx;
			} catch(HitWallException exception) {
				if (cerrentState == "normal") {
					cerrentState = "moveToRight";
				}
				x += exception.distance;
				dx = 0;
			}
		}else if (dx > 0) {
			try {
				rightWallCheck();
				x +=dx;
			} catch(HitWallException exception) {
				if (cerrentState == "normal") {
					cerrentState = "moveToLeft";
				}
				x += exception.distance;
				dx = 0;
			}
		}
	}
	
	private void flyToHero(){
		dx += (speed*(distanceX/distance) - dx)*friction;
		dy += (speed*(distanceY/distance) - dy)*friction;
	}
	
	public void turn(boolean turnLeft) {
		super.turn(turnLeft);
		// set all image to the proper location
		getChildren().get(0).setLayoutX(turnLeft ? -70 : -95);
		getChildren().get(1).setLayoutX(turnLeft ? -30 : -90);
	}
	
	protected void reset() {
		super.reset();
		setVisible(false);
		cerrentState = "idle";
	}
	
	public void die() {
		super.die();
		dropItem(new Shoes(ShoesType.GoldenShoes), 1);
	}

}
