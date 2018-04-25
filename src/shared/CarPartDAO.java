package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface CarPartDAO extends Remote {
   CarPartDTO insertCarPart(double weight, int chassisNo, String model, PartType type) throws RemoteException;
   Collection<CarPartDTO> readAllCarParts() throws RemoteException;
   void updateCarPart(CarPartDTO carPart) throws RemoteException;
   void deleteCarPart(CarPartDTO carPart) throws RemoteException;
   CarPartDTO readCarPart(int id) throws RemoteException;
}


