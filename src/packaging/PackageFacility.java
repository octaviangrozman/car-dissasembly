package packaging;

import models.Pallet;
import packaging.assembly.IPackageAssembly;
import packaging.assembly.PackageAssembly;
import packaging.assembly.orderModels.Order;
import shared.PalletDAO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class PackageFacility  {
    private PalletDAO database;
    private ArrayList<Pallet> palletList;
    private IPackageAssembly assembly;
    private Queue<Order> orderQueue;
    private IOrderGenerator orderGenerator;

    private int packageId;

    protected PackageFacility(IPackageAssembly assembly, IOrderGenerator orderGenerator, ArrayDeque<Order> queue) {
        //connectToDatabase("1111", "pallet_dao");
        this.packageId = 1;
        this.assembly = assembly;
        this.orderGenerator = orderGenerator;
        this.orderQueue = queue;

        orderQueue.add(orderGenerator.generateOrder(packageId++));
        assembly.assemblePackage(orderQueue.peek());
    }

    public static void main(String[] args) {
        new PackageFacility(new PackageAssembly(), new RandomOrderGenerator(5), new ArrayDeque<>());
    }

    private void connectToDatabase(String portNo, String stubName){
        try {
            database = (PalletDAO) Naming.lookup("rmi://localhost:"+portNo+"/"+stubName);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
