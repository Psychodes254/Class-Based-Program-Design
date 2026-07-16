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
 
  // check whether distinct elements can be assigned, one each,
  // to satisfy even / positiveOdd / between-5-and-10
  boolean satisfiesStrictCriteria();
  
  // satisfiesStrictCriteria helper
  boolean satisfiesStrictHelper(boolean usedEven, boolean usedPosOdd, boolean usedBetween);
  
  // check whether EVERY element can be assigned, one each, to
  // satisfy even / positiveOdd / between-5-and-10, with no leftovers
  boolean satisfiesExactCriteria();
  
  // satisfiesExactCriteria helper
  boolean satisfiesExactHelper(boolean usedEven, boolean usedPosOdd, boolean usedBetween);
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
  
  public boolean satisfiesStrictCriteria() {
    return this.satisfiesStrictHelper(false, false, false);
  }

  public boolean satisfiesStrictHelper(boolean usedEven, boolean usedPosOdd, boolean usedBetween) {
    boolean tryEven = !usedEven && this.hasEvenHelper(this.first)
        && this.rest.satisfiesStrictHelper(true, usedPosOdd, usedBetween);

    boolean tryPosOdd = !usedPosOdd && this.hasPositiveOddHelper(this.first)
        && this.rest.satisfiesStrictHelper(usedEven, true, usedBetween);

    boolean tryBetween = !usedBetween && this.hasBetweenFiveAndTenHelper(this.first)
        && this.rest.satisfiesStrictHelper(usedEven, usedPosOdd, true);

    boolean trySkip = this.rest.satisfiesStrictHelper(usedEven, usedPosOdd, usedBetween);

    return tryEven || tryPosOdd || tryBetween || trySkip;
  }
  
  public boolean satisfiesExactCriteria() {
    return this.satisfiesExactHelper(false, false, false);
  }

  public boolean satisfiesExactHelper(boolean usedEven, boolean usedPosOdd, boolean usedBetween) {
    boolean tryEven = !usedEven && this.hasEvenHelper(this.first)
        && this.rest.satisfiesExactHelper(true, usedPosOdd, usedBetween);

    boolean tryPosOdd = !usedPosOdd && this.hasPositiveOddHelper(this.first)
        && this.rest.satisfiesExactHelper(usedEven, true, usedBetween);

    boolean tryBetween = !usedBetween && this.hasBetweenFiveAndTenHelper(this.first)
        && this.rest.satisfiesExactHelper(usedEven, usedPosOdd, true);

    return tryEven || tryPosOdd || tryBetween;
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
  
  public boolean satisfiesStrictCriteria() {
    return this.satisfiesStrictHelper(false, false, false);
  }

  public boolean satisfiesStrictHelper(boolean usedEven, boolean usedPosOdd, boolean usedBetween) {
    return usedEven && usedPosOdd && usedBetween;
  }
  
  public boolean satisfiesExactCriteria() {
    return this.satisfiesExactHelper(false, false, false);
  }

  public boolean satisfiesExactHelper(boolean usedEven, boolean usedPosOdd, boolean usedBetween) {
    return usedEven && usedPosOdd && usedBetween;
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
  
  ILoIntegers list6 = 
      new ConsLoIntegers(6, new ConsLoIntegers(5, mt));

  ILoIntegers list7 = 
      new ConsLoIntegers(6, new ConsLoIntegers(5, new ConsLoIntegers(6, mt)));

  ILoIntegers list8 =
      new ConsLoIntegers(6, new ConsLoIntegers(5, new ConsLoIntegers(6, mt)));

  ILoIntegers list9 =
      new ConsLoIntegers(6, new ConsLoIntegers(5,
          new ConsLoIntegers(42, new ConsLoIntegers(6, mt))));
  
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
  
  boolean testSatisfiesStrict(Tester t) {
    return t.checkExpect(mt.satisfiesStrictCriteria(), false)
        && t.checkExpect(list6.satisfiesStrictCriteria(), false)
        && t.checkExpect(list7.satisfiesStrictCriteria(), true);
}
  
  boolean testSatisfiesExact(Tester t) {
    return t.checkExpect(mt.satisfiesExactCriteria(), false)
        && t.checkExpect(list8.satisfiesExactCriteria(), true)
        && t.checkExpect(list9.satisfiesExactCriteria(), false);
  }
}
