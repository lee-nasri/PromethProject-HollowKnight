package menu;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import application.Main;

public class EventLog extends VBox {
	
	private List<Label> textList = new ArrayList<Label>();
	
	private static final int maxText = 4;
	private static final double opacityDecreaseSpeed = 0.01;
	
	public EventLog() {
		setLayoutX(50);
		fillBox();
	}
	
	public void update() {
		setLayoutY(Main.getSceneHeight() - 250);
		for (Label label: new ArrayList<Label>(textList)) {
			label.setOpacity(label.getOpacity() - opacityDecreaseSpeed);
			if (label.getOpacity() == 0) {
				textList.remove(label);
			}
		}
	}
	
	public void addText(String text) {
		Label label = new Label(text);
		label.setFont(Font.font(24));
		label.setTextFill(Color.ALICEBLUE);
		label.setOpacity(3);
		textList.add(label);
		getChildren().add(label);
		if (getChildren().size() > maxText) {
			getChildren().remove(0);
		}
	}
	
	private void fillBox() {
		for (int index = 0; index < maxText; index++) {
			Label emptyLabel = new Label();
			emptyLabel.setFont(Font.font(24));
			getChildren().add(emptyLabel);
		}
	}

}
