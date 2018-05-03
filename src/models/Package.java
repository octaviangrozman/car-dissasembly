package models;

import java.io.Serializable;

import shared.PartType;

public class Package implements Serializable
{
   private int packageNo;
   private String carModel;
   private PartType partType;
   private PartList parts;
   
   public Package(int packageNo, String carModel, PartType partType)
   {
      this.packageNo = packageNo;
      this.carModel = carModel;
      this.partType = partType;
      parts = new PartList();
   }

   public Package(){}

   public int getPackageNo()
   {
      return packageNo;
   }

   public void setPackageNo(int packageNo)
   {
      this.packageNo = packageNo;
   }

   public String getCarModel()
   {
      return carModel;
   }

   public void setCarModel(String carModel)
   {
      this.carModel = carModel;
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
   
   public void addPart(CarPart part)
   {
      if(part.getType().equals(partType) || part.getModel().equals(carModel))
         parts.addPart(part);
   }
   
   public void removePart(int partID)
   {
      parts.removeByPartID(partID);
   }
}
