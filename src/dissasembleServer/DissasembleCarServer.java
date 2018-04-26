package dissasembleServer;

import models.Car;
import models.CarPart;
import shared.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class DissasembleCarServer extends UnicastRemoteObject
      implements RIDissasembleFacility
{

   private CarPartDAO carPartDAO;
   private PalletDAO palletDAO;
   private Random random = new Random();

   public DissasembleCarServer(RIDaoServer databaseServer)
         throws RemoteException
   {
      carPartDAO = (CarPartDAO) databaseServer;
      palletDAO = (PalletDAO) databaseServer;
   }

   @Override
   public void dismantleCar(Car car) throws RemoteException
   {
      ArrayList<PartType> partTypes = getRandomPartTypes();

      for (PartType partType : partTypes)
      {
         double partWeight = getRandomWeight();
         carPartDAO.insertCarPart(partWeight, car.getChassisNo(),
               car.getModel(), partType);
         // TODO: add part to pallet
         // palletDAO.insertPallet(500, partType);
      }

   }

   private ArrayList<PartType> getRandomPartTypes()
   {
      ArrayList<PartType> resultParts = new ArrayList<>();

      PartType[] allParts = PartType.values();
      Set<Integer> partsSet = new HashSet<>();

      // generate some unique random ids from 0 to allParts.length
      while (partsSet.size() < allParts.length)
      {
         int partIndex = random.nextInt(allParts.length - 1);
         if (partsSet.contains(partIndex))
            continue;
         partsSet.add(partIndex);
      }

      // add partTypes to result
      for (int partIndex : partsSet)
      {
         resultParts.add(allParts[partIndex]);
      }

      return resultParts;
   }

   public double getRandomWeight()
   {
      return random.nextDouble() * 100;
   }

   public static void main(String[] args) throws RemoteException
   {
      RIDissasembleFacility branchBankServer = new DissasembleCarServer(
            DatabaseLocator.getDatabaseServer());
      Registry registry = LocateRegistry.getRegistry(RIDissasembleFacility.PORT);
      registry.rebind(RIDissasembleFacility.SERVER_NAME, branchBankServer);
      System.out.println("Dissasemble server started...");
   }
}
