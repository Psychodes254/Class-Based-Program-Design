import tester.Tester;

// to represent interface IVehicle
interface IVehicle{
  // compute the cost of refueling this vehicle,
  // given the current price of fuel
  double cost(double cp);
}

// to represent class class Vehicle
abstract class AVehicle implements IVehicle{
  int mileage; // in kilometers
  int price;  // in dollars
  double tank;  // in liters
  
  AVehicle(int mileage, int price, double tank){
    this.mileage = mileage;
    this.price = price;
    this.tank = tank;
  }
  
  public double cost(double cp) {
    return tank * cp;
  }
}

// to represent class Car
class Car extends AVehicle{
  Car(int mileage, int price, double tank){
    super(mileage, price, tank);
  }
}

// to represent class Truck
class Truck extends AVehicle{
  Truck(int mileage, int price, double tank){
    super(mileage, price, tank);
  }
}

// to represent class Bus
class Bus extends AVehicle{
  Bus(int mileage, int price, double tank){
    super(mileage, price, tank);
  }
}

// to represent class Sedan
class Sedan extends Car{
  Sedan(int mileage, int price, double tank){
    super(mileage, price, tank);
  }
}

// to represent class ExamplesVehicles
class ExamplesVehicles{
  IVehicle car1 = new Car(12000, 15000, 35.0);
  IVehicle car2 = new Car(15000, 10000, 40.0);
  IVehicle car3 = new Car(20000, 12000, 60.0);
  
  IVehicle truck1 = new Truck(25000, 66000, 98.0);
  IVehicle truck2 = new Truck(31000, 78000, 128.0);
  IVehicle truck3 = new Truck(10000, 52000, 750.0);
  
  IVehicle bus1 = new Bus(18000, 60000, 600.0);
  IVehicle bus2 = new Bus(19000, 45000, 200.0);
  IVehicle bus3 = new Bus(29000, 33000, 500.0);
  
  Car sedan = new Sedan(5000, 17000, 38.5);
  
  // test the method cost
  boolean testCost(Tester t) {
      return 
      t.checkInexact(car1.cost(1.17), 40.95, 0.01) &&
      t.checkInexact(car2.cost(1.10), 44.0, 0.01) &&
      t.checkInexact(car3.cost(1.21), 72.6, 0.01) &&
      
      t.checkInexact(truck1.cost(1.40), 137.2, 0.01) &&
      t.checkInexact(truck2.cost(1.50), 192.0, 0.01) &&
      t.checkInexact(truck3.cost(1.45), 1087.0, 0.01) &&
      
      t.checkInexact(bus1.cost(1.45), 870.0, 0.01) &&
      t.checkInexact(bus2.cost(1.38), 276.0, 0.01) &&
      t.checkInexact(bus3.cost(1.30), 650.0, 0.01) &&
      
      t.checkInexact(sedan.cost(1.20), 46.2, 0.01);
    }
}







