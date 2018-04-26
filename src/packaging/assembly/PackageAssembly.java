package packaging.assembly;

import packaging.assembly.orderModels.Order;
import packaging.assembly.orderModels.PresetOrder;
import packaging.assembly.orderModels.TypeOrder;

public class PackageAssembly implements IPackageAssembly {

    public Package assemblePackage(TypeOrder order) {
        return null;
    }

    public Package assemblePackage(PresetOrder order) {
        return null;
    }

    @Override
    public Package assemblePackage(Order order) {
        if(order instanceof TypeOrder)
            return assemblePackage((TypeOrder) order);
        else
            return assemblePackage((PresetOrder) order);
    }
}
