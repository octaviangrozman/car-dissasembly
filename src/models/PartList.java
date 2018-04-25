package models;

import java.util.ArrayList;

import shared.PartType;

public class PartList
{
   private ArrayList<CarPart> parts;
   
   public PartList()
   {
      parts = new ArrayList<>();
   }
   
   public CarPart getPartByID(int partID)
   {
      for(CarPart p : parts)
      {
         if(p.getId() == partID)
            return p;
      }
      return null;
   }
   
   public CarPart getPartByModel(String model)
   {
      for(CarPart p : parts)
      {
         if(p.getModel().equals(model))
            return p;
      }
      return null;
   }
   
   public PartList getPartsByModel(String model)
   {
      PartList toReturn = new PartList();
      
      for(CarPart p : parts)
      {
         if(p.getModel().equals(model))
            toReturn.addPart(p);
      }
      return toReturn;
   }
   
   public PartList getPartsByModel(int amount, String model)
   {
      PartList toReturn = new PartList();
      int added = 0;
      
      for(CarPart p : parts)
      {
         if(added < amount && p.getModel().equals(model))
            toReturn.addPart(p);
      }
      return toReturn;
   }
   
   public CarPart getPartByType(PartType type)
   {
      for(CarPart p : parts)
      {
         if(p.getType().equals(type))
            return p;
      }
      return null;
   }
   
   public PartList getPartsByType(PartType type)
   {
      PartList toReturn = new PartList();
      
      for(CarPart p : parts)
      {
         if(p.getType().equals(type))
            toReturn.addPart(p);
      }
      return toReturn;
   }
   
   public PartList getPartsByType(int amount, PartType type)
   {
      PartList toReturn = new PartList();
      int added = 0; 
      
      for(CarPart p : parts)
      {
         if(added < amount && p.getType().equals(type))
            toReturn.addPart(p);
      }
      return toReturn;
   }
   
   public CarPart getPartByCar(int chassisNo)
   {
      for(CarPart p : parts)
      {
         if(p.getChassisNo()==chassisNo)
            return p;
      }
      return null;
   }
   
   public PartList getPartsByCar(int chassisNo)
   {
      PartList toReturn = new PartList();
      
      for(CarPart p : parts)
      {
         if(p.getChassisNo()==chassisNo)
            toReturn.addPart(p);
      }
      return toReturn;
   }
   
   public void addPart(CarPart part)
   {
      parts.add(part);
   }
   
   public boolean contains(CarPart part)
   {
      return parts.contains(part);
   }
   
   public void removeByPartID(int partID)
   {
      for(int i=0; i<parts.size(); i++)
      {
         if(parts.get(i).getId() == partID)
         {
            parts.remove(i);
            return;
         }
      }
   }
}
