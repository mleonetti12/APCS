// make sure PDPlayer works
import java.util.Random;

public class PlayerTester1
{
    public static void main(String[] args)
    {
        final int NUMROUNDS = 200;
        final int NUMINSTANCES = 1000;
        int instance;
        for (instance = 0; instance < NUMINSTANCES; instance++)
        {
            PDPlayer player = new max();
            String[] history = new String[NUMROUNDS];
            try
            {
                int round = 0;
                String playerChoice = player.chooseCorD("");
                history[round] = playerChoice;
                if ( !(playerChoice.equals("c") || playerChoice.equals("d")) )
                {
                    throw new RuntimeException("Player must return \"c\" or \"d\".");
                }
                Random generator = new Random();
                for (round = 1; round < NUMROUNDS; round++)
                {
                    int choice = generator.nextInt(2);
                    playerChoice = "";
                    if (choice == 0)
                    {
                        playerChoice = player.chooseCorD("c");
                        history[round] = "c" + playerChoice;
                    }
                    else
                    {
                        playerChoice = player.chooseCorD("d");
                        history[round] = "d" + playerChoice;
                    }
                    if ( !(playerChoice.equals("c") || playerChoice.equals("d")) )
                    {
                        throw new RuntimeException("Player must return \"c\" or \"d\".");
                    }
                }
            }
            catch(RuntimeException e)
            {
                System.out.print("Failure at... ");
                System.out.println("Instance #: " + instance);
                System.out.println("Round history follows:");
                for (int round = 0; round < NUMROUNDS; round++)
                {
                    System.out.println("Round: " + round + "  " + history[round]);
                }
                break;
            }
        }
        if (instance == NUMINSTANCES)
        {
            System.out.println("Player Okay!!");
        }
    }
}
