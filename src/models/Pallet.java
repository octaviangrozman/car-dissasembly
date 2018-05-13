package models;

import shared.PartType;

import java.io.Serializable;

public class Pallet implements Serializable
{
   private int palletNo; //like IDs - get from DB
   private double weightCapacity;
   private PartType partType;
   private double currentWeight;
   
   public Pallet(double weightCapacity, PartType partType)
   {
      this.weightCapacity = weightCapacity;
      this.partType = partType;
      currentWeight = 0;
   }
   
   public int getPalletNo()
   {
      return palletNo;
   }
   
   public void setPalletNo(int palletNo)
   {
      this.palletNo = palletNo;
   }
   
   public double getWeightCapacity()
   {
      return weightCapacity;
   }
   
   public void setWeightCapacity(double weightCapacity)
   {
      this.weightCapacity = weightCapacity;
   }
   
   public PartType getPartType()
   {
      return partType;
   }
   
   public void setPartType(PartType partType)
   {
      this.partType = partType;
   }

}
