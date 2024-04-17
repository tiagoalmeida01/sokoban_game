package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Martelo extends AbstractObjects implements Interactions {

	public Martelo(Point2D position) {
		super("Martelo", position, 1);

	}

	@Override
	public boolean staticObj() {
		return true;
	}

	@Override
	public void interaction(Direction dir) {
		SokobanGame.getInstance().getPlayer().catchMartelo();
		SokobanGame.getInstance().removeObject(this);
		SokobanGame.getInstance().getObjects().forEach(obj -> {if(obj instanceof ParedePartida)((ParedePartida) obj).setIsTransposableTrue();});
		
	}
}
