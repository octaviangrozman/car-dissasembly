package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface CarPartDAO extends Remote {
   CarPartDTO create(double weight, int chassisNo, String model, PartType type) throws RemoteException;
   Collection<CarPartDTO> readAll() throws RemoteException;
   void update(CarPartDTO carPart) throws RemoteException;
   void delete(CarPartDTO carPart) throws RemoteException;
   CarPartDTO read(int id) throws RemoteException;
}


