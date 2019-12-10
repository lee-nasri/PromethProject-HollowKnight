package object;

import application.Main;

public class BreakableWall extends GamePlatform implements Destroyable {
	
	protected double maxHp = 50;
	protected double hp;
	
	public BreakableWall(PlatformType platformType, double x, double y) {
		super(platformType, x, y);
	}
	
	public void attacked(double damage, double knockBackX, double knockBackY) {
		hp = ((damage > hp) ? 0 : (hp - damage));
		if (hp == 0) {
			die();
		}
	}
	
	public void die() {
		Main.world.getCerrentMap().getPlatformList().remove(this);
		remove();
	}
	
	public void remove() {
		Main.world.getDestroyableList().remove(this);
		super.remove();
	}
	
	public boolean intersectCheck(double x, double y, double width, double height) {
		if ((this.x <= (x + width)) && ((this.x + size[0]) >= x) 
				&& (this.y <= (y + height)) && ((this.y + size[1]) >= y)) {
			return true;
		}
		return false;
	}
	
	public void spawn() {
		hp = maxHp;
	}

}
