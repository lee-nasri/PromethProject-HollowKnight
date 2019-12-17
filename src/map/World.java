package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import application.Main;
import application.Sound;
import object.BreakableWall;
import object.CheckPoint;
import object.Destroyable;
import object.GameObject;
import object.GamePlatform;
import object.Updateable;

public class World extends Group {
	
	private HashMap<MapName, Map> mapList = new HashMap<MapName, Map>();
	private Map currentMap;
	private List<Updateable> objectList = new ArrayList<Updateable>();
	private List<Destroyable> destroyableList = new ArrayList<Destroyable>();
	private double viewX, viewY;
	private boolean bossFight;
	
	public World() {
		addMap(MapCreater.createStarterMap());
		addMap(MapCreater.createFalseKnightRoom());
		addMap(MapCreater.createTown());
		addMap(MapCreater.createCave());
		addMap(MapCreater.createDarkCave());
		addMap(MapCreater.createCrystalCave());
	}
	
	public void setCerrentMap(MapName name, double x, double y) {
		getChildren().clear();
		for (Updateable object: new ArrayList<Updateable>(Main.world.getObjectList())) {
			object.remove();
		}
		currentMap = mapList.get(name);
		getChildren().addAll(currentMap.getBackground());
		for (GamePlatform platform: currentMap.getPlatformList()) {
			objectList.add(platform);
			getChildren().add(platform);
			if (platform instanceof BreakableWall) {
				BreakableWall breakableWall = (BreakableWall) platform;
				destroyableList.add(breakableWall);
				breakableWall.spawn();
			}
		}
		for (CheckPoint checkPoint: currentMap.getCheckPointList()) {
			objectList.add(checkPoint);
			getChildren().add(checkPoint);
		}
		for (Destroyable enemy: currentMap.getEnemyList()) {
			objectList.add(enemy);
			destroyableList.add(enemy);
			enemy.spawn();
		}
		if (currentMap.isDarkArea()) {
			getChildren().add(Main.hero.getLight());
		}
		getChildren().add(Main.hero);
		Main.hero.setAlive(true);
		Main.hero.setLocation(x, y);
		Sound.changeBackgroundMusic(currentMap.getMusic());
	}
	
	public void drawBackground() {
		double x = Main.hero.getX() + Main.hero.getSize()[0]/2;
		double y = Main.hero.getY() + Main.hero.getSize()[1]/2;
		viewX = (x < Main.getSceneWidth()/2) 
				? 0 : ((x > (currentMap.getWidth() - Main.getSceneWidth()/2)) 
						? (currentMap.getWidth() - Main.getSceneWidth()) : (x - Main.getSceneWidth()/2));
		viewY = (y < Main.getSceneHeight()/2)
				? 0 : ((y > (currentMap.getHeight() - Main.getSceneHeight()/2)) 
						? (currentMap.getHeight() - Main.getSceneHeight()) : (y - Main.getSceneHeight()/2));
		for (ImageView i:currentMap.getBackground()) {
			i.setLayoutX(-viewX*(i.getImage().getWidth() - Main.getSceneWidth())/(currentMap.getWidth() - Main.getSceneWidth()));
			i.setLayoutY(-viewY*(i.getImage().getHeight() - Main.getSceneHeight())/(currentMap.getHeight() - Main.getSceneHeight()));
		}
	}
	
	public void addMap(Map map) {
		mapList.put(map.getName(), map);
	}
	
	public void addObject(GameObject object) {
		objectList.add(object);
		if (object instanceof Destroyable) {
			Destroyable destroyable = (Destroyable) object;
			destroyableList.add(destroyable);
		}
		getChildren().add(object);
		object.draw();
		object.setAlive(true);
	}
	
	public void reloadBackground() {
		getChildren().removeAll(currentMap.getBackground());
		getChildren().addAll(0, currentMap.getBackground());
	}

	public boolean isBossFight() {
		return bossFight;
	}
	
	public void setBossFight(Boolean bossFight) {
		this.bossFight = bossFight;
	}

	public Map getCurrentMap() {
		return currentMap;
	}
	
	public List<Updateable> getObjectList() {
		return objectList;
	}

	public List<Destroyable> getDestroyableList() {
		return destroyableList;
	}

	public double getViewX() {
		return viewX;
	}

	public double getViewY() {
		return viewY;
	}
}
