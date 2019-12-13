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
	// myActivateItem index0 = Sword , index1 = Armor;
	private GridPane activateItemPane;
	
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
		
		// Add Item For test only. don't forget to delete after test.
		addItem(new Sword(SwordType.GoldenSword));
		addItem(new Sword(SwordType.NormalSword));
		addItem(new Armor(ArmorType.LegendArmor));
		addItem(new Armor(ArmorType.RareArmor));
	} 
	
	public void addItem(Item newItem) {
		if (!myInventory.contains(newItem)) {
			myInventory.add(newItem);
			addActivatedBlock(newItem);
		}
		myInventoryPaneAdd(newItem);
		newItem.setOnAction(e -> activateItem(newItem));
	}
	
	public void activateItem(Item newItem) {
		newItem.applyBonuses();
		if (isItemtypeActivate(newItem)) {
			// remove Old item in myActivateItem (map)
			Item removedItem = deactivateItem(newItem.getTypeOfItem());
			// add removedItem to myInventory (List)
			addItem(removedItem);
		}
		// add newItem to myActivateItem (map)
		myActivateItem.put(newItem.getTypeOfItem(), newItem);
		// update MyActivatePane
		activatePaneBlock(newItem);
		newItem.setOnAction(e -> {
			Item removedItem = deactivateItem(newItem.getTypeOfItem());
			addItem(removedItem);
		});
	}
	
	public Item deactivateItem(String typeOfItem) { // item is any item that same type of item we need to deactivate
		// Guarantee that there is items in the myActivateItem (map) before call this method.
		if(typeOfItem != "Sword" && typeOfItem != "Armor") return null;
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
	}

	public boolean isItemtypeActivate(Item item) {
		return myActivateItem.containsKey(item.getTypeOfItem());
	}
	
	
	
	public GridPane getActivateItemPane() {
		return this.activateItemPane;
	}
	
	public Map<String, Item> getMyActivateItem() {
		return myActivateItem;
	}
	
	public void myInventoryPaneAdd(Item newItem) {
		int column = myInventory.indexOf(newItem) % maxColumn;
		int row = myInventory.indexOf(newItem)/maxColumn;
		add(newItem, column, row);
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
		
		Label tOther = new Label("Other");
		tOther.setTextFill(Color.WHITE);
		tOther.setAlignment(Pos.CENTER);
		
		activateItemPane.add(tSword, 0, 1);
		activateItemPane.add(tArmor, 1, 1);
		activateItemPane.add(tOther, 2, 1);
	}
	
}
