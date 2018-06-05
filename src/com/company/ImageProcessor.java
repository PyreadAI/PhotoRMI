package com.company;

import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImageProcessor extends Remote {
    void setImage() throws RemoteException;
    BufferedImage getImage();
}