package shared;

import java.rmi.RemoteException;
import java.util.Collection;

public interface PackageDAO
{
   PackageDTO insertPackage(String carModel, PartType partType)
         throws RemoteException;

   Collection<PackageDTO> readAllPackages() throws RemoteException;

   void updatePackage(PackageDTO packaged) throws RemoteException;

   void deletePackage(PackageDTO packaged) throws RemoteException;

   PackageDTO readPackage(int packageNo) throws RemoteException;
}
