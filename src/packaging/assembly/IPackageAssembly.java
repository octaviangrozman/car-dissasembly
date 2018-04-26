package packaging.assembly;

import packaging.assembly.orderModels.Order;

public interface IPackageAssembly {
    Package assemblePackage(Order order);
}
