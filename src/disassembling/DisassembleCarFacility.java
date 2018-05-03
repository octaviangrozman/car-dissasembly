package disassembling;

import models.Car;
import shared.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static java.lang.System.out;

public class DisassembleCarFacility extends UnicastRemoteObject implements RIDisassembleFacility {

    private static final int MAX_PALLET_WEIGHT_CAPACITY_KG = 500;
    private static final int MAX_CAR_PART_WEIGHT_KG = 100;
    private CarPartDAO carPartDAO;
    private PalletDAO palletDAO;
    private Random random = new Random();


    private DisassembleCarFacility(RIDaoServer databaseServer) throws RemoteException {
        super();
        carPartDAO = (CarPartDAO) databaseServer;
        palletDAO = (PalletDAO) databaseServer;
    }

    @Override
    public void dismantleCar(Car car) throws RemoteException {
        ArrayList<PartType> partTypes = getRandomPartTypes();

        for(PartType partType : partTypes) {
            double partWeight = getRandomWeight();

            CarPartDTO carInDb = carPartDAO.insertCarPart(partWeight, car.getChassisNo(), car.getModel(), partType);

            carPartDAO.updateCarPartReferenceToPallet(carInDb.getId(), getPalletNo(partType, partWeight));
        }
    }

    private int getPalletNo(PartType partType, double partWeight) throws RemoteException {
        PalletDTO[] pallets = (PalletDTO[]) palletDAO.readPalletsByType(partType).toArray();
        for (PalletDTO pallet : pallets) {
            double currentWeight = palletDAO.getPalletCurrentWeight(pallet.getPalletNo());
            if (currentWeight + partWeight < pallet.getWeightCapacity()) {
                return pallet.getPalletNo();
            }
        }
        // if no free pallet found, create a new one, and add part to it
        PalletDTO pallet = palletDAO.insertPallet(MAX_PALLET_WEIGHT_CAPACITY_KG , partType);
        return pallet.getPalletNo();
    }

    private ArrayList<PartType> getRandomPartTypes() {
        ArrayList<PartType> resultParts = new ArrayList<>();

        PartType[] allParts = PartType.values();
        Set<Integer> partsSet = new HashSet<>();

        int numberOfParts = random.nextInt(allParts.length - 1);

        // generate some unique random ids from 0 to allParts.length
        while (partsSet.size() <= numberOfParts) {
            int partIndex = random.nextInt(allParts.length - 1);
            if (partsSet.contains(partIndex)) continue;
            partsSet.add(partIndex);
        }

        // add partTypes to result
        for (int partIndex : partsSet) {
            resultParts.add(allParts[partIndex]);
        }

        return resultParts;
    }

    private double getRandomWeight() {
        return random.nextDouble() * MAX_CAR_PART_WEIGHT_KG;
    }


    public static void main(String[] args) throws RemoteException {
        RIDisassembleFacility disassembleCarFacility = new DisassembleCarFacility(DatabaseLocator.getDatabaseServer());
        Registry registry = LocateRegistry.getRegistry(RIDisassembleFacility.PORT);
        registry.rebind(RIDisassembleFacility.SERVER_NAME, disassembleCarFacility);
        out.println("Disassemble server started...");
    }
}