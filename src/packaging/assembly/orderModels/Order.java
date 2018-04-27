package packaging.assembly.orderModels;

import models.Package;
import shared.PartType;

public class Order extends Package {

    public Order(String carModel, PartType partType) {
        super(-1, carModel, partType);
    }

}
