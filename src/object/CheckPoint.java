package object;

import application.Main;

public class CheckPoint extends GameObject {
	
	private static final double width = 100;
	private static final double height = 200;
	
	public CheckPoint(double x, double y) {
		super(ClassLoader.getSystemResource("Effect/Bonfire.png").toString(), x, y, width, height);
	}
	
	public void update() {
		if (Main.hero.intersectCheck(x, y, width, height)) {
			Main.hero.setHp(Main.hero.getMaxHp());
			if (Main.hero.getSpawnMap() != Main.world.getCurrentMap().getName()) {
				Main.eventLog.addText("Game saved");
				Main.hero.setSpawnLocationX(x);
				Main.hero.setSpawnLocationY(y + 100);
				Main.hero.setSpawnMap(Main.world.getCurrentMap().getName());
			}
		}
		super.update();
	}
	
}
