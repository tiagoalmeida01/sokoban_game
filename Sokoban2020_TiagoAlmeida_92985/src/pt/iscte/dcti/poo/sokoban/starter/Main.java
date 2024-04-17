package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {

	public static void main(String[] args) {
		ImageMatrixGUI.setSize(SokobanGame.getWidth(), SokobanGame.getHeight());
		SokobanGame s = SokobanGame.getInstance(); // singleton
		ImageMatrixGUI.getInstance().registerObserver(s);
		ImageMatrixGUI.getInstance().go();
		ImageMatrixGUI.getInstance().setName("SOKOBAN | Player: " + SokobanGame.getInstance().getPlayerName());
		SokobanGame.getInstance().showInfo();
	}

}
