package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractObjects {

	public Parede(Point2D position) {
		super("Parede", position, 2);
	}

	@Override
	public boolean isTransposable() {
		return false;
	}

	@Override
	public boolean staticObj() {
		return true;
	}

}
