package data;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper<T>
{
   private static Connection connection;

   static{
      try {
         connection = getConnection();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public DatabaseHelper()
   {
   }

   public static Connection getConnection() throws SQLException
   {
      return DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/carDisassembly?currentSchema=public",
              "postgres", "123456");
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
         LinkedList<T> allRows = new LinkedList<>();
         while (rs.next())
         {
            allRows.add(mapper.create(rs));
         }
         return allRows;
      }
      catch (SQLException e)
      {
         throw new RemoteException(e.getMessage(), e);
      }
   }
}
