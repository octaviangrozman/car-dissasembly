package packaging;

import models.Pallet;
import packaging.assembly.IPackageAssembly;
import packaging.assembly.PackageAssembly;
import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.TypeOrder;
import shared.DatabaseLocator;
import shared.PackageDAO;
import shared.PackageDTO;
import shared.PartType;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class PackageFacility  {
    private final PackageDAO DB;
    private ArrayList<Pallet> palletList;
    private IPackageAssembly assembly;
    private Queue<Order> orderQueue;
    private IOrderGenerator orderGenerator;

    private int packageId;

    private PackageFacility(IPackageAssembly assembly, IOrderGenerator orderGenerator, ArrayDeque<Order> queue) {
        this.DB = connectToDB();
        this.packageId = 1;
        this.assembly = assembly;
        this.orderGenerator = orderGenerator;
        this.orderQueue = queue;

//        orderQueue.add(orderGenerator.generateOrder(packageId++));
//        Order orderToBeAssembled = orderQueue.peek();
//        PackageDTO packageDTO = null;
//        try {
//            packageDTO = DB.insertPackage(orderToBeAssembled.getCarModel(),orderToBeAssembled.getPartType());
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        if(packageDTO == null){
//            System.out.println("Could not create an packageDTO SQL record");
//            return;
//        }
//        orderToBeAssembled.setPackageNo(packageDTO.getPackageNo());
//        assembly.assemblePackage(orderQueue.peek());
    }

    public static void main(String[] args) {
        new PackageFacility(
                new PackageAssembly(),
                new RandomOrderGenerator(5),
                new ArrayDeque<>()
        ).work(new TypeOrder("Nissan Almera",PartType.Door,2));
    }

    private void work(int numberOfOrders){
        for (int i = 0; i < numberOfOrders; i++) {
            work(orderGenerator.generateOrder());
        }
    }
    private void work(Order order){
        PackageDTO packageDTO = null;
        try {
            packageDTO = DB.insertPackage(order.getCarModel(),order.getPartType());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(packageDTO == null){
            System.out.println("Could not create an packageDTO SQL record");
            return;
        }
        order.setPackageNo(packageDTO.getPackageNo());
        assembly.assemblePackage(order);
    }
    private void work(){
        work(orderGenerator.generateOrder());
    }

    private PackageDAO connectToDB() {
        PackageDAO database;
        try {
            database = DatabaseLocator.getDatabaseServer();
        } catch (RemoteException e) {
            System.out.println("Could not connect to Database server");
            e.printStackTrace();
            return null;
        }
        return database;
    }
}
