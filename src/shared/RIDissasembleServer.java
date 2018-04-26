package shared;

import models.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RIDissasembleServer extends Remote {
    String SERVER_NAME = "dissasembleServer";
    int PORT = 1099;

    void dismantleCar(Car car) throws RemoteException;

}
