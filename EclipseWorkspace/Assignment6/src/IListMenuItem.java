import tester.*;

interface IListMenuItem {
  // sort this list, according to lessThan
  IListMenuItem sort();
  // insert o into this (sorted) list
  IListMenuItem insert(IComp o);
}


class Mt implements IListMenuItem {
  Mt() {}
  
  public IListMenuItem sort() {
    return this;
  }
  
  public IListMenuItem insert(IComp o) {
    return new Cons(o,this);
  }
}

class Cons implements IListMenuItem {
  IComp first;
  IListMenuItem rest;
  
  Cons(IComp first, IListMenuItem rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public IListMenuItem sort() {
    return rest.sort().insert(first);
  }
  
  public IListMenuItem insert(IComp o) {
    if (first.lessThan(o)) {
      return new Cons(first,rest.insert(o)); }
    else {
      return new Cons(o,this); }
  }
}

class ExamplesIListMenuItems{
  ExamplesIListMenuItems(){}
  
  MenuItem item1 = new MenuItem("Knife", 5);
  MenuItem item2 = new MenuItem("Thermos", 2);
  MenuItem item3 = new MenuItem("Cups", 3);
  
  PhoneBook contact1 = new PhoneBook("John", 85158498);
  PhoneBook contact2 = new PhoneBook("Kevin", 35836142);
  PhoneBook contact3 = new PhoneBook("Prince", 56903671);
  
  IListMenuItem mt = new Mt();
  IListMenuItem listItems = new Cons(item1, new Cons(item2, new Cons(item3, mt)));
  IListMenuItem listPhone = new Cons(contact1, new Cons(contact2, new Cons(contact3, mt)));
  
  IListMenuItem sortedItems = new Cons(item2, new Cons(item3, new Cons(item1, mt)));
  IListMenuItem sortedPhone = new Cons(contact2, new Cons(contact3, new Cons(contact1, mt)));
  
  // test the sort method
  boolean testSort(Tester t) {
    return
        t.checkExpect(this.listItems.sort(), sortedItems) &&
        t.checkExpect(this.listPhone.sort(), sortedPhone);
  }
}


