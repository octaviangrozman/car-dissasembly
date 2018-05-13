package packaging;


import packaging.assembly.IPackageAssembly;
import packaging.assembly.PackageAssembly;
import packaging.assembly.orderModels.Order;
import shared.*;

import java.rmi.RemoteException;

import static java.lang.System.out;


public class PackageFacility
{
   private PackageDAO packageDAO;
   private CarPartDAO carPartDAO;
   private CarDAO carDAO;
   private IPackageAssembly assembly;
   private IOrderGenerator orderGenerator;

   private PackageFacility() throws RemoteException {
      connectToDB();
      this.assembly = new PackageAssembly(carPartDAO, 5000);
      this.orderGenerator = new RandomOrderGenerator(3, carDAO, carPartDAO);
   }

   public static void main(String[] args) throws RemoteException {
      new PackageFacility().work();
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
         out.println("Could not create an packageDTO SQL record");
         return;
      }
      //IMPORTANT
      order.setPackageNo(packageDTO.getPackageNo());

      assembly.assemblePackage(order);
   }

   private void work()
   {
      Order order = orderGenerator.generateOrder();
      if (order == null) {
         out.println("There are no enough parts in stock, try again!");
         return;
      }
      work(order);
   }

   private void connectToDB()
   {
      try
      {
         RIDaoServer daoServer = DatabaseLocator.getDatabaseServer();
         this.packageDAO = (PackageDAO) daoServer;
         this.carPartDAO = (CarPartDAO) daoServer;
         this.carDAO = (CarDAO) daoServer;
      }
      catch (RemoteException e)
      {
         out.println("Could not connect to Database server");
         e.printStackTrace();
      }
   }
}
