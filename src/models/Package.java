package models;

public class Package
{
   private int packageNo;
   private String carModel;
   private String partType;
   private PartList parts;
   
   public Package(int packageNo, String carModel, String partType)
   {
      this.packageNo = packageNo;
      this.carModel = carModel;
      this.partType = partType;
      parts = new PartList();
   }

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

   public String getPartType()
   {
      return partType;
   }

   public void setPartType(String partType)
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
