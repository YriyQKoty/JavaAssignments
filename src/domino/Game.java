package domino;

import java.util.*;

public class Game {

    List<Stone> market = new Vector<>();
    private List<Player> players;
    public Deque<Stone> table = new ArrayDeque<>();

    private int numberOfCurrentPlayer;
    public boolean fish;

    //create game
    public Game(List<Player> players) {
        this.players = players;
        initMarket();
        seedSets();
        setFirstPlayer();
    }

    //randomly set
    private void setFirstPlayer() {
        numberOfCurrentPlayer = new Random().nextInt(players.size() - 1);
    }

    //set players collection
    private void seedSets() {

        Collections.shuffle(market);
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 7; j++) {
                peekStone(players.get(i));
            }
        }
    }

    //initialise market (28 stones)
    private void initMarket() {
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                var stone = new Stone(i, j);
                this.market.add(stone);
            }
        }
    }

    public boolean isFinished;

    //next step of a game
    public void nextStep() {
        isFinished = gameOver();
        if (isFinished) return;

        this.players.get(numberOfCurrentPlayer).playerStep(this);
        showTable();

        //change player
        numberOfCurrentPlayer = (numberOfCurrentPlayer + 1) % players.size();

    }

    //show current table
    private void showTable() {
        System.out.println("\nTable:");
        for (Stone stone : table) {
            System.out.print("<--|" + stone.leftValue + "|" + stone.rightValue + "|-->");
        }
    }

    //game over if a player has no stones in collection or ALL players have no suitable stones
    private boolean gameOver() {
        if (players.get(numberOfCurrentPlayer).stones.isEmpty()) {
            return true;
        } else if (market.isEmpty()) {
            fish = false;
            for (Player player : players) {
                if (player.suitableStones.isEmpty()) {
                    fish = true;
                } else fish = false;
            }

            return fish;
        }

        return false;
    }

    //***should be implemented***
    public void getWinner() {
        for (Player player : players) {

        }
    }

    //pick stone and add to a player collection -> remove from market
    private void peekStone(Player player) {
        var index = new Random().nextInt(market.size());
        player.stones.add(market.get(index));
        market.remove(index);
    }

    //method for adding stone into the end and returning last stone in collection
    public Stone marketPeek(Player player) {
        peekStone(player);
        var lastIndex = player.stones.size() - 1;
        return player.stones.get(lastIndex);
    }
}
