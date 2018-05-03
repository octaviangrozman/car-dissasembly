package shared;

import data.DAOServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DatabaseLocator {

    public static DAOServer getDatabaseServer() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(RIDaoServer.PORT);
        try {
            return (DAOServer) registry.lookup(RIDaoServer.SERVER_NAME);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }


}
