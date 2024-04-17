package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;

public interface IsMovable {
	void move(Direction dir);

	boolean isMovable(Direction dir);

	void fall();
}
