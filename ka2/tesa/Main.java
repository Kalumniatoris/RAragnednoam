package ka2.tesa;

import java.util.Random;

import ka2.tesa.elements.Brandom;
import ka2.tesa.elements.Player;

public class Main {

	public static void main(String[] args) {
		
		Random rnd = new Random();
		
		
		
		int size=rnd.nextInt(100)+7;
		int turns = 1000;
		Board arena = new Board(size);
		//add players here
		Brandom brandom = new Brandom(1, "Brandom");
		arena.addPlayer(brandom);
		System.out.println(brandom.getPosX()+" "+brandom.getPosY());
		
		
		
		
		//
		for (int i = 0; i < turns; i++) {
			arena.turn();
		}
		
		for (Player p  : arena.getPlayers()) {
			System.out.println(p.getId()+" "+p.getName()+": "+arena.getScore(p));
		}
	}

}
