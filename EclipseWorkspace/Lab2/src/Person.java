import tester.*;

// to represent a mode of transportation
interface IMOT { }

//to represent a bicycle as a mode of transportation
class Bicycle implements IMOT{
  String brand;
  
  // the constructor
  Bicycle(String brand){
    this.brand = brand;
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
}

// to represent ExamplesIMOT
class ExamplesPerson{
  IMOT diamondback = new Bicycle("Diamondback");
  IMOT toyota = new Car("Toyota", 30);
  IMOT lamborghini = new Car("Lamborghini", 17);
 
  Person bob = new Person("Bob", diamondback);
  Person ben = new Person("Ben", toyota);
  Person becca = new Person("Becca", lamborghini);
}
