package data;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import org.postgresql.Driver;

public class DatabaseHelper<T>
{
   private DatabaseConnectionInfo dbConnection;
   private String DBurl = "jdbc:postgresql://localhost:5432/carDisassembly?currentSchema=public";
   private String DBPassword = "teentitans1sasu";
   private String DBUser = "postgres";
   
   public DatabaseHelper()
         throws RemoteException
   {
      dbConnection = new DatabaseConnectionInfo(DBurl, DBUser, DBPassword);
      try
      {
         DriverManager.registerDriver(new Driver());
      }
      catch (SQLException e)
      {
         throw new RemoteException("No JDBC driver", e);
      }
   }

 /*  public DatabaseHelper(String jdbcURL) throws RemoteException
   {
      this(jdbcURL, null, null);
   }*/

   protected Connection getConnection() throws SQLException
   {
      if (dbConnection.getUsername() == null)
      {
         return DriverManager.getConnection(dbConnection.getJdbcURL());
      }
      else
      {
         return DriverManager.getConnection(dbConnection.getJdbcURL(), dbConnection.getUsername(), dbConnection.getPassword());
      }
   }

   private PreparedStatement prepare(Connection connection, String sql,
         Object[] parameters) throws SQLException
   {
      PreparedStatement stat = connection.prepareStatement(sql);
      for (int i = 0; i < parameters.length; i++)
      {
         stat.setObject(i + 1, parameters[i]);
      }
      return stat;
   }

   public int executeUpdate(String sql, Object... parameters)
         throws RemoteException
   {
      try (Connection connection = getConnection())
      {
         PreparedStatement stat = prepare(connection, sql, parameters);
         return stat.executeUpdate();
      }
      catch (SQLException e)
      {
         throw new RemoteException(e.getMessage(), e);
      }
   }

   public T mapSingle(DataMapper<T> mapper, String sql, Object... parameters)
         throws RemoteException
   {
      try (Connection connection = getConnection())
      {
         PreparedStatement stat = prepare(connection, sql, parameters);
         ResultSet rs = stat.executeQuery();
         if (rs.next())
         {
            return mapper.create(rs);
         }
         else
         {
            return null;
         }
      }
      catch (SQLException e)
      {
         throw new RemoteException(e.getMessage(), e);
      }
   }

   public List<T> map(DataMapper<T> mapper, String sql, Object... parameters)
         throws RemoteException
   {
      try (Connection connection = getConnection())
      {
         PreparedStatement stat = prepare(connection, sql, parameters);
         ResultSet rs = stat.executeQuery();
         LinkedList<T> allCars = new LinkedList<>();
         while (rs.next())
         {
            allCars.add(mapper.create(rs));
         }
         return allCars;
      }
      catch (SQLException e)
      {
         throw new RemoteException(e.getMessage(), e);
      }
   }
}
