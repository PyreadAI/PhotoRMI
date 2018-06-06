
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;


public class ExampleClient {


    public static void main(String[] args) {

//        String host = (args.length < 1) ? null : args[0];
        try {
            ImageProcessor ip = (ImageProcessor) Naming.lookup("rmi://localhost:10001/create");
            //Client rendering here
            // Scanner sc = new Scanner(System.in);
            // int x;
            System.out.println("Enter");
            // x = sc.nextInt();
            // st.createImage();
            //
            SerializableImage si = createSerializableImage("Berry.JPG");
            //Error handling: not valid file
            if(si == null) System.out.println("not valid file");
            //Error handling: send serialzable image failed
            ip.sendSerializableImage(si);
            // System.out.println("the sum: " + st.add(x,y));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    public static SerializableImage createSerializableImage(String str){
        File f = null;
        try{
            URL path = ExampleClient.class.getResource(str);
            f = new File(path.getFile());
            BufferedImage img = ImageIO.read(f);
            SerializableImage si = new SerializableImage();
            si.setImage(img);
            System.out.println("success");
            return si;

        }catch (Exception e){
            //Error handling: Cannot transform to SI
            // System.out.println("Error" + e);
            return null;
        }
    }
}
