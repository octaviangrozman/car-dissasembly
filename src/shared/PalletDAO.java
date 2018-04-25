package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface PalletDAO extends Remote
{
   PalletDTO create(double weightCapacity, PartType partType)
         throws RemoteException;

   Collection<PalletDTO> readAll() throws RemoteException;

   void update(PalletDTO pallet) throws RemoteException;

   void delete(PalletDTO pallet) throws RemoteException;

   PalletDTO read(int palletNo) throws RemoteException;
}
