package pt.iscte.dcti.poo.sokoban.starter;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;

import java.util.Collections;

public class Scores {
	private List<String> scores = new ArrayList<String>();
	private final int level;

	public Scores(int level) {
		this.level = level;
	}

	// Guarda tudo o que esta escrito no ficheiro na ArrayList de scores
	private void readFile() {
		try {
			Scanner s = new Scanner(new FileReader("Scores/level" + level + ".txt"));
			while (s.hasNext()) {
				String temp = s.nextLine();
				scores.add(temp);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Previous scores not found! \nnew file created!");
		}
	}

	// Insere o score de cada nivel passado na ArrayList de scores excepto se o
	// jogador ja tenha feito um score igual
	private void insertNewScore(String newScore) {
		int a = 0; // contador para descobrir se ja existe o score feito
		for (String i : scores)
			if (i.equals(newScore))
				a++;
		if (a < 1) // se o score nao existir guarda-o na ArrayList de scores
			scores.add(newScore);
		// da sort da ArrayList de scores de modo a que o score mais baixo seja
		// apresentado primeiro, indepentemente de quem seja o player
		Collections.sort(scores, (scr1, scr2) -> Integer.parseInt(scr1.replaceAll("\\D+", ""))
				- Integer.parseInt(scr2.replaceAll("\\D+", "")));
	}

	// Cria um ficheiro de scores por nivel caso ja nao esteja criado. Imprime nesse
	// ficheiro o conteudo da ArrayList de scores
	public void writeFile(String newScore) {
		readFile();
		insertNewScore(newScore);
		try {
			PrintWriter writer = new PrintWriter(new File("Scores/level" + level + ".txt"));
			scores.forEach(scr -> writer.println(scr));
			writer.close();

		} catch (Exception e) {
			System.out.println("Unable to write the file!");
		}

	}

}
