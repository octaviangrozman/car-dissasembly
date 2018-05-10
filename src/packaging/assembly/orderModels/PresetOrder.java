package packaging.assembly.orderModels;

import shared.PartType;

public class PresetOrder extends Order
{

   public Preset preset;

   public PresetOrder(String carModel, Preset preset)
   {
      super(carModel, null);
      this.preset = preset;
   }

   public enum Preset
   {
      STEERING(new PartType[] { PartType.Steering, PartType.Door, PartType.SeatBelts }),
      LIGHTNING( new PartType[] { PartType.Lights, PartType.Gearbox });

      public PartType[] partTypes;

      Preset(PartType[] partTypes)
      {
         this.partTypes = partTypes;
      }

   }
}
