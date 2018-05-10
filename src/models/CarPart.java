package models;

import shared.PartType;

import java.io.Serializable;

public class CarPart implements Serializable
{
   private int id;
   private double weight;
   private int chassisNo;
   private String model;
   private PartType type;

   public CarPart(double weight, int chassisNo, String model, PartType type)
   {
      this.weight = weight;
      this.chassisNo = chassisNo;
      this.model = model;
      this.type = type;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public double getWeight()
   {
      return weight;
   }

   public void setWeight(double weight)
   {
      this.weight = weight;
   }

   public int getChassisNo()
   {
      return chassisNo;
   }

   public void setChassisNo(int chassisNo)
   {
      this.chassisNo = chassisNo;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }

   public PartType getType()
   {
      return type;
   }

   public void setType(PartType type)
   {
      this.type = type;
   }
}
