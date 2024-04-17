package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends Movable {
	private boolean movableObj = true;

	public BigStone(Point2D position) {
		super("BigStone", position);
	}

	@Override
	public void fall() {
		movableObj = false;
	}

	@Override
	public boolean movableObj() {
		return movableObj;
	}
}
