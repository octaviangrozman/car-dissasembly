package packaging.assembly.orderModels;

import models.Package;
import shared.PartType;

public class Order extends Package {

    public Order(int packageNo, String carModel, PartType partType) {
        super(packageNo, carModel, partType);
    }

}
