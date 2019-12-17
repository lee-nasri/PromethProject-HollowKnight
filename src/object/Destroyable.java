package object;

public interface Destroyable extends Updateable {
	
	public void attacked(double damage, double knockbackX, double knockbackY);
	
	public void die();
	
	public void spawn();
	
	public boolean intersectCheck(double x, double y, double width, double height);

}
