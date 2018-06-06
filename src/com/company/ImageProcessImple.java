package com.company;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.nio.Buffer;


public class ImageProcessImple {
    public static void main(String args[]) throws IOException{
        File f = null;
        try{
            URL path = ImageProcessImple.class.getResource("Berry.JPG");
            f = new File(path.getFile());
            BufferedImage img = ImageIO.read(f);
            //For testing purpose, remove later
            BufferedImage img_manipulated = manipulateImage(img);

            File output = new File("D://Img//OutputBerry.jpg");
            ImageIO.write(img_manipulated, "jpg", output);

            System.out.println("success");

        }catch (Exception e){
            //need to adjust to client side rendering
            System.out.println("Error" + e);
        }
    }
    private static BufferedImage manipulateImage(BufferedImage img){
        BufferedImage img_mirrored = null;
        try{
            int img_width = img.getWidth();
            int img_height = img.getHeight();
            img_mirrored = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < img_width; x++){
                for(int y = 0; y < img_height; y++){
                    Color c = new Color(img.getRGB(x,y));
                    boolean checker = analyzePixel(c);
                    Color myPixelColor = checker ? new Color(0,0,0): new Color(255, 255, 255);
                    img_mirrored.setRGB(x, (img_height-1)-y, myPixelColor.getRGB());
                }
            }

        }catch (Exception e){
            System.out.println("Error occurred when processing image "  + e);
        }
        return img_mirrored;
    }
    private static boolean analyzePixel(Color c){
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int sum =  red + green + blue;
        if(sum == 0){
            return false;
        }else{
            float red_percentage = (red*100)/sum;
            float blue_percentage = (blue*100)/sum;
            return red_percentage > 50 || blue_percentage > 50;
        }

    }
}
