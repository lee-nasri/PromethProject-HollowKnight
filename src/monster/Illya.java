package monster;

import application.Main;
import application.Music;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Boss;

public class Illya extends Boss {
	
	private static final double range = 200000;
	
	public Illya(double x, double y) {
		super(x, y, 80, 220);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Illya.png").toString(), 250, 220, false, true)));
		getChildren().get(0).setLayoutX(-100);
		artList.add("normal");
		friction = 0.05;
		speed = 5;
		maxHp = 600;
		attackDamage = 25;
		bossTheme = Music.Get_Goal;
	}
	
	public void setMovement() {
		switch(cerrentStage) {
		case "idle":
			if (Math.pow(Main.hero.getCenterX() - getCenterX(), 2) 
					+ Math.pow(Main.hero.getCenterY() - getCenterY(), 2) < range*2) {
				startBossFight();
				changeStage();
			}
			break;
		case "normal":
			turn(Main.hero.getCenterX() < getCenterX());
			if (Math.pow(Main.hero.getCenterX() - getCenterX(), 2) 
					+ Math.pow(Main.hero.getCenterY() -  getCenterY(), 2) > range) {
				dx += ((turnLeft ? -speed : speed) - dx)*friction;
				dy += (((Main.hero.getCenterY() < (y + size[1])) ? -speed : speed) - dy)*friction;
			} else {
				dx += ((turnLeft ? speed : -speed) - dx)*friction;
				dy += (((Main.hero.getCenterY() <  getCenterY()) ? speed : -speed) - dy)*friction;
			}
			break;
		}
	}
	
	protected void changeStage() {
		switch (cerrentStage) {
		case "idle":
			changeArt("normal");
			turn((Main.hero.getX() + Main.hero.getSize()[0]/2) < (x + size[0]/2));
			setVisible(true);
			break;
		}
	}
	
	public void turn(boolean turnLeft) {
		super.turn(turnLeft);
		getChildren().get(0).setLayoutX(turnLeft ? -70 : -100);
	}
	
	protected void reset() {
		super.reset();
		setVisible(false);
		cerrentStage = "idle";
	}

}
