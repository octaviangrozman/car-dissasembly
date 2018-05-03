package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface PalletDAO extends Remote
{
   PalletDTO insertPallet(double weightCapacity, PartType partType)
         throws RemoteException;

   List<PalletDTO> readAllPallets() throws RemoteException;

   List<PalletDTO> readPalletsByType(PartType partType) throws RemoteException;
   
   double getPalletCurrentWeight(int palletNo) throws RemoteException;

   void updatePallet(PalletDTO pallet) throws RemoteException;

   void deletePallet(PalletDTO pallet) throws RemoteException;

   PalletDTO readPallet(int palletNo) throws RemoteException;
}
