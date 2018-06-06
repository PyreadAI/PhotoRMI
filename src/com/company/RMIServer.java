package com.company;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String args[]) {

        try {
            ImageProcessorImpl ipi = new ImageProcessorImpl();
            Naming.rebind("RMIServer", ipi);
//            Server obj = new Server();
//            ImageProcessor stub = (ImageProcessor) UnicastRemoteObject.exportObject((Remote) obj, 0);
///           Bind the remote object's stub in the registry
//            Registry registry = LocateRegistry.getRegistry(10001);
//            registry.bind("RMIServer", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
