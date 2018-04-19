package shared;

import java.io.Serializable;

public class CarDTO implements Serializable {
   
   private static final long serialVersionUID = 1L;
   private int chassisNo;
   private String model;
   private double weight;


   public CarDTO(int chassisNo, String model, double weight) {
       this.chassisNo = chassisNo;
       this.model = model;
       this.weight = weight;
   }

   public int getChassisNo() {
       return chassisNo;
   }


   public String getModel() {
       return model;
   }

   public double getWeight() {
       return weight;
   }

}
