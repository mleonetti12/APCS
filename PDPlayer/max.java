// my player character
public class max implements PDPlayer
{
    private String move;
    private String author;
    public max()
    {
        move = "d";
        author = "max leonetti";
    }
    public String getAuthor()
    {
        return author;
    }
    public String chooseCorD(String opponentsLastMove)
    {
        return move;
    }
    public String toString()
    {
        String str;
        str = "I always defect";
        return str;
    }
}