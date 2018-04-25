package shared;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface CarDAO extends Remote {
   CarDTO insertCar(int chassisNo, String model, double weight) throws RemoteException;
   Collection<CarDTO> readAllCars() throws RemoteException;
   void updateCar(CarDTO car) throws RemoteException;
   void deleteCar(CarDTO car) throws RemoteException;
   CarDTO readCar(int chassisNo) throws RemoteException;
}
