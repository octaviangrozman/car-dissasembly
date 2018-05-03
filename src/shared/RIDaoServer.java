package shared;

import java.rmi.Remote;

public interface RIDaoServer extends Remote
{
   String SERVER_NAME = "dbServer";
   int PORT = 1099;
}
