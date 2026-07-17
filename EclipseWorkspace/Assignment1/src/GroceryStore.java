import tester.*;

//to represent GroceryStoreInfo class
class GroceryStoreInfo{
  String brandName;
  double weight; // grams
  double price; // cents

  // the constructor
  GroceryStoreInfo(String brandName, double weight, double price){
   this.brandName = brandName;
   this.weight = weight;
   this.price = price;
  }

  double unitPrice() {
    return (this.price / this.weight);
  }
  
  boolean lowerUnitPrice(double lowerUnit) {
    return (unitPrice() < lowerUnit);
  }
  
  boolean cheaperThan(GroceryStoreInfo that) {
    return (this.unitPrice() < that.unitPrice());
  }
}

//to represent IPackaging interface
interface IPackaging { }

//to represent Frozen packaging
class Frozen implements IPackaging { }

//to represent Fresh packaging
class Fresh implements IPackaging { }

//to represent Bottled packaging
class Bottled implements IPackaging { }

//to represent Canned packaging
class Canned implements IPackaging { }

// to represent IGroceryStore interface
interface IGroceryStore { }

// to represent IceCream class
class IceCream2 implements IGroceryStore {
  GroceryStoreInfo info;
  String flavor;
  
  // the constructor
  IceCream2(GroceryStoreInfo info, String flavor){
    this.info = info;
    this.flavor = flavor;
  }
}

// to represent Coffee class
class Coffee implements IGroceryStore {
  GroceryStoreInfo info;
  boolean decaffeinated;
  
  // the constructor
  Coffee(GroceryStoreInfo info, boolean decaffeinated){
    this.info = info;
    this.decaffeinated = decaffeinated;
  }
}

// to represent Juice class
class Juice implements IGroceryStore {
  GroceryStoreInfo info;
  String flavor;
  IPackaging packaging;
  
  // the constructor
  Juice(GroceryStoreInfo info, String flavor, IPackaging packaging){
    this.info = info;
    this.flavor = flavor;
    this.packaging = packaging;
  }
}

// to represent examples and test cases for the GroceryStore
class ExamplesGroceryStore {
  // Packaging
  IPackaging frozen = new Frozen();
  IPackaging fresh = new Fresh();
  IPackaging bottled = new Bottled();
  IPackaging canned = new Canned();

  // Grocery information
  GroceryStoreInfo benJerry =
      new GroceryStoreInfo("Ben & Jerry's", 473.0, 650.0);

  GroceryStoreInfo haagenDazs =
      new GroceryStoreInfo("Haagen-Dazs", 414.0, 720.0);

  GroceryStoreInfo starbucks =
      new GroceryStoreInfo("Starbucks", 340.0, 1190.0);

  GroceryStoreInfo nescafe =
      new GroceryStoreInfo("Nescafe", 200.0, 500.0);

  GroceryStoreInfo tropicana =
      new GroceryStoreInfo("Tropicana", 1000.0, 420.0);

  GroceryStoreInfo minuteMaid =
      new GroceryStoreInfo("Minute Maid", 1500.0, 900.0);

  // Grocery items
  IceCream2 vanilla =
      new IceCream2(benJerry, "Vanilla");

  IceCream2 chocolate =
      new IceCream2(haagenDazs, "Chocolate");

  Coffee regularCoffee =
      new Coffee(nescafe, false);

  Coffee decafCoffee =
      new Coffee(starbucks, true);

  Juice orangeJuice =
      new Juice(tropicana, "Orange", bottled);

  Juice appleJuice =
      new Juice(minuteMaid, "Apple", fresh);
  
  boolean testUnitPrice(Tester t) {
    return
        t.checkInexact(benJerry.unitPrice(), 650.0 / 473.0, 0.001)
     && t.checkInexact(haagenDazs.unitPrice(), 720.0 / 414.0, 0.001)
     && t.checkInexact(nescafe.unitPrice(), 2.5, 0.001)
     && t.checkInexact(tropicana.unitPrice(), 0.42, 0.001);
  }
  
  boolean testLowerUnitPrice(Tester t) {
    return
        t.checkExpect(benJerry.lowerUnitPrice(2.0), true)
     && t.checkExpect(haagenDazs.lowerUnitPrice(1.5), false)
     && t.checkExpect(nescafe.lowerUnitPrice(3.0), true)
     && t.checkExpect(starbucks.lowerUnitPrice(3.0), false)
     && t.checkExpect(tropicana.lowerUnitPrice(0.5), true)
     && t.checkExpect(minuteMaid.lowerUnitPrice(0.5), false);
  }
  
  boolean testCheaperThan(Tester t) {
    return
        t.checkExpect(benJerry.cheaperThan(haagenDazs), true)
     && t.checkExpect(haagenDazs.cheaperThan(benJerry), false)

     && t.checkExpect(nescafe.cheaperThan(starbucks), true)
     && t.checkExpect(starbucks.cheaperThan(nescafe), false)

     && t.checkExpect(tropicana.cheaperThan(minuteMaid), true)
     && t.checkExpect(minuteMaid.cheaperThan(tropicana), false)

     && t.checkExpect(tropicana.cheaperThan(benJerry), true)
     && t.checkExpect(starbucks.cheaperThan(haagenDazs), false);
  }
}