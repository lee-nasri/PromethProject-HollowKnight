package menu;

import application.Main;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BossHpBar extends HpBar {
	
	public BossHpBar(double maxHp, String name) {
		super(30, maxHp,  (Main.getSceneWidth() - 100)/maxHp);
		Label bossName = new Label("   " + name);
		bossName.setFont(Font.font(24));
		bossName.setTextFill(Color.BLACK);
		getChildren().add(bossName);
		hpDecreaseSpeed = 1;
		setLayoutX(50);
		setLayoutY(Main.getSceneHeight() - 100);
	}
	
	public void update(double hp) {
		setLayoutY(Main.getSceneHeight() - 100);
		scale = (Main.getSceneWidth() - 100)/maxHp;
		border.setWidth(maxHp*scale);
		super.update(hp);
	}

}
