package com.company;
import java.io.*;
import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.imageio.ImageIO;
//import com.company.SerializableImage;

public class ImageProcessorImpl extends UnicastRemoteObject implements ImageProcessor {


    private BufferedImage img;

    protected ImageProcessorImpl() throws RemoteException {
        super();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(1);
        ImageIO.write(img, "jpg", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        final int imageCount = in.readInt();
        img = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);

    }

//    @Override
//    public SerializableImage serializeImage(BufferedImage bi) {
//        try{
//            SerializableImage si = new SerializableImage();
//            si.setImage(bi);
//            return si;
//        }catch(Exception e){
//            return null;
//        }
//
//    }

    @Override
    public String sendSerializableImage(SerializableImage si) {
        BufferedImage bi = si.getImage();
        if(bi == null){
            return "No image get sent to the server";
        }else{
            this.img = bi;
            return "Image sent successful";
        }
    }

    @Override
    public SerializableImage recvSerializableImage() {
        try{
            SerializableImage si = new SerializableImage();
            si.setImage(this.img);
            return si;
        }catch (Exception e){
            return null;
        }
    }
}
