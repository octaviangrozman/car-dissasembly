package shared;

import models.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DissasembleServer extends Remote {
    String SERVER_NAME = "dissasembleServer";
    int PORT = 1099;

    void dismantleCar(int chassisNo) throws RemoteException;
    List<CarPart> getPartsByModel(String model) throws RemoteException;
    List<CarPart> getPartsByType(String partType) throws RemoteException;

}
