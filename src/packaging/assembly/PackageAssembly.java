package packaging.assembly;

import models.CarPart;
import models.Package;
import packaging.Util;
import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.PresetOrder;
import packaging.assembly.orderModels.TypeOrder;
import shared.CarPartDAO;
import shared.CarPartDTO;
import shared.DatabaseLocator;

import java.rmi.RemoteException;

public class PackageAssembly implements IPackageAssembly
{
   private final CarPartDAO DB;
   private final int PAUSE_TIME;

   public PackageAssembly(CarPartDAO db, int pauseTime)
   {
      this.DB = db;
      this.PAUSE_TIME = pauseTime;
   }

   private Package assemblePackage(TypeOrder order)
   {
      boolean isLookingForMissingCarParts = false;
      CarPartDTO part;

      while (order.getParts().size() < order.quantity)
      {
         
         for (int i = order.getParts().size(); i < order.quantity; i++)
         {
            try
            {
               part = DB.readCarPartByType(order.getPartType());

               CarPart carPart = Util
                     .convertToModel(part);
               if (carPart == null)
               {
                  if (isLookingForMissingCarParts)
                  {
                     System.out.println("There is no Car Part of a type: "
                           + order.getPartType() + " . Waiting...");
                     waitForCarPart();
                  }
                  continue;
               }
               order.addPart(carPart);
               DB.updateCarPartReferenceToPackage(part.getId(),
                     order.getPackageNo());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
         isLookingForMissingCarParts = true;
      }

      return order;
   }



   private Package assemblePackage(PresetOrder order)
   {
      boolean isLookingForMissingCarParts = false;
      CarPartDTO part;

      while (order.getParts().size() < order.preset.partTypes.length)
      {
         for (int i = order.getParts()
               .size(); i < order.preset.partTypes.length; i++)
         {
            try
            {
               part = DB.readCarPartByTypeAndModel(order.preset.partTypes[i] ,
                     order.getCarModel());
               if (order.preset.partTypes[i] == null)
                  continue;
               CarPart carPart = Util.convertToModel(part);
               if (carPart == null)
               {
                  if (isLookingForMissingCarParts)
                  {
                     System.out.println("There is no Car Part of a type: "
                           + order.preset.partTypes[i] + " . Waiting...");
                     waitForCarPart();
                  }

                  continue;
               }
               order.addPart(carPart);
               order.preset.partTypes[i] = null;
               DB.updateCarPartReferenceToPackage(part.getId(),
                     order.getPackageNo());
            }
            catch (RemoteException e)
            {
               e.printStackTrace();
            }
         }
         isLookingForMissingCarParts = true;

      }

      return order;
   }


   @Override
   public Package assemblePackage(Order order)
   {
      if (order.getCarModel() == null)
         return assemblePackage((TypeOrder) order);
      else
         return assemblePackage((PresetOrder) order);
   }

   private void waitForCarPart() {
      try
      {
         Thread.sleep(PAUSE_TIME);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }
}
