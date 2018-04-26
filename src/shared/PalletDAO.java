package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface PalletDAO extends Remote
{
   PalletDTO insertPallet(double weightCapacity, PartType partType)
         throws RemoteException;

   Collection<PalletDTO> readAllPallets() throws RemoteException;

   Collection<PalletDTO> readPalletsByType(String partType) throws RemoteException;
   
   double getPalletCurrentWeight(int palletNo) throws RemoteException;

   void updatePallet(PalletDTO pallet) throws RemoteException;

   void deletePallet(PalletDTO pallet) throws RemoteException;

   PalletDTO readPallet(int palletNo) throws RemoteException;
}
