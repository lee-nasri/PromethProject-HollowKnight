package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GamePlatform extends GameObject {
	
	public GamePlatform(PlatformType platformType, double x, double y) {
		super(platformType.getPath(), x, y, platformType.getWidth(), platformType.getHeight());
	}
	
	public GamePlatform(PlatformType platformType, double x, double y, double width, double height) {
		super(x, y, width, height);
		Image image = new Image(platformType.getPath());
		//duplicate the image
		int duplicateX = (platformType.repeatX() ? (int)(width/image.getWidth() + 0.5) : 1);
		int duplicateY = (platformType.repeatY() ? (int)(height/image.getHeight() + 0.5) : 1);
		image = new Image(platformType.getPath(), (width/duplicateX + 1), (height/duplicateY + 1), false, true);
		for (int i = 0; i < duplicateX; i++) {
			for (int j = 0; j < duplicateY; j++) {
				getChildren().add(new ImageView(image));
				getChildren().get(getChildren().size() - 1).setLayoutX(width/duplicateX*i);
				getChildren().get(getChildren().size() - 1).setLayoutY(height/duplicateY*j);
			}
		}
	}
	
	public GamePlatform(PlatformType platformType, double x, double y, double width, double height, 
			boolean flipX, boolean flipY) {
		this(platformType, x, y, width, height);
		if (flipX) {
			setScaleX(-1);
		}
		if (flipY) {
			setScaleY(-1);
		}
	}
	
	public void checkLeft(MoveableObject object) throws HitWallException {
		if (((object.getY() + object.getSize()[1]) > y)
				&& (object.getY() < (y + size[1]))
				&& ((object.getX() + object.getSize()[0]) <= x)
				&& ((object.getX() + object.getSize()[0] + object.getDx()) > x)) {
			throw new HitWallException(x - object.getX() - object.getSize()[0]);
		}
	}
	
	public void checkRight(MoveableObject object) throws HitWallException {
		if (((object.getY() + object.getSize()[1]) > y)
				&& (object.getY() < (y + size[1]))
				&& (object.getX() >= (x + size[0]))
				&& ((object.getX() + object.getDx()) < (x + size[0]))) {
			throw new HitWallException(x + size[0] - object.getX());
		}
	}
	
	public void checkTop(MoveableObject object) throws HitWallException {
		if (((object.getX() + object.getSize()[0]) > x)
				&& (object.getX() < (x + size[0]))
				&& ((object.getY() + object.getSize()[1]) <= y)
				&& ((object.getY() + object.getSize()[1] + object.getDy()) >= y)) {
			throw new HitWallException(y - object.getY() - object.getSize()[1]);
		}
	}
	
	public void checkBottom(MoveableObject object) throws HitWallException {
		if (((object.getX() + object.getSize()[0]) > x)
				&& (object.getX() < (x + size[0]))
				&& (object.getY() >= (y + size[1]))
				&& ((object.getY() + object.getDy()) < (y + size[1]))) {
			throw new HitWallException(y + size[1] - object.getY());
		}
	}

}
