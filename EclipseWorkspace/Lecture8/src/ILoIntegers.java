import tester.*;

// to represent a List of Integers
interface ILoIntegers { 
  // check if a list contains even integers
  boolean hasEven();
  
  // hasEven helper
  boolean hasEvenHelper(int num);
  
  // check if a list contains positive and odd integers
  boolean hasPositiveOdd();
  
  // hasPositiveOdd Helper
  boolean hasPositiveOddHelper(int num);
  
  // check if a list is in between five and ten
  boolean hasBetweenFiveAndTen();
  
  // hasBetweenFiveAndTen Helper
  boolean hasBetweenFiveAndTenHelper(int num);
  
 // check whether the list has an even, a positive odd,
 // and a number between five and ten
 boolean satisfiesCriteria();

 // satisfiesCriteria helper
 boolean satisfiesCriteriaHelper(boolean sawEven, boolean sawPosOdd, boolean sawBetween);
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
    return this.hasEvenHelper(first)
        || this.rest.hasEven();
  }
  
  public boolean hasEvenHelper(int num) {
    return (num % 2 == 0);
  }
  
  public boolean hasPositiveOdd() {
    if (this.hasPositiveOddHelper(first))
      return true;
    else 
      return this.rest.hasPositiveOdd();
  }
  
  public boolean hasPositiveOddHelper(int num) {
    return (num > 0 && ! hasEvenHelper(num));
  }
  
  public boolean hasBetweenFiveAndTen() {
    if (this.hasBetweenFiveAndTenHelper(first))
      return true;
    else
      return this.rest.hasBetweenFiveAndTen();
  }
  
  public boolean hasBetweenFiveAndTenHelper(int num) {
    return num >= 5 && num <= 10;
  }
  
  public boolean satisfiesCriteria() {
    return this.satisfiesCriteriaHelper(false, false, false);
  }
  
  public boolean satisfiesCriteriaHelper(boolean sawEven, boolean sawPosOdd, boolean sawBetween) {
    return this.rest.satisfiesCriteriaHelper(
        sawEven || this.hasEvenHelper(this.first),
        sawPosOdd || this.hasPositiveOddHelper(this.first),
        sawBetween || this.hasBetweenFiveAndTenHelper(this.first));
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
  
  public boolean hasPositiveOdd() {
    return false;
  }
  
  public boolean hasPositiveOddHelper(int num) {
    return false;
  }
  
  public boolean hasBetweenFiveAndTen() {
    return false;
  }
  
  public boolean hasBetweenFiveAndTenHelper(int num) {
    return false;
  }
  
  public boolean satisfiesCriteria() {
    return this.satisfiesCriteriaHelper(false, false, false);
  }
  
  public boolean satisfiesCriteriaHelper(boolean sawEven, boolean sawPosOdd, boolean sawBetween) {
    return sawEven && sawPosOdd && sawBetween;
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
  
  boolean testHasPositiveOdd(Tester t) {
    return t.checkExpect(mt.hasPositiveOdd(), false)
        && t.checkExpect(list1.hasPositiveOdd(), false)
        && t.checkExpect(list2.hasPositiveOdd(), true)  
        && t.checkExpect(list3.hasPositiveOdd(), true)  
        && t.checkExpect(list4.hasPositiveOdd(), false) 
        && t.checkExpect(list5.hasPositiveOdd(), true);  
}
  
  boolean testHasBetweenFiveAndTen(Tester t) {
    return t.checkExpect(mt.hasBetweenFiveAndTen(), false)
        && t.checkExpect(list1.hasBetweenFiveAndTen(), true)   
        && t.checkExpect(list2.hasBetweenFiveAndTen(), false)
        && t.checkExpect(list3.hasBetweenFiveAndTen(), true)   
        && t.checkExpect(list4.hasBetweenFiveAndTen(), false)
        && t.checkExpect(list5.hasBetweenFiveAndTen(), true);  
}
  
  boolean testSatisfiesCriteria(Tester t) {
    t.checkExpect(mt.satisfiesCriteria(), false);
    t.checkExpect(list1.satisfiesCriteria(), false);  
    t.checkExpect(list2.satisfiesCriteria(), false);
    t.checkExpect(list3.satisfiesCriteria(), true);                                                           
    t.checkExpect(list4.satisfiesCriteria(), false);  
    t.checkExpect(list5.satisfiesCriteria(), true);
    return true;
  }
}
