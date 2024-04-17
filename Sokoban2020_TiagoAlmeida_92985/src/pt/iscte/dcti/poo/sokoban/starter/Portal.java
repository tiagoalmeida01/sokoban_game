package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Portal extends AbstractObjects implements Interactions {

	public Portal(Point2D position) {
		super("Portal_Azul", position, 1);

	}

	@Override
	public void interaction(Direction dir) {
		Point2D position = this.getPosition();
		for (AbstractObjects obj : SokobanGame.getInstance().getObjects())
			if (obj instanceof Portal && obj.getPosition() != this.getPosition())
				position = obj.getPosition(); // vai buscar a posicao do outro portal

		if (isPortalAvailable(position)) {
			SokobanGame.getInstance().getPlayer().setPosition(position);
			for (Movable obj : SokobanGame.getInstance().getMovableObjects())
				if (obj.getPosition().equals(this.getPosition()))
					obj.setPosition(position);
		}
	}

	private boolean isPortalAvailable(Point2D position) {
		int count = 0;
		for (AbstractObjects obj : SokobanGame.getInstance().objectsAtPosition(position))
			if (!(obj instanceof Portal))
				count++;
		return count == 0;
	}
}
