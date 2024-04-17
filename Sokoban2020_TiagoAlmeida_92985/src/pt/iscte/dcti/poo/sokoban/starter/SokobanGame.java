package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

	private static SokobanGame INSTANCE;
	private List<AbstractObjects> objects = new ArrayList<>();
	private Empilhadora player;
	private int level = 0;
	private String playerName;

	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	private static final int MUMBEROFLEVELS = 5;

	private static final char PAREDE = '#';
	private static final char BATERIA = 'b';
	private static final char BURACO = 'O';
	private static final char CAIXOTE = 'C';
	private static final char ALVO = 'X';
	private static final char CHAO = ' ';
	private static final char EMPILHADORA = 'E';
	private static final char SMALLSTONE = 's';
	private static final char BIGSTONE = 'S';
	private static final char GELO = 'g';
	private static final char MARTELO = 'm';
	private static final char PAREDEPARTIDA = '%';
	private static final char PORTAL = 't';

	private SokobanGame() {
		userInterface();
		assembleLevel();
	}

	public static SokobanGame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SokobanGame();
		}
		return INSTANCE;
	}

	private void assembleLevel() {
		objects.clear();
		ImageMatrixGUI.getInstance().clearImages();
		buildLevel();
		objects.forEach(obj -> ImageMatrixGUI.getInstance().addImage(obj));
		ImageMatrixGUI.getInstance().update();
	}

	private void buildLevel() {
		try {
			Scanner s = new Scanner(new File("levels/level" + level + ".txt"));
			while (s.hasNextLine()) {
				for (int y = 0; y != HEIGHT; y++) {
					String temp = s.nextLine();
					for (int x = 0; x != WIDTH; x++) {
						char obj = temp.charAt(x);
						createObject(x, y, obj);
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("No levels found!");
		}
	}

	private void createObject(int x, int y, char obj) {
		switch (obj) {
		case PAREDE:
			objects.add(new Parede(new Point2D(x, y)));
			break;
		case BATERIA:
			objects.add(new Bateria(new Point2D(x, y)));
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case BURACO:
			objects.add(new Buraco(new Point2D(x, y)));
			break;
		case CAIXOTE:
			objects.add(new Caixote(new Point2D(x, y)));
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case ALVO:
			objects.add(new Alvo(new Point2D(x, y)));
			break;
		case CHAO:
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case SMALLSTONE:
			objects.add(new SmallStone(new Point2D(x, y)));
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case BIGSTONE:
			objects.add(new BigStone(new Point2D(x, y)));
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case EMPILHADORA:
			player = new Empilhadora(new Point2D(x, y));
			objects.add(player);
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case GELO:
			objects.add(new Gelo(new Point2D(x, y)));
			break;
		case MARTELO:
			objects.add(new Martelo(new Point2D(x, y)));
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case PAREDEPARTIDA:
			objects.add(new ParedePartida(new Point2D(x, y)));
			objects.add(new Chao(new Point2D(x, y)));
			break;
		case PORTAL:
			objects.add(new Portal(new Point2D(x, y)));
			break;
		}
	}

	private void userInterface() {
		playerName = JOptionPane.showInputDialog(null, "What's your name?", "Insert Player Name",
				JOptionPane.QUESTION_MESSAGE);
		if (playerName == null)
			System.exit(0);

		if (playerName.matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(null, "Please type only letters", "Error in player name",
					JOptionPane.ERROR_MESSAGE);
			userInterface();
		} else
			JOptionPane.showMessageDialog(null,
					"You win when all the targets are full with boxes. \nUse arrows to move. \nPress R to restart the level. \nPress Q to quit the game",
					"Instructions", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void update(Observed arg0) {
		try {
			int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();
			if (lastKeyPressed == KeyEvent.VK_R) {
				assembleLevel();
				showInfo();
				System.out.println("Level restared!");
			}
			if (lastKeyPressed == KeyEvent.VK_Q)
				System.exit(0);

			Direction dir = Direction.directionFor(lastKeyPressed);
			if (Direction.isDirection(lastKeyPressed))
				if (player != null) {
					player.move(dir);
				}

			showInfo();
			checkTargets();
		} catch (Exception e) {// Controlo de excecoes - para nao rebentar o programa se carregar numa tecla
								// indevida sem querer
		}
	}

	public void showInfo() {
		ImageMatrixGUI.getInstance().setStatusMessage("    Level: " + getLevel() + "    Energy: " + player.getEnergy()
				+ "%" + "    Moves " + player.getMoves());
	}

	private void checkTargets() {
		int nrTargets = 0;
		int targetWithBox = 0;
		for (AbstractObjects obj : objects)
			if (obj instanceof Alvo) {
				nrTargets++;
				if (((Alvo) obj).isFull(obj.getPosition()))
					targetWithBox++;
			}

		if (nrTargets == targetWithBox && level < MUMBEROFLEVELS) {
			if (level == MUMBEROFLEVELS - 1) // chegou ao fim dos niveis
				wonGame();

			else {// passa de nivel
				writeScore();
				level++;
				assembleLevel();
				showInfo();
			}
		}
	}

	private void wonGame() {
		writeScore();
		JOptionPane.showMessageDialog(null, "Congrats " + playerName + ", you won!", "You Win",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	private void writeScore() {
		Scores s = new Scores(level);
		s.writeFile((String) (playerName + " - " + player.getMoves()));
	}

	public void removeObject(AbstractObjects obj) {
		objects.remove(obj);
		ImageMatrixGUI.getInstance().removeImage(obj);
	}

	public void restartLevel() {
		assembleLevel();
	}

	public List<AbstractObjects> objectsAtPosition(Point2D position) {
		return select(obj -> obj.getPosition().equals(position));
	}

	public List<AbstractObjects> getObjects() {
		return select(obj -> obj instanceof AbstractObjects);
	}

	public List<Movable> getMovableObjects() {
		return select(obj -> obj instanceof Movable);
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> select(Predicate<AbstractObjects> p) {
		List<T> selected = new ArrayList<T>();
		for (AbstractObjects obj : objects)
			if (p.test(obj))
				selected.add((T) obj);
		return selected;
	}

	public int getLevel() {
		return level;
	}

	public String getPlayerName() {
		return playerName;
	}

	public Empilhadora getPlayer() {
		return player;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

}