package hr.fer.oop.lab5;

import java.util.*;

class DeliverySystem {

    public static Map<String, Integer> numOfDeliveriesPerDeliveryMan(Map<String,Collection<Connection>> data) {
      Map<String, Integer> result = new TreeMap<String, Integer>(); 
      int number;
      for (String  names: data.keySet()) {
          number=0;
          Collection<Connection> values = data.get(names);
          for(int i=0; i<values.size(); i++) {
              number++;
          }
          result.put(names, number);
      }
      return result;
  }

  public static Map<String, Float> distancePerDeliveryMan(Map<String,Collection<Connection>> data) {
      Map<String, Float> result = new TreeMap<String, Float>(); 
      float distance;
      for (String  names: data.keySet()) {
         distance =0;
          Collection<Connection> values = data.get(names);
          for(int i=0; i<values.size(); i++) {
              distance +=  (float)((Connection) values).getDistance();
          }
          result.put(names, distance);
      }
      return result;
  }

}