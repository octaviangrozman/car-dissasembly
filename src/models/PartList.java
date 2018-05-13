package models;

import java.io.Serializable;
import java.util.ArrayList;

public class PartList implements Serializable
{
   private ArrayList<CarPart> parts;
   
   public PartList()
   {
      parts = new ArrayList<>();
   }

   public void addPart(CarPart part)
   {
      parts.add(part);
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

   public int size(){
      return this.parts.size();
   }
}
