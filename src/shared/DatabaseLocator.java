package shared;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DatabaseLocator {

    public static DatabaseServer getDatabaseServer() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(DatabaseServer.PORT);
        try {
            return (DatabaseServer) registry.lookup(DatabaseServer.SERVER_NAME);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }


}
