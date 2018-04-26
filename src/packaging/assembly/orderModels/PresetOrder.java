package packaging.assembly.orderModels;

import shared.PartType;

import java.util.HashMap;

public class PresetOrder extends Order {
    public static final HashMap<Preset, PartType[]> presetMap = new HashMap<>();

    static{
        presetMap.put(Preset.STEERING, new PartType[]{PartType.Steering, PartType.Horn,PartType.SeatBelts});
        //presetMap.put(Preset.TRANSMISSION, new PartType[]{PartType.Engine, PartType.Gearbox,PartType.FuelSystem});
    }

    public Preset preset;

    public PresetOrder(int packageNo, String carModel, Preset preset) {
        super(packageNo, carModel, null);
        this.preset = preset;
    }

    public enum Preset{
        STEERING(new PartType[]{PartType.Lights, PartType.Gearbox, PartType.SeatBelts});


        public PartType[] partTypes;
        Preset(PartType[] partTypes){
                this.partTypes = partTypes;
        }

    }
}
