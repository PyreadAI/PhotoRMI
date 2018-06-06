


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.Serializable;

// Class that encapsulates bufferedImage that can be serializable
public class SerializableImage implements Serializable {
    private byte[] ib;

    public SerializableImage() throws IOException
    {
        super();
        this.ib = new byte[0];
        
    }

    public final void setImage(BufferedImage in) throws IOException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(in, "jpg", outputStream);
        this.ib = outputStream.toByteArray();
    }

    public BufferedImage getImage()
    {
        try {
            return ImageIO.read(new ByteArrayInputStream(this.ib));
        } catch (Exception io)
        {
            return null;
        }
    }


}
