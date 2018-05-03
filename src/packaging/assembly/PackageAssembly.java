package packaging.assembly;

import models.CarPart;
import models.Package;
import packaging.Util;
import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.PresetOrder;
import packaging.assembly.orderModels.TypeOrder;
import shared.CarPartDAO;
import shared.DatabaseLocator;

import java.rmi.RemoteException;

public class PackageAssembly implements IPackageAssembly
{
   private final CarPartDAO DB;

   public PackageAssembly()
   {
      this.DB = connectToDB();
   }

   private CarPartDAO connectToDB()
   {
      CarPartDAO DBcarParts1;
      try
      {
         DBcarParts1 = (CarPartDAO) DatabaseLocator.getDatabaseServer();
      }
      catch (RemoteException e)
      {
         System.out.println("Could not connect to Database server");
         e.printStackTrace();
         return null;
      }
      return DBcarParts1;
   }

   private Package assemblePackage(TypeOrder order)
   {
      boolean isLookingForMissingCarParts = false;

      while (order.getParts().size() < order.quantity)
      {
         for (int i = order.getParts().size(); i < order.quantity; i++)
         {
            try
            {
               CarPart carPart = Util
                     .convertToModel(DB.readCarPartByType(order.getPartType()));
               if (carPart == null)
               {
                  if (isLookingForMissingCarParts)
                  {
                     System.out.println("There is no Car Part of a type: "
                           + order.getPartType() + " . Waiting...");
                     try
                     {
                        Thread.sleep(3000);
                     }
                     catch (InterruptedException e)
                     {
                        e.printStackTrace();
                     }
                  }
                  continue;
               }
               order.addPart(carPart);
               DB.updateCarPartReferenceToPackage(carPart.getId(),
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

      while (order.getParts().size() < order.preset.partTypes.length)
      {
         for (int i = order.getParts()
               .size(); i < order.preset.partTypes.length; i++)
         {
            try
            {
               if (order.preset.partTypes[i] == null)
                  continue;
               CarPart carPart = Util.convertToModel(
                     DB.readCarPartByTypeAndModel(order.preset.partTypes[i],
                           order.getCarModel()));
               if (carPart == null)
               {
                  if (isLookingForMissingCarParts)
                  {
                     System.out.println("There is no Car Part of a type: "
                           + order.preset.partTypes[i] + " . Waiting...");
                     try
                     {
                        Thread.sleep(3000);
                     }
                     catch (InterruptedException e)
                     {
                        e.printStackTrace();
                     }
                  }

                  continue;
               }
               order.addPart(carPart);
               order.preset.partTypes[i] = null;
               DB.updateCarPartReferenceToPackage(carPart.getId(),
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
      if (order instanceof TypeOrder)
         return assemblePackage((TypeOrder) order);
      else
         return assemblePackage((PresetOrder) order);
   }
}
