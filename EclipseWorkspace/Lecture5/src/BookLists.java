import tester.*;

/*
               +--------------------------------+
               | ILoBook                        |<----------------------+
               +--------------------------------+                       |
               +--------------------------------+                       |
               | int count()                    |                       |
               | double salePrice(int discount) |                       |
               | ILoBook allBefore(int year)    |                       |
               | ILoBook sortByPrice()          |                       |
               +--------------------------------+                       |
                                |                                       |
                               / \                                      |
                               ---                                      |
                                |                                       |
                  -----------------------------                         |
                  |                           |                         |
+--------------------------------+   +--------------------------------+ |
| MtLoBook                       |   | ConsLoBook                     | |
+--------------------------------+   +--------------------------------+ |
+--------------------------------+ +-| Book first                     | |
| int count()                    | | | ILoBook rest                   |-+
| double totalPrice()            | | +--------------------------------+
| ILoBook cheaperThan(int price) | | | int count()                    |
| double salePrice(int discount) | | | double totalPrice()            |
| ILoBook allBefore(int year)    | | | ILoBook cheaperThan(int price) |
| ILoBook sortByPrice()          | | | double salePrice(int discount) |
+--------------------------------+ | | ILoBook allBefore(int year)    |
                                   | | ILoBook sortByPrice()          |
                                   | +--------------------------------+
                                   v
                   +--------------------------------+
                   | Book                           |
                   +--------------------------------+
                   | String title                   |
                   | String author                  |
                   | int year                       |
                   | double price                   |
                   +--------------------------------+
                   | double salePrice(int discount) |
                   +--------------------------------+ 
*/

// to represent Book class
class Book {
  String title;
  String author;
  int year;
  double price;
  
  // the constructor
  Book(String title, String author, int year, double price) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.price = price;
  }
  
  double salePrice(int discount) {
    return this.price - (this.price * discount) / 100;
  }
}

// to represent ILoBook interface
interface ILoBook {
  // count the books in this list
  int count();
  
  // calculate the total price of all the books in a given list of Books
  double totalPrice();
  
  // produce the books that are cheaper than the price given
  ILoBook cheaperThan(int price);
  
  // calculate the total sale price of all books in this list for a given discount 
  double salePrice(int discount);
  
  // produce a list of all books published before the given date
  // from this list of books
  ILoBook allBefore(int year);
}

// to represent MtLoBook class
class MtLoBook implements ILoBook {
  MtLoBook(){}
  
  public int count() {
    return 0;
  }
  
  public double totalPrice() {
    return 0;
  }
  
  public ILoBook cheaperThan(int price) {
    return this;
  }
  
  public double salePrice(int discount) {
    return 0;
  }
  
  public ILoBook allBefore(int year) {
    return this;
  }
}

// to represent ConsLoBook
class ConsLoBook implements ILoBook {
  Book first;
  ILoBook rest;
  
  // the constructor
  ConsLoBook(Book first, ILoBook rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public int count() {
    return 1 + rest.count();
  }
  
  public double totalPrice() {
    return this.first.price + this.rest.totalPrice();
  }
  
  public ILoBook cheaperThan(int price) {
    if (this.first.price < price)
      return new ConsLoBook(this.first, this.rest.cheaperThan(price));
    else
      return this.rest.cheaperThan(price);
  }
  
  public double salePrice(int discount) {
    return this.first.salePrice(discount) + rest.salePrice(discount);
  }
  
  public ILoBook allBefore(int year) {
    if (this.first.year < year)
      return new ConsLoBook(this.first, this.rest.allBefore(year));
    else
      return this.rest.allBefore(year);
  }
}

// tests and examples for ILoBook interface and Book class
class ILoBooksExample{
  ILoBooksExample(){}
  
  Book hp1 = new Book("HP1", "JKR", 1997, 20);
  Book hp2 = new Book("HP2", "JKR", 2000, 30);
  Book hp3 = new Book("HP3", "JKR", 2004, 40);
  
  ILoBook hpList3 = new ConsLoBook(hp1, new ConsLoBook(hp2, new ConsLoBook(hp3, new MtLoBook())));
  ILoBook emptyList = new MtLoBook();
  ILoBook hpList2 = new ConsLoBook(hp2, new ConsLoBook(hp3, new MtLoBook()));
  ILoBook hpList1 = new ConsLoBook(hp1, new MtLoBook());
  
  // test the count method
  boolean testCount(Tester t) {
    return
    t.checkExpect(this.hpList3.count(), 3) &&
    t.checkExpect(this.emptyList.count(), 0) &&
    t.checkExpect(this.hpList2.count(), 2) &&
    t.checkExpect(this.hpList1.count(), 1);
  }
  
  // test the totalPrice method
  boolean testTotalPrice(Tester t) {
    return 
    t.checkExpect(this.hpList3.totalPrice(), 90.0) &&
    t.checkExpect(this.hpList2.totalPrice(), 70.0) &&
    t.checkExpect(this.hpList1.totalPrice(), 20.0) &&
    t.checkExpect(this.emptyList.totalPrice(), 0.0);
  }
  
  // test the cheaperThan method
  boolean testCheaperThan(Tester t) {
    return
    t.checkExpect(this.emptyList.cheaperThan(10), new MtLoBook()) &&
    t.checkExpect(this.hpList1.cheaperThan(25), new ConsLoBook(hp1, new MtLoBook())) &&
    t.checkExpect(this.hpList2.cheaperThan(35), new ConsLoBook(hp2, new MtLoBook())) &&
    t.checkExpect(this.hpList3.cheaperThan(30), new ConsLoBook(hp1, new MtLoBook())) &&
    t.checkExpect(this.hpList3.cheaperThan(40), new ConsLoBook(hp1, new ConsLoBook(hp2, new MtLoBook()))) &&
    t.checkExpect(this.hpList3.cheaperThan(10), new MtLoBook());
  }
  
  // test the salePrice method
  boolean testSalePrice(Tester t) {
    return 
    t.checkExpect(this.hpList3.salePrice(20), 72.0) &&
    t.checkExpect(this.hpList2.salePrice(25), 52.5) &&
    t.checkExpect(this.hpList1.salePrice(30), 14.0) &&
    t.checkExpect(this.emptyList.salePrice(15), 0.0);
  }
  
  // test the allBefore method
  boolean testAllBefore(Tester t) {
    return 
    t.checkExpect(this.emptyList.allBefore(1998), new MtLoBook()) && 
    t.checkExpect(this.hpList3.allBefore(2008), hpList3) &&
    t.checkExpect(this.hpList2.allBefore(2001), new ConsLoBook(hp2, new MtLoBook())) &&
    t.checkExpect(this.hpList3.allBefore(1995), new MtLoBook()) &&
    t.checkExpect(this.hpList1.allBefore(2000), new ConsLoBook(hp1, new MtLoBook()));
  }
}














