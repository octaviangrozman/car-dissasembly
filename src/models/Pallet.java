package models;

import shared.PartType;

public class Pallet
{
   private int palletNo; //like IDs - get from DB
   private double weightCapacity;
   private PartType partType;
   private PartList parts;
   private double currentWeight;
   
   public Pallet(double weightCapacity, PartType partType)
   {
      this.weightCapacity = weightCapacity;
      this.partType = partType;
      parts = new PartList();
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
   
   public PartList getParts()
   {
      return parts;
   }
   
   public void setParts(PartList parts)
   {
      this.parts = parts;
   }
   
   public void addPart(CarPart part)
   {
      double weightToBe = currentWeight + part.getWeight();
      
      if(part.getType().equals(partType) && weightToBe<=weightCapacity)
         {
            parts.addPart(part);
            currentWeight = weightToBe;
         }
   }
   
   public void removePart(int partID)
   {
      parts.removeByPartID(partID);
   }
}
