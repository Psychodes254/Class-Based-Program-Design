import tester.*;

// to represent a List of Integers
interface ILoIntegers { 
  // check if a list contains even integers
  boolean hasEven();
  
  // hasEven helper
  boolean hasEvenHelper(int num);
}

class ConsLoIntegers implements ILoIntegers{
  int first;
  ILoIntegers rest;
  
  // the constructor
  ConsLoIntegers(int first, ILoIntegers rest){
    this.first = first;
    this.rest = rest;
  }
  
  public boolean hasEven() {
    if (this.hasEvenHelper(first))
      return true;
    else
      return this.rest.hasEven();
  }
  
  public boolean hasEvenHelper(int num) {
    return (num % 2 == 0);
  }
}

class MtLoIntegers implements ILoIntegers{
  MtLoIntegers(){}
  
  public boolean hasEven() {
    return false;
  }
  
  public boolean hasEvenHelper(int num) {
    return false;
  }
}

class ExamplesIntegers {  
  ILoIntegers mt = new MtLoIntegers();

  ILoIntegers list1 =
      new ConsLoIntegers(8, mt);

  ILoIntegers list2 =
      new ConsLoIntegers(3,
          new ConsLoIntegers(11, mt));

  ILoIntegers list3 =
      new ConsLoIntegers(5,
          new ConsLoIntegers(10, mt));

  ILoIntegers list4 =
      new ConsLoIntegers(-4,
          new ConsLoIntegers(-3, mt));

  ILoIntegers list5 =
      new ConsLoIntegers(7,
          new ConsLoIntegers(8,
              new ConsLoIntegers(3, mt)));
  
  boolean testHasEven(Tester t) {
    return t.checkExpect(mt.hasEven(), false)
        && t.checkExpect(list1.hasEven(), true)   
        && t.checkExpect(list2.hasEven(), false)   
        && t.checkExpect(list3.hasEven(), true)  
        && t.checkExpect(list4.hasEven(), true)   
        && t.checkExpect(list5.hasEven(), true); 
}
}
