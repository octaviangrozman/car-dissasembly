package registry.server;

import disassembling.DisassembleCarFacility;
import models.Car;
import shared.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.System.out;

public class RegistryServer
        extends UnicastRemoteObject
        implements RIRegistryServer {

    private CarDAO carDAO;
    private RIDisassembleFacility disassembleFacility;

    public RegistryServer(RIDaoServer dbServer, RIDisassembleFacility disassembleFacility) throws RemoteException {
        super();
        this.carDAO = (CarDAO) dbServer;
        this.disassembleFacility = disassembleFacility;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {

       // Starting disassembleFacility because it needs to start before RegistryServer starts
       DisassembleCarFacility disassembleCarFacility = new DisassembleCarFacility(
             DatabaseLocator.getDatabaseServer());
       Registry registry = LocateRegistry.getRegistry(RIDisassembleFacility.PORT);
       registry.rebind(RIDisassembleFacility.SERVER_NAME, disassembleCarFacility);
       out.println("Disassemble server started...");


        //Start registry server
        RIRegistryServer registryServer = new RegistryServer(
                DatabaseLocator.getDatabaseServer(), disassembleCarFacility);
        registry.rebind(RIRegistryServer.SERVER_NAME, registryServer);
        out.println("Registry server started...");
    }

    @Override
    public void registerCar(Car car) throws RemoteException {
        carDAO.insertCar(new CarDTO(car.getChassisNo(), car.getModel(), car.getWeight()));
        disassembleFacility.dismantleCar(car);
    }
}
