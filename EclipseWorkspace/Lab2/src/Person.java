import tester.*;

// to represent a mode of transportation
interface IMOT { 
  boolean isMoreFuelEfficientThan(int mpg);
}

//to represent a bicycle as a mode of transportation
class Bicycle implements IMOT{
  String brand;
  
  // the constructor
  Bicycle(String brand){
    this.brand = brand;
  }
  
  public boolean isMoreFuelEfficientThan(int mpg) {
    return true;
  }
}

// to represent a car as a mode of transportation
class Car implements IMOT{
  String make;
  double mpg; // miles per gallon
  
  Car(String make, double mpg){
    this.make = make;
    this.mpg = mpg;
  }
  
  public boolean isMoreFuelEfficientThan(int mpg) {
    return (this.mpg >= mpg);
  }
}

// keeps track of how a person is transported
class Person{
  String name;
  IMOT mot;
  
  // the constructor
  Person(String name, IMOT mot){
    this.name = name;
    this.mot = mot;
  }
  
  // Does this person's mode of transportation meet the given fuel
  // efficiency target (in miles per gallon)?
  boolean motMeetsFuelEfficiency(int mpg) { 
    return (this.mot.isMoreFuelEfficientThan(mpg));
  }

}

// to represent ExamplesPerson
class ExamplesPerson{
  //examples of transportation
   IMOT diamondback = new Bicycle("Diamondback");
   IMOT trek = new Bicycle("Trek");
  
   IMOT toyota = new Car("Toyota", 30);
   IMOT honda = new Car("Honda", 42);
   IMOT lamborghini = new Car("Lamborghini", 17);
  
   // examples of people
   Person bob = new Person("Bob", diamondback);
   Person ben = new Person("Ben", toyota);
   Person bella = new Person("Bella", honda);
   Person becca = new Person("Becca", lamborghini);
  
   //Tests for Car.isMoreFuelEfficientThan
   boolean testCarFuelEfficiency(Tester t) {
     return
         // above target
         t.checkExpect(toyota.isMoreFuelEfficientThan(25), true)
  
         // exactly equal
         && t.checkExpect(toyota.isMoreFuelEfficientThan(30), true)
  
         // below target
         && t.checkExpect(toyota.isMoreFuelEfficientThan(35), false)
  
         // another efficient car
         && t.checkExpect(honda.isMoreFuelEfficientThan(40), true)
  
         // inefficient car
         && t.checkExpect(lamborghini.isMoreFuelEfficientThan(20), false)
  
         // exact equality
         && t.checkExpect(lamborghini.isMoreFuelEfficientThan(17), true);
   }
   
   // Tests for Bicycle.isMoreFuelEfficientThan
   boolean testBicycleFuelEfficiency(Tester t) {
     return
         t.checkExpect(diamondback.isMoreFuelEfficientThan(10), true)
         && t.checkExpect(diamondback.isMoreFuelEfficientThan(50), true)
         && t.checkExpect(diamondback.isMoreFuelEfficientThan(1000), true)
         && t.checkExpect(trek.isMoreFuelEfficientThan(25), true);
   }

   // Tests for Person.motMeetsFuelEfficiency
   boolean testPersonFuelEfficiency(Tester t) {
     return
         // bicycle always succeeds
         t.checkExpect(bob.motMeetsFuelEfficiency(100), true)
  
         // Toyota
         && t.checkExpect(ben.motMeetsFuelEfficiency(25), true)
         && t.checkExpect(ben.motMeetsFuelEfficiency(30), true)
         && t.checkExpect(ben.motMeetsFuelEfficiency(35), false)
  
         // Honda
         && t.checkExpect(bella.motMeetsFuelEfficiency(40), true)
  
         // Lamborghini
         && t.checkExpect(becca.motMeetsFuelEfficiency(20), false)
         && t.checkExpect(becca.motMeetsFuelEfficiency(17), true);
   }
}
