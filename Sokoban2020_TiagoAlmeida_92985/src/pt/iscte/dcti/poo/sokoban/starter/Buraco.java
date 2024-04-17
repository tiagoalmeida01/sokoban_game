package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractObjects implements Interactions {

	public Buraco(Point2D position) {
		super("Buraco", position, 1);
	}

	@Override
	public void interaction(Direction dir) {
		SokobanGame.getInstance().objectsAtPosition(getPosition()).forEach(obj -> {if(obj instanceof IsMovable) ((IsMovable) obj).fall();});
	}
}
