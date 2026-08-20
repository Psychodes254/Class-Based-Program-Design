import tester.*;

interface IList {
  // sort this list, according to lessThan
  IList sort();
  // insert o into this (sorted) list
  IList insert(IComp o);
}


class Mt implements IList {
  Mt() {}
  
  public IList sort() {
    return this;
  }
  
  public IList insert(IComp o) {
    return new Cons(o,this);
  }
}

class Cons implements IList {
  IComp first;
  IList rest;
  
  Cons(IComp first, IList rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public IList sort() {
    return rest.sort().insert(first);
  }
  
  public IList insert(IComp o) {
    if (first.lessThan(o)) {
      return new Cons(first,rest.insert(o)); }
    else {
      return new Cons(o,this); }
  }
}

class ExamplesILists{
  ExamplesILists(){}
  
  MenuItem item1 = new MenuItem("Knife", 5);
  MenuItem item2 = new MenuItem("Thermos", 2);
  MenuItem item3 = new MenuItem("Cups", 3);
  
  PhoneBook contact1 = new PhoneBook("John", 85158498);
  PhoneBook contact2 = new PhoneBook("Kevin", 35836142);
  PhoneBook contact3 = new PhoneBook("Prince", 56903671);
  
  IList mt = new Mt();
  IList listItems = new Cons(item1, new Cons(item2, new Cons(item3, mt)));
  IList listPhone = new Cons(contact1, new Cons(contact2, new Cons(contact3, mt)));
  
  IList sortedItems = new Cons(item2, new Cons(item3, new Cons(item1, mt)));
  IList sortedPhone = new Cons(contact2, new Cons(contact3, new Cons(contact1, mt)));
  
  // test the sort method
  boolean testSort(Tester t) {
    return
        t.checkExpect(this.listItems.sort(), sortedItems) &&
        t.checkExpect(this.listPhone.sort(), sortedPhone);
  }
}


