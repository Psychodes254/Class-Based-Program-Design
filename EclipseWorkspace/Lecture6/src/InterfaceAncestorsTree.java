import tester.*;

// to represent ILoS
interface ILoString  { 
  //append another list of names onto the end of this list
  ILoString append(ILoString other);  
}

// to represent ConsLoString 
class ConsLoString implements ILoString  {
  String first;
  ILoString rest;
  
  // the constructor
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public ILoString append(ILoString other) {
    return new ConsLoString(this.first, this.rest.append(other));
  }
}

// to represent MtLoString 
class MtLoString implements ILoString {
  MtLoString () { }
  
  public ILoString append(ILoString other) {
    return other;
  }
}

// to represent IAT
interface IAT { 
  // count no. of people in a given tree
  int count();
  
  // count helper
  int countHelper();
  
  // count the number of female in a given tree
  int countFemale();
  
  // countFemale helper
  int countFemaleHelper();
  
  // check if the ancestor tree is well formed according to age
  boolean wellFormed();
  
  // wellFormed helper
  boolean wellFormedHelper(int childYOB);
  
  // compute how many female are older than 40
  int countFemaleOver40();
  
  // countFemaleOver40 helper
  int countFemaleOver40Helper();
  
  // list of this person's name followed by all their ancestors' names
  ILoString ancNames();
  
  // compare the younger ancestor in IAt
  IAT youngerIAT(IAT other);
  
  // youngerIAT helper method
  IAT youngerIATHelper(IAT other, int otherYOB);
  
  // check the youngest parent
  IAT youngestParent();
  
  // check the youngest grandparent
  IAT youngestGrandparent();
  
  // check the youngest ancestor in a generation
  IAT youngestAncAtGen(int gen);
  
  // check the number of male in ancestor tree
  int countMale();
  
  // countMale Helper
  int countMaleHelper();
}

// to represent Unknown class
class Unknown implements IAT {
  Unknown() { }
  
  public int count() {
    return 0;
  }
  
  public int countHelper() {
    return 0;
  }
  
  public int countFemale() {
    return 0;
  }
  
  public int countFemaleHelper() {
    return 0;
  }
  
  public boolean wellFormed() {
    return true;
  }
  
  public boolean wellFormedHelper(int childYOB) {
    return true;
  }
  
  public int countFemaleOver40() {
    return 0;
  }
  
  public int countFemaleOver40Helper() {
    return 0;
  }
  
  public ILoString ancNames() {
    return new MtLoString();
  }
  
  public IAT youngerIAT(IAT other) {
    return other;
  }
  
  public IAT youngerIATHelper(IAT other, int otherYOB) {
    return other;
  }
  
  public IAT youngestParent() {
    return new Unknown();
  }
  
  public IAT youngestGrandparent() {
    return new Unknown();
  }
  
  public IAT youngestAncAtGen(int gen) {
    if ( gen == 0)
      return this;
    else 
      return new Unknown();
  }
  
  public int countMale() {
    return 0;
  }
  
  public int countMaleHelper() {
    return 0;
  }
}

// to represent Person class
class Person implements IAT {
  String name;
  int yob;
  IAT mom;
  IAT dad;
  boolean isMale;
  
  // the constructor
  Person(String name, int yob, boolean isMale, IAT mom, IAT dad) {
    this.name = name;
    this.yob = yob;
    this.isMale = isMale;
    this.mom = mom;
    this.dad = dad;
  }
  
  public int count() {
    return this.mom.countHelper() + this.dad.countHelper();
  }
  
  public int countHelper() {
    return 1 + this.mom.countHelper() + this.dad.countHelper();
  }
  
  public int countFemale() {
    return this.dad.countFemaleHelper() + this.mom.countFemaleHelper();
  }
  
  public int countFemaleHelper() {
    if (this.isMale == false)
      return 1 + this.mom.countFemaleHelper() + this.dad.countFemaleHelper();
    else
      return this.mom.countFemaleHelper() + this.dad.countFemaleHelper();
  }
  
  public boolean wellFormed() {
    return this.mom.wellFormedHelper(yob) &&
           this.dad.wellFormedHelper(yob);
  }
  
  public boolean wellFormedHelper(int childYOB) {
    return (this.yob < childYOB) &&
           this.dad.wellFormedHelper(childYOB) &&
           this.mom.wellFormedHelper(childYOB);
  }
  
  public int countFemaleOver40() {
    return this.dad.countFemaleOver40Helper() + this.mom.countFemaleOver40Helper();
  }
  
  public int countFemaleOver40Helper() {
    if (this.isMale == false && 2015 - this.yob > 40)
      return 1 + this.mom.countFemaleOver40Helper() + this.dad.countFemaleOver40Helper();
    else
      return this.mom.countFemaleOver40Helper() + this.dad.countFemaleOver40Helper();
  }
  
  public ILoString ancNames() {
    return new ConsLoString(this.name, this.mom.ancNames().append(this.dad.ancNames()));
  }
  
  public IAT youngerIAT(IAT other) {
    return other.youngerIATHelper(this, this.yob);
  }
  
  public IAT youngerIATHelper(IAT other, int otherYOB) {
    if (this.yob > otherYOB)
      return this;
    else
      return other;
  }
  
  public IAT youngestParent() {
    return this.dad.youngerIAT(mom);
  }
  
  public IAT youngestGrandparent() {
    return this.mom.youngestParent().youngerIAT(this.dad.youngestParent());
  }
  
  public IAT youngestAncAtGen(int gen) {
    if ( gen == 0)
      return this;
    else 
      return this.mom.youngestAncAtGen(gen-1).youngerIAT(this.dad.youngestAncAtGen(gen-1));
  }
  
  public int countMale() {
    return this.mom.countMaleHelper() + this.dad.countMaleHelper();
  }
  
  public int countMaleHelper() {
    if (this.isMale)
      return 1 + this.mom.countFemaleHelper() + this.dad.countFemaleHelper();
    else
      return this.mom.countFemaleHelper() + this.dad.countFemaleHelper();
  }
}

// examples and tests for the class hierarchy that represents IAT
class ExamplesIAT {
  IAT enid = new Person("Enid", 1904, false, new Unknown(), new Unknown());
  IAT edward = new Person("Edward", 1902, true, new Unknown(), new Unknown());
  IAT emma = new Person("Emma", 1906, false, new Unknown(), new Unknown());
  IAT eustace = new Person("Eustace", 1907, true, new Unknown(), new Unknown());

  IAT david = new Person("David", 1925, true, new Unknown(), this.edward);
  IAT daisy = new Person("Daisy", 1927, false, new Unknown(), new Unknown());
  IAT dana = new Person("Dana", 1933, false, new Unknown(), new Unknown());
  IAT darcy = new Person("Darcy", 1930, false, this.emma, this.eustace);
  IAT darren = new Person("Darren", 1935, true, this.enid, new Unknown());
  IAT dixon = new Person("Dixon", 1936, true, new Unknown(), new Unknown());

  IAT clyde = new Person("Clyde", 1955, true, this.daisy, this.david);
  IAT candace = new Person("Candace", 1960, false, this.dana, this.darren);
  IAT cameron = new Person("Cameron", 1959, true, new Unknown(), this.dixon);
  IAT claire = new Person("Claire", 1956, false, this.darcy, new Unknown());

  IAT bill = new Person("Bill", 1980, true, this.candace, this.clyde);
  IAT bree = new Person("Bree", 1981, false, this.claire, this.cameron);

  IAT andrew = new Person("Andrew", 2001, true, this.bree, this.bill);
  
  // test the count method
  boolean testCount(Tester t) {
    return
    t.checkExpect(this.enid.count(), 0) &&
    t.checkExpect(this.andrew.count(), 16) &&
    t.checkExpect(this.david.count(), 1) &&
    t.checkExpect(this.enid.count(), 0) &&
    t.checkExpect(new Unknown().count(), 0);
  }
  
  // test the countFemale method
  boolean testCountFemale(Tester t) {
    return
    t.checkExpect(this.emma.countFemale(), 0)  &&
    t.checkExpect(this.andrew.countFemale(), 8);
  }
  
  // test the countFemaleOver40 method
  boolean testFemaleAncOver40(Tester t) {
    return
        t.checkExpect(this.andrew.countFemaleOver40(), 7) &&
        t.checkExpect(this.bree.countFemaleOver40(), 3) &&
        t.checkExpect(this.darcy.countFemaleOver40(), 1) &&
        t.checkExpect(this.enid.countFemaleOver40(), 0) &&
        t.checkExpect(new Unknown().countFemaleOver40(), 0);
}
  
  // test the wellFormed method
  boolean testWellFormed(Tester t) {
    return
    t.checkExpect(this.andrew.wellFormed(), true) &&
    t.checkExpect(new Unknown().wellFormed(), true) &&
    t.checkExpect(
        new Person("Zane", 2000, true, this.andrew, this.bree).wellFormed(),
        false);
  }
  
  // test the AncNames method
  boolean testAncNames(Tester t) {
    return
    t.checkExpect(this.david.ancNames(),
        new ConsLoString("David",
            new ConsLoString("Edward", new MtLoString()))) &&
    t.checkExpect(this.eustace.ancNames(),
        new ConsLoString("Eustace", new MtLoString())) &&
    t.checkExpect(new Unknown().ancNames(), new MtLoString());
  }
  
  // test the youngerIAT method
  boolean testYoungerIAT(Tester t) {
    return
    t.checkExpect(this.bree.youngerIAT(bill), bree) && 
    t.checkExpect(this.cameron.youngerIAT(candace), candace);
  }
  
  // test the youngestParent method
  boolean testYoungestParent(Tester t) {
    return 
    t.checkExpect(andrew.youngestParent(), bree) &&
    t.checkExpect(bree.youngestParent(), cameron);
  }
  
  // test the youngestGrandparent method
  boolean testYoungestGrandparent(Tester t) {
      return
      t.checkExpect(this.emma.youngestGrandparent(), new Unknown()) &&
      t.checkExpect(this.david.youngestGrandparent(), new Unknown()) &&
      t.checkExpect(this.claire.youngestGrandparent(), this.eustace) &&
      t.checkExpect(this.bree.youngestGrandparent(), this.dixon) &&
      t.checkExpect(this.andrew.youngestGrandparent(), this.candace) &&
      t.checkExpect(new Unknown().youngestGrandparent(), new Unknown());
  }
  
  // test the youngestAncAtGen method
  boolean testYoungestAncAtGen(Tester t) {
    return 
    t.checkExpect(andrew.youngestAncAtGen(0), andrew) &&
    t.checkExpect(andrew.youngestAncAtGen(1), bree) &&
    t.checkExpect(andrew.youngestAncAtGen(2), candace) &&
    t.checkExpect(andrew.youngestAncAtGen(3), dixon) &&
    t.checkExpect(andrew.youngestAncAtGen(4), eustace);
  }
  
  // test the countMale method
  boolean testCountMale(Tester t) {
    return
    t.checkExpect(this.enid.countMale(), 0) &&
    t.checkExpect(this.edward.countMale(), 0) &&
    t.checkExpect(this.david.countMale(), 1) &&
    t.checkExpect(this.darcy.countMale(), 1) &&
    t.checkExpect(this.claire.countMale(), 1) &&
    t.checkExpect(this.bill.countMale(), 4) &&
    t.checkExpect(this.bree.countMale(), 3) &&
    t.checkExpect(this.andrew.countMale(), 8) &&
    t.checkExpect(new Unknown().countMale(), 0);
  }
}