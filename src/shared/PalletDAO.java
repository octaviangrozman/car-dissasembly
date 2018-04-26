package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface PalletDAO extends Remote
{
   PalletDTO insertPallet(double weightCapacity, PartType partType)
         throws RemoteException;

   Collection<PalletDTO> readAllPallets() throws RemoteException;

   Collection<PalletDTO> readPalletsByType(PartType partType) throws RemoteException;

   void updatePallet(PalletDTO pallet) throws RemoteException;

   void deletePallet(PalletDTO pallet) throws RemoteException;

   PalletDTO readPallet(int palletNo) throws RemoteException;
}
