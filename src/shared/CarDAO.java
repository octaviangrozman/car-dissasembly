package shared;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface CarDAO extends Remote {
   CarDTO insertCar(CarDTO car) throws RemoteException;
   List<CarDTO> readAllCars() throws RemoteException;
   void updateCar(CarDTO car) throws RemoteException;
   void deleteCar(CarDTO car) throws RemoteException;
   CarDTO readCar(int chassisNo) throws RemoteException;
}
