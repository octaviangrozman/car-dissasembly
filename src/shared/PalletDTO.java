package shared;

import models.PartList;

import java.io.Serializable;

public class PalletDTO implements Serializable
{
   private static final long serialVersionUID = 1L;
   private int palletNo; //like IDs - get from DB
   private double weightCapacity;
   private PartType partType;
   private PartList parts;
   private double currentWeight;
   
   public PalletDTO(double weightCapacity, PartType partType)
   {
      this.weightCapacity = weightCapacity;
      this.partType = partType;
      parts = new PartList();
      currentWeight = 0;
   }
   
   public PalletDTO(int palletNo, double weightCapacity, PartType partType)
   {
      this.palletNo = palletNo;
      this.weightCapacity = weightCapacity;
      this.partType = partType;
      parts = new PartList();
      currentWeight = 0;
   }
   
   public int getPalletNo()
   {
      return palletNo;
   }

   public double getWeightCapacity()
   {
      return weightCapacity;
   }
   
   public double getCurrentWeight()
   {
      return currentWeight;
   }

   public PartType getPartType()
   {
      return partType;
   }

   public PartList getParts()
   {
      return parts;
   }
   
   public String toString()
   {
      return "Pallet " + palletNo + ": WC " + weightCapacity + " PT " + partType;
   }
}
