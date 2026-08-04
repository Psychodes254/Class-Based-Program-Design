// to represent interface ITaxiVehicle
interface ITaxiVehicle { }

// to represent abstract class ATaxiVehicle
abstract class ATaxiVehicle implements ITaxiVehicle{
  int idNum;
  int passengers;
  int pricePerMile;
  
  ATaxiVehicle(int idNum, int passengers, int pricePerMile){
    this.idNum = idNum;
    this.passengers = passengers;
    this.pricePerMile = pricePerMile;
  }
}

// to represent class Cab
class Cab extends ATaxiVehicle{
  Cab(int idNum, int passengers, int pricePerMile){
    super(idNum, passengers, pricePerMile);
  }
}

// to represent class Limo
class Limo extends ATaxiVehicle{
  int mimRental;
  
  Limo(int idNum, int passengers, int pricePerMile, int minRental){
    super(idNum, passengers, pricePerMile);
    this.mimRental = minRental;
  }
}

// to represent class Van
class Van extends ATaxiVehicle{
  boolean access;
  
  Van(int idNum, int passengers, int pricePerMile, boolean access){
    super(idNum, passengers, pricePerMile);
    this.access = access;
  }
}

class ExamplesTaxiVehicles {
  // examples of Cab
  Cab cab1 = new Cab(101, 4, 2);
  Cab cab2 = new Cab(102, 4, 3);
  Cab cab3 = new Cab(103, 3, 2);

  // examples of Limo
  Limo limo1 = new Limo(201, 8, 5, 3);
  Limo limo2 = new Limo(202, 10, 6, 2);
  Limo limo3 = new Limo(203, 6, 4, 4);

  // examples of Van
  Van van1 = new Van(301, 12, 3, true);
  Van van2 = new Van(302, 15, 4, false);
  Van van3 = new Van(303, 8, 3, true);

  // examples of ATaxiVehicle 
  ATaxiVehicle vehicle1 = this.cab1;
  ATaxiVehicle vehicle2 = this.limo1;
  ATaxiVehicle vehicle3 = this.van1;

  // examples of ITaxiVehicle 
  ITaxiVehicle taxi1 = this.cab2;
  ITaxiVehicle taxi2 = this.limo2;
  ITaxiVehicle taxi3 = this.van2;
}