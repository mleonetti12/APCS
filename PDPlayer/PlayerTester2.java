// play against the computer
import java.util.Scanner;

public class PlayerTester2
{
    public static void main(String[] args)
    {
        final int NUMROUNDS = 200;
        PDPlayer computer = new max();
        boolean algorithmGoesFirst = true;

        String[] history = new String[NUMROUNDS];

        Scanner input = new Scanner(System.in);
        for (int round = 0; round < NUMROUNDS; round++)
        {
            history[round] = "";
            if (algorithmGoesFirst)
            {
                String lastMove = "";
                if (round > 0)
                {
                    lastMove = history[round - 1].substring(1);
                }
                history[round] += computer.chooseCorD(lastMove);
            }
            System.out.println("Game play thus far:");
            for (int roundNum = 0; roundNum <= round; roundNum++)
            {
                System.out.println("Round: " + roundNum + "  " + history[roundNum]);
            }
            System.out.print("\nMake your choice (\"c\" or \"d\" or \"q\" to quit): ");
            String humanChoice = input.nextLine();
            if (humanChoice.equals("q"))
            {
                break;
            }
            if ( !(humanChoice.equals("c") || humanChoice.equals("d")) )
            {
                throw new RuntimeException("Player must return \"c\" or \"d\".");
            }
            history[round] += humanChoice;
            if (!algorithmGoesFirst)
            {
                history[round] += computer.chooseCorD(humanChoice);
            }
        }
    }
}
