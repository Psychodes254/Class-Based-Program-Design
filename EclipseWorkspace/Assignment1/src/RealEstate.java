import tester.*;

/*
+--------------------+
| RealEstate         | 
+--------------------+
| String kind        |
| int no. of rooms   |
| int price [in USD] |
| Address address    | 
+--------------------+  
           |
           |
           V
  +-----------------+
  | Address         | 
  +-----------------+
  | int strtNumber  |
  | String strtName |
  | String city     |
  +-----------------+ 
 */

// to represent an Address
class Address {
  int streetNumber;
  String streetName;
  String city;
  
  // the constructor
  Address(int streetNumber, String streetName, String city) {
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.city = city;
  }
}

// to represent Real Estate
class RealEstate {
  String kind;
  int rooms;
  int price;
  Address address;
  
  // the constructor
  RealEstate(String kind, int rooms, int price, Address address) {
    this.kind = kind;
    this.rooms = rooms;
    this.price = price;
    this.address = address;
  }
}

// examples for the class that represents the RealEstate
class ExamplesRealEstate {
  Address address1 = new Address(23, "Maple Street", "Brookline");
  Address address2 = new Address(5, "Joye Road", "Newton");
  Address address3 = new Address(83, "Winslow Road", "Waltham");
  
  RealEstate ranch = new RealEstate("Ranch", 7, 375000, this.address1);
  RealEstate colonial = new RealEstate("Colonial", 9, 450000, this.address2);
  RealEstate cape = new RealEstate("Cape", 6, 235000, this.address3);
}