package domino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Domino {

    public static void main(String[] args) {
        Player player1 = new Player("Dan");
        Player player2 = new Player("Jane");
//        Player player3 = new Player("John");
//        Player player4 = new Player("Bill");

        List<Player> players = new ArrayList<Player>();
        Collections.addAll(players, player1, player2);

        Game domino = new Game(players);

        while (!domino.isFinished) {
            domino.nextStep();
        }
        if (domino.fish) {
            System.out.println("\nGame was played, but without winners - fish!");
        } else {
            domino.getWinner();
        }
    }
}
