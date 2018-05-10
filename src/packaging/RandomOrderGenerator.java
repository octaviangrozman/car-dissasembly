package packaging;

import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.PresetOrder;
import packaging.assembly.orderModels.TypeOrder;
import shared.PartType;

import java.util.Random;

class RandomOrderGenerator implements IOrderGenerator
{
   private final Random RD = new Random();
   private final String[] CAR_MODELS = new String[] { "Nissan Almera",
         "BMW e39", "Honda Civic", "Ferrari", "Bugatti", "Kamaz" };
   private final int maxPartsPerPackageLimit;

   public RandomOrderGenerator(int maxPartsPerPackageLimit)
   {
      this.maxPartsPerPackageLimit = maxPartsPerPackageLimit;
   }

   @Override
   public Order generateOrder() {
      Order order;
      if (RD.nextInt(1) == 1)
         order = new TypeOrder(
               PartType.values()[RD.nextInt(PartType.values().length)],
               (RD.nextInt(maxPartsPerPackageLimit) + 1)
         );
      else
         order = new PresetOrder(
               CAR_MODELS[RD.nextInt(CAR_MODELS.length)],
               PresetOrder.Preset.values()[RD.nextInt(PresetOrder.Preset.values().length)]
         );
      return order;
   }

}
