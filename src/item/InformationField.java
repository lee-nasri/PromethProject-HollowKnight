package item;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InformationField extends VBox{
	private String itemName, itemBonus, itemTypeBonus;
	private ImageView itemImage;
	private Label textName, textBonus;
	
	public InformationField() {
		
		setAlignment(Pos.CENTER);
		setPrefWidth(350);
		setSpacing(5);
		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
		setLayoutX(300);
		setLayoutY(300);
		
		Label textTitle = new Label("Item Information"); 
		textTitle.setTextFill(Color.WHITE);
		textTitle.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		textTitle.setAlignment(Pos.CENTER);
		textTitle.setFont(Font.font(15)); 
		textTitle.setPrefWidth(600);
		
		
		textName = new Label(" Name : Unknow Item");
		textName.setTextFill(Color.WHITE);
		textName.setAlignment(Pos.CENTER_LEFT);
		textName.setFont(Font.font(15)); 
		textName.setPrefWidth(600);
		textBonus = new Label(" Bonus : +No Bonus");
		textBonus.setTextFill(Color.WHITE);
		textBonus.setAlignment(Pos.CENTER_LEFT);
		textBonus.setFont(Font.font(15)); 
		textBonus.setPrefWidth(600);
		itemImage = new ImageView(new Image(ClassLoader.getSystemResource
				("ItemImage/InventoryBlock.png").toString()));
		itemImage.setVisible(false);
		getChildren().addAll(textTitle, itemImage, textName , textBonus);
	}
	
	public void newItem(Item item) {
		// 1. set String
		itemName = item.getName();
		itemBonus = Integer.toString(item.getBonus());
		itemImage.setImage(item.getImage());
		if (item.getTypeOfItem() == "Sword") itemTypeBonus = "Attack Damage";
		else if (item.getTypeOfItem() == "Armor") itemTypeBonus = "Hp";
		else if (item.getTypeOfItem() == "Shoes") itemTypeBonus = "Speed";
		// 2. set Label
		textName.setText(" Name : " + itemName);
		textBonus.setText(" Bonus : +" + itemBonus + " " + itemTypeBonus);
		itemImage.setVisible(true);
	}
	
	public void clearItem() {
		// 1. clear String
		itemName = "Unknow Item";
		itemBonus = "No Bonus";
		itemTypeBonus = "";
		itemImage.setVisible(false);
		// 2. clear Label
		textName.setText(" Name : " + itemName);
		textBonus.setText(" Bonus : +" + itemBonus + " " + itemTypeBonus);
	}
}
