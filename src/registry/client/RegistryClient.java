package registry.client;

import models.Car;
import shared.RIDaoServer;
import shared.RIRegistryServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(RIDaoServer.PORT);
        RIRegistryServer registryServer = (RIRegistryServer) registry.lookup(RIRegistryServer.SERVER_NAME);

        // test data
        Car ferrari = new Car(592920, "Ferrari", 2000);
        Car bugatti = new Car(643123, "Bugatti", 1605);
        Car kamaz = new Car(223164, "Kamaz", 5050);

        registryServer.registerCar(ferrari);
        registryServer.registerCar(bugatti);
        registryServer.registerCar(kamaz);

//        test data
//        Car Nissan = new Car(1, "Nissan", 700);
//        Car Mustang = new Car(2, "Mustang", 1500);
//        Car Belaz = new Car(3, "Belaz", 10000);
//
//        registryServer.registerCar(Nissan);
//        registryServer.registerCar(Mustang);
//        registryServer.registerCar(Belaz);
        
    }

}
