package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractObjects implements ImageTile, ObjProperties {
	private Point2D position;
	private String imageName;
	private int layer;

	public AbstractObjects(String imageName, Point2D position, int layer) {
		this.imageName = imageName;
		this.position = position;
		this.layer = layer;
	}

	@Override
	public boolean isTransposable() {
		return true;
	}

	@Override
	public boolean movableObj() {
		return false;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override
	public String getName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public boolean staticObj() {
		return false;
	}

}
