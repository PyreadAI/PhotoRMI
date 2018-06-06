package com.company;

import java.awt.image.BufferedImage;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(10001);
            ImageProcessor stub = (ImageProcessor) registry.lookup("Hello");
            Object serializableImage = stub.recvSerializableImage();
            System.out.println("response: " + serializableImage);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}