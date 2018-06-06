
import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImageProcessor extends Remote {
//    SerializableImage serializeImage(BufferedImage bi) throws RemoteException;
    String sendSerializableImage(SerializableImage si) throws RemoteException;
    SerializableImage recvSerializableImage() throws RemoteException;
}