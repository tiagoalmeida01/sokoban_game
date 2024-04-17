package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class ParedePartida extends AbstractObjects implements Interactions {
	private boolean isTransposable;

	public ParedePartida(Point2D position) {
		super("Parede_Partida", position, 2);

	}

	@Override
	public boolean isTransposable() {
		return isTransposable;
	}

	@Override
	public boolean staticObj() {
		return true;
	}

	public void setIsTransposableTrue() {
		isTransposable = true;
	}

	@Override
	public void interaction(Direction dir) {
		if (SokobanGame.getInstance().getPlayer().hasMartelo())
			SokobanGame.getInstance().removeObject(this);
	}
}
