// to represent class class Vehicle
class Vehicle{
  int mileage;
  int price;
  
  Vehicle(int mileage, int price){
    this.mileage = mileage;
    this.price = price;
  }
}

// to represent class Sedan
class Sedan extends Vehicle{
  Sedan(int mileage, int price){
    super(mileage, price);
  }
}
