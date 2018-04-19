package shared;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface CarDAO extends Remote {
   CarDTO create(int chassisNo, String model, double weight) throws RemoteException;
   Collection<CarDTO> readAll() throws RemoteException;
   void update(CarDTO car) throws RemoteException;
   void delete(CarDTO car) throws RemoteException;
   CarDTO read(int chassisNo) throws RemoteException;
}
