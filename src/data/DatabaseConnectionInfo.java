package data;

import java.rmi.RemoteException;

public class DatabaseConnectionInfo
{
   private String jdbcURL;
   private String username;
   private String password;

   public String getJdbcURL()
   {
      return jdbcURL;
   }

   public String getUsername()
   {
      return username;
   }

   public String getPassword()
   {
      return password;
   }

   public DatabaseConnectionInfo(String jdbcURL, String username, String password)
         throws RemoteException
   {
      this.jdbcURL = jdbcURL;
      this.username = username;
      this.password = password;
   }
}
