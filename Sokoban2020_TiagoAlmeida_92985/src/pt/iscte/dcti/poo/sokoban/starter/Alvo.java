package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractObjects {

	public Alvo(Point2D position) {
		super("Alvo", position, 1);
	}

	public boolean isFull(Point2D position) {
		for (AbstractObjects obj : SokobanGame.getInstance().objectsAtPosition(position))
			if (obj instanceof Caixote)
				return true;
		return false;

	}

}
