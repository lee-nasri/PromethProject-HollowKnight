package item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends GridPane {
	
	private ObservableList<Item> myInventory = FXCollections.observableArrayList();
	private Map<String, Item> myActivateItem = new HashMap<>();
	private GridPane activateItemPane;
	private InformationField informationField;
	
	private static final int maxSize = 18;
	private static final int maxColumn = 6;
	private static final int maxRow = 3;
	
	public Inventory () {
		// set property of this GridPane
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);	
		addInventoryPane();
		
		// create and set property of myActivateItem GridPane.
		activateItemPane = new GridPane();
		activateItemPane.setAlignment(Pos.CENTER);
		activateItemPane.setVgap(10);
		activateItemPane.setHgap(10);
		addActivePane();
		
		// create  InformationField.
		informationField = new InformationField();
		
		// Starting Item.
		try {
			addItem(new Sword(SwordType.NormalSword));
			addItem(new Armor(ArmorType.NormalArmor));
			addItem(new Shoes(ShoesType.NormalShoes));
		} catch (FullInventoryException e) {
			
		}
	}
	
	public void addItem(Item newItem) throws FullInventoryException {
		if (myInventory.size() >= maxSize) {
			throw new FullInventoryException();
		}
		myInventory.add(newItem);
		addActivatedBlock(newItem);
		myInventoryPaneAdd(newItem);
	}
	
	public void activateItem(Item newItem) {
		newItem.applyBonuses();
		if (isItemtypeActivate(newItem)) {
			// remove Old item in myActivateItem (map)
			Item removedItem = deactivateItem(newItem.getTypeOfItem());
			// add removedItem to myInventoryPane
			myInventoryPaneAdd(removedItem);
		}
		// add newItem to myActivateItem (map)
		myActivateItem.put(newItem.getTypeOfItem(), newItem);
		// update MyActivatePane
		activatePaneBlock(newItem);
	}
	
	
	public Item deactivateItem(String typeOfItem) { // item is any item that same type of item we need to deactivate
		// Guarantee that there is items in the myActivateItem (map) before call this method.
		if(typeOfItem != "Sword" && typeOfItem != "Armor" && typeOfItem != "Shoes") return null;
		Item removedItem = myActivateItem.remove(typeOfItem);
		removedItem.unapplyBonuses();
		return removedItem;
	}
	
	public void activatePaneBlock(Item newItem) {	
		// Update only block of new item.
		if (newItem.getTypeOfItem() == "Sword") {
			activateItemPane.add(newItem, 0, 0);
		}
		if (newItem.getTypeOfItem() == "Armor") {
			activateItemPane.add(newItem, 1, 0);
		}
		
		if (newItem.getTypeOfItem() == "Shoes") {
			activateItemPane.add(newItem, 2, 0);
		}
		newItem.setOnAction(event -> {
			Item removedItem = deactivateItem(newItem.getTypeOfItem());
			myInventoryPaneAdd(removedItem);
		});
	}

	public boolean isItemtypeActivate(Item item) {
		return myActivateItem.containsKey(item.getTypeOfItem());
	}
	
	
	
	public GridPane getActivateItemPane() {
		return this.activateItemPane;
	}
	
	public InformationField getInformationField() {
		return informationField;
	}
	
	public Map<String, Item> getMyActivateItem() {
		return myActivateItem;
	}
	
	public void myInventoryPaneAdd(Item newItem) {
		int column = myInventory.indexOf(newItem) % maxColumn;
		int row = myInventory.indexOf(newItem)/maxColumn;
		add(newItem, column, row);
		newItem.setOnAction(e -> activateItem(newItem));
		newItem.setOnMouseEntered(e -> informationField.newItem(newItem));
		newItem.setOnMouseExited(e -> informationField.clearItem());
	}
	
	public void addActivatedBlock(Item item) {
		ImageView activateBlock = new ImageView(new Image(ClassLoader.getSystemResource
				("ItemImage/ActivateBlock.png").toString(), 60, 60, false, true));
		int column = myInventory.indexOf(item) % maxColumn;
		int row = myInventory.indexOf(item)/maxColumn;
		add(activateBlock, column, row);
	}
	
	public void addInventoryPane() {
		for	(int row = 0 ; row < maxRow ; row++) {
			for	(int column=0; column < maxColumn ; column++) {
				ImageView emptyBlock = new ImageView(new Image(ClassLoader.getSystemResource
						("ItemImage/InventoryBlock.png").toString()));
				emptyBlock.setFitHeight(63);
				emptyBlock.setFitWidth(63);
				add(emptyBlock, column, row);
			}
		}
	}
	
	public void addActivePane() {
		for	(int column=0; column < 3 ; column++) {
			ImageView emptyBlock = new ImageView(new Image(ClassLoader.getSystemResource
					("ItemImage/InventoryBlock.png").toString()));
			emptyBlock.setFitHeight(63);
			emptyBlock.setFitWidth(63);
			activateItemPane.add(emptyBlock, column, 0);
		}
		Label tSword = new Label("Sword");
		tSword.setTextFill(Color.WHITE);
		tSword.setAlignment(Pos.CENTER);
		
		Label tArmor = new Label("Armor");
		tArmor.setTextFill(Color.WHITE);
		tArmor.setAlignment(Pos.CENTER);
		
		Label tOther = new Label("Shoes");
		tOther.setTextFill(Color.WHITE);
		tOther.setAlignment(Pos.CENTER);
		
		activateItemPane.add(tSword, 0, 1);
		activateItemPane.add(tArmor, 1, 1);
		activateItemPane.add(tOther, 2, 1);
	}
	
}
