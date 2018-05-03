package shared;

import models.Car;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RIRegistryServer extends Remote {
    public static final String SERVER_NAME = "registryServer";

    void registerCar(Car car) throws RemoteException;
}
