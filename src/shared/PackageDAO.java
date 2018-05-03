package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface PackageDAO extends Remote
{
   PackageDTO insertPackage(String carModel)
         throws RemoteException;
   
   PackageDTO insertPackage(PartType partType)
         throws RemoteException;

   List<PackageDTO> readAllPackages() throws RemoteException;

   void updatePackage(PackageDTO packaged) throws RemoteException;

   void deletePackage(PackageDTO packaged) throws RemoteException;

   PackageDTO readPackage(int packageNo) throws RemoteException;
}
