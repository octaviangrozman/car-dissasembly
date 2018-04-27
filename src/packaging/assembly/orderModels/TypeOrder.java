package packaging.assembly.orderModels;

import shared.PartType;

public class TypeOrder extends Order {
    public int quantity;

    public TypeOrder(int packageNo, String carModel, PartType partType, int quantity) {
        super(packageNo, null, partType);
        this.quantity = quantity;
    }
}
