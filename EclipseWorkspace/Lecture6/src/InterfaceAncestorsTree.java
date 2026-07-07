import tester.*;

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
    t.checkExpect(this.andrew.count(), 16);
  }
  
  // test the countFemale method
  boolean testCountFemale(Tester t) {
    return
    t.checkExpect(this.emma.countFemale(), 0)  &&
    t.checkExpect(this.andrew.countFemale(), 8);
  }
}
