import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;

public class RIImplement extends UnicastRemoteObject implements ImageProcessor {
    private BufferedImage img;

    RIImplement() throws RemoteException{
        super();
    }

    public int add(int x, int y){
        return x + y;
    }
    public String sendSerializableImage(SerializableImage si){
        BufferedImage bi;
        bi = si.getImage();
        if (bi == null) {
            return "Sending failed";
        }else{
            try{
                setManipulatedImage(bi);
                return "Sending sucessful";
            }catch(Exception e){
                return "Sending sucessful but Manipulation failed";
            }   
        }
    }

    public SerializableImage recvSerializableImage(){
       
        try{
            SerializableImage si_to_recv = new SerializableImage();
            si_to_recv.setImage(this.img);
            return si_to_recv;
            
        }catch(Exception e){
            //let client handle the error
            System.out.println("recv failed");
            return null;
        }
       
    }

    public void setManipulatedImage(BufferedImage bi) throws Exception{
        //For testing purpose, remove later
        BufferedImage img_manipulated = manipulateImage(bi);
        this.img = img_manipulated;
        File output = new File("D://Img//OutputBerry.jpg");
        ImageIO.write(img_manipulated, "jpg", output);

        System.out.println("set manipulated image success");
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
