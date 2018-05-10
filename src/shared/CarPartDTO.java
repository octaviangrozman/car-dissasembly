package shared;

import java.io.Serializable;

public class CarPartDTO implements Serializable
{
   private static final long serialVersionUID = 1L;
   private int id;
   private double weight;
   private int chassisNo;
   private String model;
   private PartType type;
   private int palletNo;
   private int packageNo;

   public int getPalletNo() {
      return palletNo;
   }

   public void setPalletNo(int palletNo) {
      this.palletNo = palletNo;
   }

   public int getPackageNo() {
      return packageNo;
   }

   public void setPackageNo(int packageNo) {
      this.packageNo = packageNo;
   }

   public CarPartDTO(double weight, int chassisNo, String model, PartType type)
   {
      this.weight = weight;
      this.chassisNo = chassisNo;
      this.model = model;
      this.type = type;
   }

   public CarPartDTO(int id, double weight, int chassisNo, String model, PartType type)
   {
      this.id = id;
      this.weight = weight;
      this.chassisNo = chassisNo;
      this.model = model;
      this.type = type;
   }

   public CarPartDTO(int id, double weight, int chassisNo, String model, PartType type, int palletNo, int packageNo)
   {
      this.id = id;
      this.weight = weight;
      this.chassisNo = chassisNo;
      this.model = model;
      this.type = type;
      this.palletNo = palletNo;
      this.packageNo = packageNo;
   }
   public int getId()
   {
      return id;
   }

   public double getWeight()
   {
      return weight;
   }

   public int getChassisNo()
   {
      return chassisNo;
   }

   public String getModel()
   {
      return model;
   }

   public PartType getType()
   {
      return type;
   }
   
   public String toString()
   {
      return "Part: " + id + " PT " + type + " m " + model + " wt " + weight;
   }
}
