package object;

import application.Main;
import application.Music;
import application.Sound;
import menu.BossHpBar;

public abstract class Boss extends MoveableEnemy {
	
	protected Music bossTheme;
	protected BossHpBar hpBar;
	protected String name;
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void update() {
		if (hpBar != null) {
			hpBar.update(hp);
		}
		super.update();
	}
	
	protected void startBossFight() {
		Sound.changeBackgroundMusic(bossTheme);
		Main.world.setBossFight(true);
		hpBar = new BossHpBar(maxHp, name);
		Main.gameRoot.getChildren().add(hpBar);
	}
	
	public void die() {
		Main.world.getCerrentMap().getEnemyList().remove(this);
		Sound.changeBackgroundMusic(Main.world.getCerrentMap().getMusic());
		Main.eventLog.addText(name + " has been slayed");
		super.die();
	}
	
	public void remove() {
		Main.gameRoot.getChildren().remove(hpBar);
		Main.world.setBossFight(false);
		super.remove();
	}

	public BossHpBar getHpBar() {
		return hpBar;
	}
	
}
