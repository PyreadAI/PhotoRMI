

import java.rmi.*;

public class ExampleServer {


    public static void main(String args[]) {

        try {
            ImageProcessor ip = new ImageProcessorImpl();
            Naming.bind("rmi://localhost:10001/create", ip);
            System.out.println("server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}