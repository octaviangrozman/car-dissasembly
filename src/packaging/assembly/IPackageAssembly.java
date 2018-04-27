package packaging.assembly;

import models.Package;
import packaging.assembly.orderModels.Order;

public interface IPackageAssembly {
    Package assemblePackage(Order order);
}
