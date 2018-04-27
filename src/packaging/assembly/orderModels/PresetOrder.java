package packaging.assembly.orderModels;

import shared.PartType;

public class PresetOrder extends Order {

    public Preset preset;

    public PresetOrder(int packageNo, String carModel, Preset preset) {
        super(packageNo, carModel, null);
        this.preset = preset;
    }

    public enum Preset{
        STEERING(new PartType[]{PartType.Steering, PartType.Door, PartType.SeatBelts}),
        LIGHTNING(new PartType[]{PartType.Lights, PartType.Gearbox, PartType.Engine});

        public PartType[] partTypes;

        Preset(PartType[] partTypes){
                this.partTypes = partTypes;
        }

    }
}
