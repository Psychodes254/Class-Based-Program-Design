import tester.*;

interface IComp {
  // is this object less than o?
  boolean lessThan(Object o);
}

abstract class AComp implements IComp{
  String name;
  int value;
  
  AComp(String name, int value) {
  this.name = name;
  this.value = value;
  }
  
  public boolean lessThan(Object o) {
    MenuItem m;
    if (o instanceof MenuItem) {
      m = (MenuItem)o;
    return this.value < m.value; 
    }
    else { 
      return false;
    }
  }
}

class MenuItem extends AComp {
  
  MenuItem(String name, int value) {
    super(name, value);
  }
}

class PhoneBook extends MenuItem{  
  PhoneBook(String name, int number){
    super(name, number);
  }
}

class ExamplesComps{
  ExamplesComps(){}
  
  MenuItem item1 = new MenuItem("Knife", 5);
  MenuItem item2 = new MenuItem("Thermos", 2);
  MenuItem item3 = new MenuItem("Cups", 3);
  
  PhoneBook contact1 = new PhoneBook("John", 85158498);
  PhoneBook contact2 = new PhoneBook("Kevin", 35836142);
  PhoneBook contact3 = new PhoneBook("Prince", 56903671);
  
  // test less than methods
  boolean testLessThan(Tester t) {
    return 
        t.checkExpect(this.item1.lessThan(item2), false) &&
        t.checkExpect(this.item2.lessThan(item3), true) &&
        t.checkExpect(this.contact3.lessThan(contact1), true) &&
        t.checkExpect(this.contact1.lessThan(contact2), false) &&
        t.checkExpect(this.contact1.lessThan(item2), false) &&
        t.checkExpect(this.item3.lessThan(contact2), true);
  }
}



