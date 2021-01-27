package domino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {

    private boolean hasSuitableStone;
    private String name;
    public List<Stone> stones = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public final List<Stone> suitableStones = new ArrayList<>();

    //player makes step
    public void playerStep(Game game) {
        //if first step -> just choose stone from all stones
        if (game.table.isEmpty()) {
            chooseStone(game);
            return;
        }

        //get left and right elements
        var stoneLeftSide = game.table.getFirst().leftValue;
        var stoneRightSide = game.table.getLast().rightValue;

        //search suitable stones in a set
        for (Stone stone : stones) {
            hasSuitableStone = checkForSuitability(stoneLeftSide, stoneRightSide, stone);
        }
        //if not found -> peek new stone from market and check
        if (suitableStones.isEmpty()) {
            while (!hasSuitableStone) {


                //if market is empty
                if (game.market.isEmpty()) {
                   return;
                }

                //check if picked
                var pickedStone = game.marketPeek(this);
                hasSuitableStone = checkForSuitability(stoneLeftSide, stoneRightSide, pickedStone);

            }
        } else hasSuitableStone = true;

        chooseStone(game);

    }

    private boolean checkForSuitability(int left, int right, Stone stoneToCheck) {
        // there is no difference if a one or lots of stones on a table
        // we only check the far left and far right elements
        // if there is only one element -> left.equals(right)
        if (left == stoneToCheck.rightValue || left == stoneToCheck.leftValue
                || right == stoneToCheck.leftValue || right == stoneToCheck.rightValue) {
            suitableStones.add(stoneToCheck);
            return true;
        } else {
            return false;
        }
    }

    private void chooseStone(Game game) {

        //show all player`s stones
        System.out.println(String.format("\nIt`s %s`s turn!", this.name));
        System.out.println(String.format("All %s`s stones!", this.name));
        for (Stone stone: stones) {
            System.out.print(stone.leftValue + "|" + stone.rightValue + "  ");
        }
        System.out.println("\nSuitable stones, choose one, entering number:");

        var in = new Scanner(System.in);

        //if table is empty (0 items) -> just add what player want
        if (game.table.isEmpty()) {
            for (Stone stone: stones) {
                System.out.print(stone.leftValue + "|" + stone.rightValue + "  ");
            }
            //input a number of stone to choose
            var index = in.nextInt();

            game.table.add(stones.get(index));

            //remove stone from player collection
            this.stones.remove(index);
            //clear suitable stones
            this.suitableStones.clear();

            return;
        }

        //show suitable stones
        for (Stone stone: suitableStones) {
            System.out.print(stone.leftValue + "|" + stone.rightValue + "  ");
        }

        //***should be checked for correct number***

        //input number
        var index = in.nextInt();

        //if table has >= 1 items on a table
        //analysing player`s choice

        var stone = suitableStones.get(index);


        //if his stone should be added from left
        if (stone.rightValue == game.table.getFirst().leftValue) {
            game.table.addFirst(stone);
        }
        else if (stone.leftValue == game.table.getFirst().leftValue) {
            var temp = stone.rightValue;
            stone.rightValue = stone.leftValue;
            stone.leftValue = temp;
            game.table.addFirst(stone);
        }
        //or from right
        else if (stone.leftValue == game.table.getLast().rightValue){
            game.table.addLast(stone);
        }
        else  if (stone.rightValue == game.table.getLast().rightValue) {
            var temp = stone.rightValue;
            stone.rightValue = stone.leftValue;
            stone.leftValue = temp;
            game.table.addLast(stone);
        }

        //remove stone from player collection
        this.stones.remove(suitableStones.get(index));
        //clear suitable stones
        this.suitableStones.clear();

        //reset state
        hasSuitableStone = false;
    }
}
