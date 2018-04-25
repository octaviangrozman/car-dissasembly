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

  public CarPartDTO(double weight, int chassisNo, String model, PartType type) {
      this.weight = weight;
      this.chassisNo = chassisNo;
      this.model = model;
      this.type = type;
  }
  
 
     

  public int getId() {
      return id;
  }


  public double getWeight() {
      return weight;
  }


  public int getChassisNo() {
      return chassisNo;
  }


  public String getModel() {
      return model;
  }


  public PartType getType() {
      return type;
  }

}
