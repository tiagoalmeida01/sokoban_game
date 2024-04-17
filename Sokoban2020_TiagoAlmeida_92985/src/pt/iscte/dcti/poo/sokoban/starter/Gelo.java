package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Gelo extends AbstractObjects implements Interactions {

	public Gelo(Point2D position) {
		super("Gelo", position, 0);
	}

	@Override
	public void interaction(Direction dir) {
		SokobanGame.getInstance().objectsAtPosition(this.getPosition()).forEach(obj -> {
			if (obj instanceof IsMovable && SokobanGame.getInstance().getPlayer().getEnergy() != 0)
				((IsMovable) obj).move(dir);
		});

	}

}
