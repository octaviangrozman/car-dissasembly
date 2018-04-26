package shared;

import models.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RIDissasembleFacility extends Remote {
    String SERVER_NAME = "disassembling";
    int PORT = 1099;

    void dismantleCar(Car car) throws RemoteException;

}
