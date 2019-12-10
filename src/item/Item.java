package item;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public abstract class Item extends Button{
	
	public Item(String imagePath) {
		setGraphic(new ImageView(new Image(imagePath, 50, 50, false, true)));
		setPadding(new Insets(5));
		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public abstract void applyBonuses();
	
	public abstract void unapplyBonuses();
	
	public abstract String getTypeOfItem();
	
//	public void highlight() {
//		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
//
//	}
//	
//	public void unhighlight() {
//		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	
//	}	
	
}
