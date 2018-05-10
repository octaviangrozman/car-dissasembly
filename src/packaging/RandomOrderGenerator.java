package packaging;

import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.PresetOrder;
import packaging.assembly.orderModels.TypeOrder;
import shared.CarDAO;
import shared.CarPartDAO;
import shared.CarPartDTO;
import shared.PartType;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

class RandomOrderGenerator implements IOrderGenerator
{
   private final Random random = new Random();
   private final CarPartDAO carPartDAO;
   private List<String> carModels;
   private final int maxPartsPerPackageLimit;

   public RandomOrderGenerator(int maxPartsPerPackageLimit, CarDAO carDAO, CarPartDAO carPartDAO) throws RemoteException {
      this.maxPartsPerPackageLimit = maxPartsPerPackageLimit;
      this.carPartDAO = carPartDAO;
      carModels = carDAO.readAllCarModels();
   }

   @Override
   public Order generateOrder() {
      boolean shouldGenerateTypeOrder = random.nextBoolean();

      if (shouldGenerateTypeOrder) {
         return generateTypeOrder();
      }

      return generatePresetOrder();
   }

   private Order generatePresetOrder() {
      String carModel = carModels.get(random.nextInt(carModels.size()));
      PresetOrder.Preset preset = PresetOrder.Preset.values()[random.nextInt(PresetOrder.Preset.values().length)];

      // checking if preset parts are present in database, return null if not present
      for (PartType partType : preset.partTypes) {
         try {
            CarPartDTO carPart = carPartDAO.readCarPartByTypeAndModel(partType, carModel);
            if (carPart == null || carPart.getPackageNo() != 0) return null;
         } catch (RemoteException e) {
            e.printStackTrace();
         }
      }

      return new PresetOrder(carModel, preset);
   }

   private Order generateTypeOrder() {
      PartType partType = null;
      int numberOfParts = 0;
      while (partType == null) {
         PartType partTypeToCheck = PartType.values()[random.nextInt(PartType.values().length)];
         try {
            int partsCountAvailable = this.carPartDAO.readCarPartCount(partTypeToCheck);
            if (partsCountAvailable == 0) continue;
            partType = partTypeToCheck;
            // if there are less parts available than MAX_PARTS than we reduce max to number of available parts
            numberOfParts = random.nextInt(partsCountAvailable < maxPartsPerPackageLimit ? partsCountAvailable : maxPartsPerPackageLimit ) + 1;
         } catch (RemoteException e) {
            e.printStackTrace();
         }
      }
      return new TypeOrder(partType, numberOfParts);
   }

}
