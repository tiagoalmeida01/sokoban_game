package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Movable extends AbstractObjects implements IsMovable {
	public Movable(String imageName, Point2D position) {
		super(imageName, position, 2);
	}

	@Override
	public void move(Direction dir) {
		Point2D newPosition = getPosition().plus(dir.asVector());
		if (isMovable(dir)) {
			setPosition(newPosition);
			SokobanGame.getInstance().getObjects().forEach(obj -> {
				if (obj.getPosition().equals(newPosition) && obj instanceof Interactions && !obj.staticObj())
					((Interactions) obj).interaction(dir);
			});
		}
	}

	@Override
	public boolean isMovable(Direction dir) {
		Point2D newPosition = getPosition().plus(dir.asVector());
		for (AbstractObjects obj : SokobanGame.getInstance().getObjects()) {
			if (newPosition.equals(obj.getPosition()) && !obj.isTransposable())
				return false;
		}
		return true;
	}

	@Override
	public void fall() {
		SokobanGame.getInstance().removeObject(this);
	}

	@Override
	public boolean isTransposable() {
		return false;
	}

	@Override
	public boolean movableObj() {
		return true;
	}

}
