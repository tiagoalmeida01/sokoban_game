package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractObjects implements Interactions {

	public Bateria(Point2D position) {
		super("Bateria", position, 1);

	}

	@Override
	public boolean staticObj() {
		return true;
	}

	@Override
	public void interaction(Direction dir) {
		SokobanGame.getInstance().getPlayer().setEnergy(Empilhadora.getMAXENERGY());
		SokobanGame.getInstance().removeObject(this);
	}
}
