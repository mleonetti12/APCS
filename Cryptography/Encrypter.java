// encrypt input file
import java.io.*;
import java.util.Scanner;
public class Encrypter
{
    public static void main(String[] args) throws IOException
    {
        Scanner reader =  new Scanner(System.in);
        System.out.println("Enter your file name: ");
        String f = reader.nextLine();
        Scanner input = new Scanner(new File(f));
        int asciiValue = 0;
        System.out.println("Enter the desired output file name: ");
        String n = reader.nextLine();
        PrintWriter writer = new PrintWriter(new File(n));
        while (input.hasNext())
        {
            String word = input.next();
            word = word.toLowerCase();
            for (int t = 0; t < word.length(); t++)
            {
                asciiValue = word.charAt(t);
                if (asciiValue >= 97)
                {
                    if (asciiValue <= 109)
                    {
                        asciiValue += 13;
                    }
                    else 
                    {
                        asciiValue -= 13;
                    }
                }
                writer.print((char)asciiValue);
            }
            writer.print(" ");
        }
        writer.close();
    }
}
