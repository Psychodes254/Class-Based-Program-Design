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
}

// to represent examples and tests for objects in Item interface
class ExamplesItems{
  ExamplesItems(){}
  
  IItem ethi = new Coffee("Ethiopian",1200);
  IItem kona = new Coffee("Kona",2095);
  IItem ethi1300 = (new Coffee("Ethiopian",1300));
  Decaf decaf1 = new Decaf("Ethiopian",1200,99);
  Decaf decaf2 = new Decaf("Ethiopian",1200,98);
  
  IItem majani = new Tea("Majani", 800);
  IItem ketepa = new Tea("Ketepa", 950);
  IItem fahari = new Tea("Fahari", 790);

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
        t.checkExpect(this.decaf2.isTea(), false);
  }
  
  // test the sameness method of the Tea class
  boolean testSameTea(Tester t) {
    return 
        t.checkExpect(this.majani.same(this.majani), true) &&
        t.checkExpect(this.ketepa.same(fahari), false);
  }
}
