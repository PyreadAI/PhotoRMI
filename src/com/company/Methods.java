package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Methods extends Remote {
    String sayHello() throws RemoteException;
}