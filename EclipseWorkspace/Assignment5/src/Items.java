// some grocery items
import tester.Tester;

interface IItem {
  // is this the same IItem as other?
  boolean same(IItem x);
  // is this Coffee?
  boolean isCoffee();
  // convert this to Coffee (if feasible)
  Coffee toCoffee();
  // is this Tea?
  boolean isTea();
  // convert this to Tea (if feasible)
  Tea toTea();
  // is this Chocolate?
  boolean isChocolate();
  // convert this to Chocolate (if feasible)
  Chocolate toChocolate();
}

// to represent abstract class for the Item 
abstract class AItem implements IItem{
  int price;
  
  AItem(int price){
    this.price = price;
  }

  public boolean same(IItem x) {
    return false;
  } 
   
  public boolean isCoffee() {
    return false;
  }
  
  public Coffee toCoffee() {
    throw new IllegalArgumentException("not a valid item!");
  }
   
  public boolean isTea(){
    return false;
  }
   
  public Tea toTea() {
    throw new IllegalArgumentException("not a valid item!");
  }
   
  public boolean isChocolate(){
    return false;
  }
   
  public Chocolate toChocolate() {
    throw new IllegalArgumentException("not a valid item!");
  }
}

// to represent examples and tests for objects in Item interface
class ExamplesItems{
  ExamplesItems(){}
  
  IItem ethi = new Coffee("Ethiopian",1200);
  IItem kona = new Coffee("Kona",2095);
  IItem ethi1300 = (new Coffee("Ethiopian",1300));
  IItem decaf1 = new Decaf("Ethiopian",1200,99);
  IItem decaf2 = new Decaf("Ethiopian",1200,98);
  
  IItem majani = new Tea("Majani", 800);
  IItem ketepa = new Tea("Ketepa", 950);
  IItem fahari = new Tea("Fahari", 790);
  
  IItem ruby = new Chocolate("Unsweetened", 5);
  IItem milk = new Chocolate("Bittersweet", 8);
  IItem gianduja = new Chocolate("Couverture", 12);

  // test the sameness of the coffee method
  boolean testSameCoffee(Tester t){
      return
      t.checkExpect(this.ethi.same(this.ethi), true) &&
      t.checkExpect(this.kona.same(this.ethi), false) &&
      t.checkExpect(this.ethi.same(this.ethi1300), false);
  }

  // test the sameness of the decaffeinated coffee
  boolean testSameDecaf(Tester t){
      return 
      t.checkExpect(this.decaf2.same(this.decaf1), false) &&
      t.checkExpect(this.decaf1.same(this.decaf1), true);
  }
  
  // test the type object method
  boolean testSameType(Tester t) {
    return
        t.checkExpect(this.ethi.isCoffee(), true) &&
        t.checkExpect(this.majani.isTea(), true) &&
        t.checkExpect(this.ketepa.isCoffee(), false) &&
        t.checkExpect(this.kona.isTea(), false) &&
        t.checkExpect(this.decaf1.isCoffee(), true) &&
        t.checkExpect(this.decaf2.isTea(), false) &&
        t.checkExpect(this.ruby.isCoffee(), false) &&
        t.checkExpect(this.gianduja.isChocolate(), true);
  }
  
  // test the sameness method of the Tea class
  boolean testSameTea(Tester t) {
    return 
        t.checkExpect(this.majani.same(this.majani), true) &&
        t.checkExpect(this.ketepa.same(fahari), false);
  }
  
  // test the sameness method of the chocolate class
  boolean testSameChocolate(Tester t) {
    return
        t.checkExpect(this.ruby.same(this.gianduja), false) &&
        t.checkExpect(this.milk.same(this.milk), true);
  }
  
  //test the toCoffee method
  boolean testToCoffee(Tester t) {
   return
       t.checkExpect(this.ethi.toCoffee(), this.ethi) &&
       t.checkExpect(this.decaf1.toCoffee(), this.decaf1) &&
       t.checkException(
           new IllegalArgumentException("not a valid item!"),
           this.majani, "toCoffee") &&
       t.checkException(
           new IllegalArgumentException("not a valid item!"),
           this.ruby, "toCoffee");
  }

  //test the toTea method
  boolean testToTea(Tester t) {
   return
       t.checkExpect(this.majani.toTea(), this.majani) &&
       t.checkExpect(this.ketepa.toTea(), this.ketepa) &&
       t.checkException(
           new IllegalArgumentException("not a valid item!"),
           this.ethi, "toTea") &&
       t.checkException(
           new IllegalArgumentException("not a valid item!"),
           this.ruby, "toTea");
  }

  //test the toChocolate method
  boolean testToChocolate(Tester t) {
   return
       t.checkExpect(this.ruby.toChocolate(), this.ruby) &&
       t.checkExpect(this.milk.toChocolate(), this.milk) &&
       t.checkException(
           new IllegalArgumentException("not a valid item!"),
           this.ethi, "toChocolate") &&
       t.checkException(
           new IllegalArgumentException("not a valid item!"),
           this.majani, "toChocolate");
  }
}
