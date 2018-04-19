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


public class CarDAOServer extends UnicastRemoteObject implements CarDAO {
   private static final long serialVersionUID = 1;
   private DatabaseHelper<CarDTO> helper;

   public CarDAOServer() throws RemoteException {
      helper = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=car_base", "postgres", "password");
   }
   
   private Connection getConnection() throws SQLException {
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=car_base", "postgres", "password");
   }

   @Override
   public CarDTO create(int chassisNo, String model, double weight) throws RemoteException {
      helper.executeUpdate("INSERT INTO car VALUES (?, ?, ?)", chassisNo, model, weight);
      return new CarDTO(chassisNo, model, weight);
   }
   
   private CarDTO createCar(ResultSet rs) throws SQLException {
      int chassisNo = rs.getInt("chassisNo");
      String model = rs.getString("model");
      double weight = rs.getDouble("year");
      return new CarDTO(chassisNo, model, weight);
   }

   @Override
   public CarDTO read(int chassisNo) throws RemoteException {
      return helper.mapSingle((rs) -> createCar(rs), "SELECT * FROM car where  chassisNo = ?", chassisNo);
   }

   @Override
   public Collection<CarDTO> readAll() throws RemoteException {
      return helper.map((rs) -> createCar(rs), "SELECT * FROM car");
   }

   @Override
   public void update(CarDTO car) throws RemoteException {
      helper.executeUpdate("UPDATE car SET model=?, weight=? WHERE chassisNo = ?", 
            car.getModel(), car.getWeight(), car.getChassisNo());
   }

   @Override
   public void delete(CarDTO car) throws RemoteException {
      helper.executeUpdate("DELETE FROM car WHERE chassisNo = ?", car.getChassisNo());
   }
   
   private void createTestDB() throws SQLException {
      try (Connection connection = getConnection()) {
         Statement stat = connection.createStatement();
         stat.executeUpdate("DELETE FROM car");
         stat.executeUpdate("INSERT INTO car VALUES(123456, 'Ford', 2000)");
      }
   }
   
   public static void startAsServer() throws RemoteException {
      CarDAOServer carDAOServer = new CarDAOServer();
      Registry registry = LocateRegistry.getRegistry(1099);
      registry.rebind("carDao", carDAOServer);
   }
   
   public static void startAsTestServer() throws RemoteException, SQLException {
      CarDAOServer carDAOServer = new CarDAOServer();
      carDAOServer.createTestDB();
      Registry registry = LocateRegistry.getRegistry(1099);
      registry.rebind("carDao", carDAOServer);
   }
   
   public static void main(String[] args) throws Exception {
      startAsTestServer();
   }
}
