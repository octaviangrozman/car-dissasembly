package dissasembleServer;

import models.CarPart;
import shared.DatabaseLocator;
import shared.DissasembleServer;
import shared.RIDaoServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DissasembleCarServer extends UnicastRemoteObject implements DissasembleServer {

    private RIDaoServer databaseServer;

    public DissasembleCarServer(RIDaoServer databaseServer) throws RemoteException {
        this.databaseServer = databaseServer;
    }

    @Override
    public void dismantleCar(int chassisNo) throws RemoteException {

    }

    @Override
    public List<CarPart> getPartsByModel(String model) throws RemoteException {
        return new ArrayList<>();
    }

    @Override
    public List<CarPart> getPartsByType(String partType) throws RemoteException {
        return new ArrayList<>();
    }

    public static void main(String[] args) throws RemoteException {
        DissasembleServer branchBankServer = new DissasembleCarServer(DatabaseLocator.getDatabaseServer());
        Registry registry = LocateRegistry.getRegistry(DissasembleServer.PORT);
        registry.rebind(DissasembleServer.SERVER_NAME, branchBankServer);
        System.out.println("Dissasemble server started...");
    }
}
