package packaging.assembly.orderModels;

import shared.PartType;

public class TypeOrder extends Order {
    public int quantity;

    public TypeOrder(PartType partType, int quantity) {
        super( null, partType);
        this.quantity = quantity;
    }
}
