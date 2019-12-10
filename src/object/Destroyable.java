package object;

public interface Destroyable extends Updateable {
	
	public void attacked(double damage, double knockbackX, double knockbackY);
	
	public void die();
	
	public void spawn();
	
	public boolean intersectCheck(double x0, double x1, double y0, double y1);

}
