// decrypt input file
import java.io.*;
import java.util.Scanner;
public class Decrypter
{
    public static void main(String[] args) throws IOException
    {
        Scanner reader =  new Scanner(System.in);
        System.out.println("Enter your file name: ");
        String f = reader.nextLine();
        Scanner input = new Scanner(new File(f));
        double[] shifts = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074};
        double[] errors = new double[26];
        int asciiValue = 0;
        String starter = "";
        System.out.println("Enter the desired output file name: ");
        String n = reader.nextLine();
        PrintWriter writer = new PrintWriter(new File(n));
        // read in file
        while (input.hasNext())
        {
            String word = input.next();
            word = word.toLowerCase();
            starter += (word + " ");
        }
        // determine frequencies of each char
        for (int i = 0; i < 26; i++)
        {   
            double count = 0;
            double[] frequencies = new double[26];

            for (int t = 0; t < starter.length(); t++)
            {
                asciiValue = starter.charAt(t);
                if (asciiValue >= 97 && asciiValue <= 122)
                {
                    asciiValue -= i;
                    if (asciiValue < 97)
                    {
                        asciiValue +=26;
                    }
                    frequencies[asciiValue - 97]++;
                    count++;
                }
            }
            double margin = 0;
            for (int j = 0; j < 26; j++)
            {
                frequencies[j] /= count;
                margin += (Math.abs(frequencies[j] - shifts[j]))/shifts[j];
            }
            errors[i] = margin;
            count = 0;
        }
        // find smallest error and determine how far char's ascii values have been shifted
        double smallerror = errors[0];
        int diff = 0;
        for (int k =0; k < errors.length; k++)
        {
            if (errors[k] < smallerror)
            {
                smallerror = errors[k];
                diff = k;
            }
        }
        System.out.println("This text was shifted by: " + diff + " letters.");
        // use shift in ascii values to decode and output file
        int asciiValue2 = 0;
        Scanner input2 = new Scanner(new File(f));
        while (input2.hasNext())
        {
            String word2 = input2.next();
            word2 = word2.toLowerCase();
            for (int m = 0; m < word2.length(); m++)
            {
                asciiValue2 = word2.charAt(m);
                if (asciiValue2 >= 97 && asciiValue <= 122)
                {
                    asciiValue2 -= diff;
                    if (asciiValue2 < 97)
                    {
                        asciiValue2 += 26;
                    }
                }
                writer.print((char)asciiValue2);
            }
            writer.print(" ");
        }
        writer.close();
    }
}