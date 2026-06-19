import tester.*;

/*
+--------------------+
| RealEstate         | 
+--------------------+
| String kind        |
| int no. of rooms   |
| int price [in USD] |
| String address     | 
+--------------------+  
 */

// to represent Real Estate
class RealEstate {
  String kind;
  int rooms;
  int price;
  String address;
  
  // the constructor
  RealEstate(String kind, int rooms, int price, String addreass) {
    this.kind = kind;
    this.rooms = rooms;
    this.price = price;
    this.address = addreass;
  }
}

// examples for the class that represents the RealEstate
class ExamplesRealEstate {
  RealEstate ranch = new RealEstate("Ranch", 7, 375000, "23 Maple Street, Brookline");
  RealEstate colonial = new RealEstate("Colonial", 9, 450000, "5 Joye Road, Newton");
  RealEstate cape = new RealEstate("Cape", 6, 235000, "83 Winslow Road, Waltham");
}