package packaging;

import models.Package;
import models.Pallet;
import packaging.assembly.IPackageAssembly;
import packaging.assembly.PackageAssembly;
import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.PresetOrder;
import packaging.assembly.orderModels.PresetOrder.Preset;
import packaging.assembly.orderModels.TypeOrder;
import shared.*;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class PackageFacility
{
   private PackageDAO packageDAO;
   private CarPartDAO carPartDAO;
   private IPackageAssembly assembly;
   private IOrderGenerator orderGenerator;


   private PackageFacility(IOrderGenerator orderGenerator)
   {
      connectToDB();
      this.assembly = new PackageAssembly(carPartDAO, 5000);
      this.orderGenerator = orderGenerator;

   }

   public static void main(String[] args)
   {
      new PackageFacility(new RandomOrderGenerator(5))
                  .work();
   }

   private void work(int numberOfOrders)
   {
      for (int i = 0; i < numberOfOrders; i++)
      {
         work(orderGenerator.generateOrder());
      }
   }

   private void work(Order order)
   {
      PackageDTO packageDTO = null;
      try
      {
         //FINDING OUT WHAT TYPE OF ORDER IT IS: TYPEORDER OR PRESETORDER AND PASSING THE VALUES TO DB ACCORDINGLY
         //PRESET ORDER MUST HAVE A CARMODEL AND TYPEORDER MUST NOT HAVE A CARMODEL
         if(order.getCarModel() == null)
            packageDTO = packageDAO.insertPackage(order.getPartType());
         
         if(order.getPartType() == null)
            packageDTO = packageDAO.insertPackage(order.getCarModel());
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
      if (packageDTO == null)
      {
         System.out.println("Could not create an packageDTO SQL record");
         return;
      }
      //IMPORTANT
      order.setPackageNo(packageDTO.getPackageNo());

      assembly.assemblePackage(order);
   }

   private void work()
   {
      work(orderGenerator.generateOrder());
   }

   private void connectToDB()
   {
      try
      {
         RIDaoServer daoServer = DatabaseLocator.getDatabaseServer();
         this.packageDAO = (PackageDAO) daoServer;
         this.carPartDAO = (CarPartDAO) daoServer;
      }
      catch (RemoteException e)
      {
         System.out.println("Could not connect to Database server");
         e.printStackTrace();
      }
   }
}
