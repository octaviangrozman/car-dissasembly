package shared;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import data.DAOServer;

public class DatabaseLocator {

    public static RIDaoServer getDatabaseServer() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(RIDaoServer.PORT);
        try {
            return (RIDaoServer) registry.lookup(RIDaoServer.SERVER_NAME);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }


}
