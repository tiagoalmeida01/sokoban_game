package pt.iscte.dcti.poo.sokoban.starter;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Empilhadora extends AbstractObjects implements IsMovable {
	private int moves;
	private int energy;
	private static final int MAXENERGY = 50;
	private boolean hasMartelo;

	public Empilhadora(Point2D position) {
		super("Empilhadora_U", position, 2);
		this.moves = 0;
		this.energy = MAXENERGY;
	}

	@Override
	public void move(Direction dir) {
		Point2D newPosition = getPosition().plus(dir.asVector());
		if (energy > 0 && isMovable(dir)) {
			iterateCounters();
			for (AbstractObjects obj : SokobanGame.getInstance().getObjects())
				if (obj.getPosition().equals(newPosition)) {
					if (obj instanceof Movable)
						((Movable) obj).move(dir);
					setPosition(newPosition);
					changeImg(dir);
					if (obj instanceof Interactions && energy != 0)
						((Interactions) obj).interaction(dir);

				}
		}

		ImageMatrixGUI.getInstance().update();
		lostPower();
	}

	@Override
	public boolean isMovable(Direction dir) {
		Point2D newPosition = getPosition().plus(dir.asVector());
		for (AbstractObjects obj : SokobanGame.getInstance().getObjects()) {
			if (newPosition.equals(obj.getPosition()) && !(obj.isTransposable() || obj.movableObj()))
				return false;
			if (newPosition.equals(obj.getPosition()) && obj instanceof Movable)
				if (!((Movable) obj).isMovable(dir))
					return false;
		}
		return true;
	}

	private void iterateCounters() {
		moves++;
		energy--;
	}

	private void lostPower() {
		if (energy == 0) {
			SokobanGame.getInstance().showInfo();
			JOptionPane.showMessageDialog(null, "No power! Press R to restart", "Charge Up!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void fall() {
		ImageMatrixGUI.getInstance().update();
		JOptionPane.showMessageDialog(null, "You Lose! Try Again!", "Game Over!", JOptionPane.WARNING_MESSAGE);
		SokobanGame.getInstance().restartLevel();
	}

	private void changeImg(Direction dir) {
		switch (dir) {
		case LEFT:
			setImageName("Empilhadora_L");
			break;
		case UP:
			setImageName("Empilhadora_U");
			break;
		case RIGHT:
			setImageName("Empilhadora_R");
			break;
		case DOWN:
			setImageName("Empilhadora_D");
			break;
		}
	}

	public void catchMartelo() {
		hasMartelo = true;
	}

	public int getMoves() {
		return moves;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public static int getMAXENERGY() {
		return MAXENERGY;
	}

	public boolean hasMartelo() {
		return hasMartelo;
	}

}
