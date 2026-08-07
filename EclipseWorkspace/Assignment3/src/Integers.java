import tester.*;

// a list of integers
interface ILin {
  int howMany(int i);
  boolean in(int i);
  int count();
  ILin removeDuplicates();
}

// a list of integers constructor
class Cin implements ILin{
  int first;
  ILin rest;
  
  Cin(int first, ILin rest){
    this.first = first;
    this.rest = rest;
  }
  
  public int howMany(int i) {
    if (this.first == i)
      return 1 + this.rest.howMany(i);
    else
      return this.rest.howMany(i);
  }
  
  public boolean in(int i) {
    return this.howMany(i) > 0;
  }
  
  public int count() {
    return 1 + this.rest.count();
  }
  
  public ILin removeDuplicates() {
    ILin restRemove = this.rest.removeDuplicates();
    if (restRemove.in(first)) {
      return restRemove;
    }
    else
      return new Cin(this.first, restRemove);
  }
}

// an empty list of integers
class MTin implements ILin{
  MTin(){}
  
  public int howMany(int i) {
    return 0;
  }
  
  public int count() {
    return 0;
  }
  
  public boolean in(int i) {
    return false;
  }
  
  public ILin removeDuplicates() {
    return this;
  }
}

// to represent abstract class AIntegers
abstract class ICollection implements ILin{
  ILin elements;
  
  ICollection(ILin elements) {
    this.elements = elements;
  }
  
  public boolean in(int i) {
    return this.elements.howMany(i) > 0;
  }
  
  // how often is i in the bag or set?
  public int howMany(int i) {
    return this.elements.howMany(i);
  }
  
  public int count() {
    return this.elements.count();
  }
  
  public abstract ILin removeDuplicates();
}

// to represent a set of integers class
//a set of integers: contains an integer at most once
class Set extends ICollection {
  Set(ILin elements) {
    super(elements);
  }
  
  // add i to this set unless it is already in there
  Set add(int i) {
    if (this.in(i)) {
      return this; }
    else {
      return new Set(new Cin(i,this.elements));
    }
  }
  
  public ILin removeDuplicates() {
    return new Set(this.elements.removeDuplicates());
  }
}

// to represent bag of integers class a bag of integers
class Bag extends ICollection {  
  Bag(ILin elements) {
    super(elements);
  }
  
  //add i to this bag
  Bag add(int i) {
    return new Bag(new Cin(i,this.elements));
  }
  
  public ILin removeDuplicates() {
    return new Bag(this.elements.removeDuplicates());
  }
}

// to represent ExamplesIntegers class
class ExamplesIntegers{
  ILin mt = new MTin();
  
  ILin list1 = new Cin(1, new Cin(2, new Cin(3, new Cin(4, new Cin(5, new MTin())))));
  ILin list2 = new Cin(6, new Cin(7, new Cin(8, new Cin(9, new Cin(10, new MTin())))));
  
  //a set built from list1 — note list1 has no duplicates, so this is a valid set
  Set set1 = new Set(list1);
  Set emptySet = new Set(new MTin());
  
  Bag bag1 = new Bag(list1);
  Bag emptyBag = new Bag(new MTin());
  
  Bag messyBag = new Bag(new Cin(3, new Cin(3, new Cin(5, new Cin(3, new MTin())))));
  
  ILin cleanBag = messyBag.removeDuplicates();

  // ILin tests 

  boolean testCount(Tester t) {
    return t.checkExpect(this.mt.count(), 0)
         && t.checkExpect(this.list1.count(), 5)
         && t.checkExpect(this.list2.count(), 5);
   }

  boolean testHowManyLin(Tester t) {
    return t.checkExpect(this.list1.howMany(3), 1)
       && t.checkExpect(this.list1.howMany(99), 0)
       && t.checkExpect(this.mt.howMany(1), 0);
  }

  boolean testInLin(Tester t) {
    return t.checkExpect(this.list1.in(3), true)
        && t.checkExpect(this.list1.in(99), false)
        && t.checkExpect(this.mt.in(1), false);
  }

  // Set tests
  boolean testSetCount(Tester t) {
    return t.checkExpect(this.set1.count(), 5)
        && t.checkExpect(this.emptySet.count(), 0);
  }

  boolean testSetIn(Tester t) {
    return t.checkExpect(this.set1.in(3), true)
        && t.checkExpect(this.set1.in(99), false);
  }

  // adding a value already in the set should NOT change it
  boolean testSetAddDuplicate(Tester t) {
    Set result = this.set1.add(3);
    return t.checkExpect(result.count(), 5) 
        && t.checkExpect(result, this.set1);  
  }

  // adding a new value should grow the set by one
  boolean testSetAddNew(Tester t) {
    Set result = this.set1.add(100);
    return t.checkExpect(result.count(), 6)
        && t.checkExpect(result.in(100), true);
  }

  boolean testEmptySetAdd(Tester t) {
    Set result = this.emptySet.add(7);
    return t.checkExpect(result.count(), 1)
        && t.checkExpect(result.in(7), true);
   }

 // Bag tests
  boolean testBagCount(Tester t) {
    return t.checkExpect(this.bag1.count(), 5)
        && t.checkExpect(this.emptyBag.count(), 0);
  }

  // bags allow duplicates, so adding an existing value grows the bag
  boolean testBagAddDuplicate(Tester t) {
    Bag result = this.bag1.add(3);
    return t.checkExpect(result.count(), 6)      
       && t.checkExpect(result.howMany(3), 2);     
  }

  boolean testBagAddNew(Tester t) {
    Bag result = this.bag1.add(100);
    return t.checkExpect(result.count(), 6)
        && t.checkExpect(result.in(100), true);
  }
  
  // test the removeDuplicates method
  boolean testRemoveDuplicates(Tester t) {
    return
        t.checkExpect(cleanBag.count(), 2) &&
        t.checkExpect(cleanBag.howMany(3), 1) &&
        t.checkExpect(cleanBag.howMany(5), 1);
  }
}