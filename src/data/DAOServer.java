package data;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import shared.*;

public class DAOServer extends UnicastRemoteObject implements CarDAO, CarPartDAO, PalletDAO, PackageDAO
{
   private static final long serialVersionUID = 1;
   private DatabaseHelper<CarDTO> carHelper;
   private DatabaseHelper<CarPartDTO> partHelper;
   private DatabaseHelper<PalletDTO> palletHelper;
   private DatabaseHelper<PackageDTO> packageHelper;

   
   
   public DAOServer() throws RemoteException
   {//Connection information is in DatabaseHelper contructor implementation
      carHelper = new DatabaseHelper<>();
      partHelper = new DatabaseHelper<>();
      palletHelper = new DatabaseHelper<>();
      packageHelper = new DatabaseHelper<>();
   }

   private Connection getConnection() throws SQLException
   {
      return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/carDisassembly?currentSchema=public",
            "postgres", "123456");
   }
   ////////SQL DML STATEMENTS//////////////////////////////////////////////////////////////////
    ///////////////////////////////////
   //DAO Insert Statements////////////
  ///////////////////////////////////
   @Override
   public CarDTO insertCar(int chassisNo, String model, double weight)
         throws RemoteException
   {
      carHelper.executeUpdate("INSERT INTO car (chassisNo, model, carweight) VALUES (?, ?, ?)", chassisNo, model,
            weight);
      return new CarDTO(chassisNo, model, weight);
   }
   
   @Override
   public CarPartDTO insertCarPart(double weight, int chassisNo, String model,
         PartType type) throws RemoteException
   {
      partHelper.executeUpdate("INSERT INTO Part (carChassisNo, partType, partWeight) VALUES (?, ?, ?)", chassisNo, type,
            weight);
      return new CarPartDTO(weight, chassisNo, model, type);
   }
   
   @Override
   public PalletDTO insertPallet(double weightCapacity, PartType type) throws RemoteException
   {
      palletHelper.executeUpdate("INSERT INTO Pallet (weightCapacity, partType) VALUES (?, ?)", weightCapacity, type);
      return new PalletDTO(weightCapacity, type);
   }
   
   @Override
   public PackageDTO insertPackage(String model, PartType type) throws RemoteException
   {
      packageHelper.executeUpdate("INSERT INTO Package (carModel, partType) VALUES (?, ?)", model, type);
      return new PackageDTO(model, type);
   }
   
    //////////////////////////////
   //CREATE statements///////////
  //////////////////////////////
   private CarDTO createCar(ResultSet rs) throws SQLException
   {
      int chassisNo = rs.getInt("chassisNo");
      String model = rs.getString("model");
      double weight = rs.getDouble("carWeight");
      return new CarDTO(chassisNo, model, weight);
   }
   
   private CarPartDTO createCarPart(ResultSet rs1) throws SQLException
   {
      int chassisNo = rs1.getInt("carChassisNo");
      double weight = rs1.getDouble("partWeight");
      String model = rs1.getString("carModel");
      PartType type = PartType.valueOf(rs1.getString("partType"));
      return new CarPartDTO(weight, chassisNo, model, type);
   }
   
   private PalletDTO createPallet(ResultSet rs) throws SQLException
   {
      double weightCapacity = rs.getDouble("weightCapacity");
      PartType type = PartType.valueOf(rs.getString("partType"));
      return new PalletDTO(weightCapacity, type);
   }
   
   private PackageDTO createPackage(ResultSet rs) throws SQLException
   {
      String carModel = rs.getString("carModel");
      PartType partType = PartType.valueOf(rs.getString("partType"));
      return new PackageDTO(carModel, partType);
   }
    //////////////////////////////
   //READ statements/////////////
  //////////////////////////////
   @Override
   public CarDTO readCar(int chassisNo) throws RemoteException
   {
      return carHelper.mapSingle((rs) -> createCar(rs), "SELECT * FROM car where  chassisNo = ?"
                                                                                          , chassisNo);
   }
  
   @Override
   public CarPartDTO readCarPart(int id) throws RemoteException
   {
      return partHelper.mapSingle((rs1) -> createCarPart(rs1), "SELECT * FROM Part where ID= ?", id);
   }
   
   @Override
   public PalletDTO readPallet(int palletNo) throws RemoteException
   {
      return palletHelper.mapSingle((rs) -> createPallet(rs), "Select * from Pallet where palletNo = ?"
                                                                                                , palletNo);
   }
   
   @Override
   public PackageDTO readPackage(int packageNo) throws RemoteException
   {
      return packageHelper.mapSingle((rs) -> createPackage(rs), "Select * from Package where packageNo = ?"
                                                                                                , packageNo);
   }
     ///////////////////////////////////
    //READ ALL STATEMENTS//////////////
   ///////////////////////////////////
   @Override
   public Collection<CarDTO> readAllCars() throws RemoteException
   {
      return carHelper.map((rs) -> createCar(rs), "SELECT * FROM car");
   }
   
   @Override
   public Collection<CarPartDTO> readAllCarParts() throws RemoteException
   {
      return partHelper.map((rs) -> createCarPart(rs), "Select * from Part");
   }
   
   @Override
   public Collection<PalletDTO> readAllPallets() throws RemoteException
   {
      return palletHelper.map((rs) -> createPallet(rs), "Select * from Pallet");
   }
   
   @Override
   public Collection<PackageDTO> readAllPackages() throws RemoteException
   {
      return packageHelper.map((rs) -> createPackage(rs), "Select * from Package");
   }
   /////////////////////////////////
  //UPDATE STATEMENTS//////////////
 /////////////////////////////////
   @Override
   public void updateCar(CarDTO car) throws RemoteException
   {
      carHelper.executeUpdate(
            "UPDATE car SET model=?, weight=? WHERE chassisNo = ?",
            car.getModel(), car.getWeight(), car.getChassisNo());
   }
   
   @Override
   public void updateCarPart(CarPartDTO carPart) throws RemoteException
   {
      partHelper.executeUpdate("Update Part Set model = ?, type = ?, weight = ? where ID = ?"
            , carPart.getModel(), carPart.getType().toString(), carPart.getWeight(), carPart.getId());
   }
   
   @Override
   public void updatePallet(PalletDTO pallet) throws RemoteException
   {
      palletHelper.executeUpdate("Update Pallet Set weightCapacity = ?, partType = ? where palletNo = ?"
            , pallet.getWeightCapacity(), pallet.getPartType().toString(), pallet.getPalletNo());
   }
   
   @Override
   public void updatePackage(PackageDTO packaged) throws RemoteException
   {
      packageHelper.executeUpdate("Update Pallet Set carModel = ?, partType = ? where packageNo = ?"
            , packaged.getCarModel(), packaged.getPartType().toString(), packaged.getPackageNo());
   }
   
    /////////////////////////////////
   /////DELETE STATEMENTS///////////
  /////////////////////////////////
   @Override
   public void deleteCar(CarDTO car) throws RemoteException
   {
      carHelper.executeUpdate("DELETE FROM car WHERE chassisNo = ?",
            car.getChassisNo());
   }
   
   @Override
   public void deleteCarPart(CarPartDTO carPart) throws RemoteException
   {
      partHelper.executeUpdate("DELETE FROM carPart where ID = ?", carPart.getId());
   }

   @Override
   public void deletePallet(PalletDTO pallet) throws RemoteException
   {
      palletHelper.executeUpdate("DELETE FROM pallet where palletNo = ?", pallet.getPalletNo());
   }
   
   @Override
   public void deletePackage(PackageDTO packaged) throws RemoteException
   {
      packageHelper.executeUpdate("DELETE FROM Package where packageNo = ?", packaged.getPackageNo());
   }
   /////////////////////////////////////
   /// DML STATEMENTS END!!!!!!////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

   private void createTestDB() throws SQLException
   {
      try (Connection connection = getConnection())
      {
         Statement stat = connection.createStatement();
         stat.executeUpdate("INSERT INTO car VALUES(123456, 'Ford', 2000)");
         stat.executeUpdate("DELETE FROM car");
         
      }
   }

   public static void startAsServer() throws RemoteException
   {
      DAOServer DAOServer = new DAOServer();
      Registry registry = LocateRegistry.getRegistry(1099);
      registry.rebind("Dao", DAOServer);
   }

   public static void startAsTestServer() throws RemoteException, SQLException
   {
      DAOServer DAOServer = new DAOServer();
      DAOServer.createTestDB();
      Registry registry = LocateRegistry.getRegistry(1099);
      registry.rebind("Dao", DAOServer);
   }

   public static void main(String[] args) throws Exception
   {
      startAsTestServer();
   }
 
}
