// Collages and adds basic filters to an input image
import images.APImage;
import images.Pixel;
import java.util.Scanner;
public class PRO
{
    public static void main(String[] args)
    {
        Scanner stringreader = new Scanner(System.in);
        //alows the user to choose what image to collage
        System.out.println("Enter the name of your image: ");
        String i = stringreader.nextLine();
        APImage image = new APImage(i);
        int width = image.getImageWidth();
        int height = image.getImageHeight();
        int nwidth = (width*2);
        int nheight = (height*2);
        //making a new image with 4x the size of the original image
        APImage image2 = new APImage(nwidth, nheight);
        //making 3 clones of the original image to edit
        APImage clo1 = image.clone();
        APImage clo2 = image.clone();
        APImage clo3 = image.clone();

        for (int row=0; row < height; row++) //using a nested for loop to go through the pixels of the 1st clone and change colors to grayscale
        {
            for (int col=0; col < width; col++)
            {
                Pixel p = clo1.getPixel(col, row);
                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();
                int color = (int)(r * .299) + (int)(g * .587) + (int)(b * .114);

                p.setRed(color);
                p.setGreen(color);
                p.setBlue(color);
            }
        }
        
        for (Pixel q : clo2) //goes through clone 2 and applies a purple filter to the image
        {
            int red = q.getRed();
            // I left the green value in as an option if I ever wanted to change the type of filter
            int green = q.getGreen();
            int blue = q.getBlue();
            red += 255;
            green += 0;
            blue += 255;
            // so the values dont go over 255 or under 0
            if (red < 0)
            {
                red=0;
            }
            else if (red > 255)
            {
                red=255;
            }
            if (green < 0)
            {
                green=0;
            }
            else if (green > 255)
            {
                green=255;
            }
            if (blue < 0)
            {
                blue=0;
            }
            else if (blue > 255)
            {
                blue=255;
            }
            q.setRed(red);
            q.setGreen(green);
            q.setBlue(blue);
        }
        
        for (Pixel w : clo3) //going through the 3rd clone's pixels, and changing colors to negative
        {
            int red =w.getRed();
            int green =w.getGreen();
            int blue =w.getBlue();
            //gives the opposite color value of each pixel
            red=Math.abs(255-red); //abs so you dont get a negative value
            green=Math.abs(255-green);
            blue=Math.abs(255-blue);
            w.setRed(red);
            w.setGreen(green);
            w.setBlue(blue);
        }
        
        for (int row=0; row<height;row++) //nested for loop to go through pixels of new image
        //uses height and width of original image, since it applies all 4 pictures at once
        {
            for(int col=0; col<width; col++)
            {
                // get pixels from original image, and set to top left corner of new image
                Pixel z = image.getPixel(col,row);
                image2.setPixel(col, row, z); 
                //get pixels from 1st clone, then set pixels to top right of new image
                Pixel x = clo1.getPixel(col,row);
                image2.setPixel(col + width, row, x);
                //get pixels from 2nd clone and setting them to the bottom left corner of the new image
                Pixel c = clo2.getPixel(col, row);
                image2.setPixel(col, row+height, c);
                //get pixels from final clone and set to bottom right corner of new image
                Pixel v = clo3.getPixel(col, row);
                image2.setPixel(col+width, row+height, v);
            }
        }
        image2.draw();
    }
}