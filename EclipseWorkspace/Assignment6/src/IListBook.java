import tester.*;

interface IListBook<T> {
  IListBook<T> sort(Comparator<T> order);
  IListBook<T> insert(T data, Comparator<T> order);
}

class MtList<T> implements IListBook<T> {
  MtList() {}
  
  public IListBook<T> sort(Comparator<T> order) {
    return this;
  }
  
  public IListBook<T> insert(T data, Comparator<T> order) {
    return new ConsList<T>(data, this);
  }
}

class ConsList<T> implements IListBook<T> {
  T first;
  IListBook<T> rest;
  
  ConsList(T first, IListBook<T> rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public IListBook<T> sort(Comparator<T> order) {
    return this.rest.sort(order).insert(this.first, order);
  }
  
  public IListBook<T> insert(T data, Comparator<T> order) {
    if (order.compare(data, this.first) <= 0) {
      return new ConsList<T>(data, this);
    }
    else {
      return new ConsList<T>(this.first, this.rest.insert(data, order));
    }
  }
}

class ExamplesIListBooks{
  ExamplesIListBooks(){}
  
  Book book1 = new Book("Dune", "Herbert", 15);
  Book book2 = new Book("Emma", "Austen", 48);
  Book book3 = new Book("Cujo", "King", 30);
  
  IListBook<Book> mt = new MtList<Book>();
  IListBook<Book> listBook1 = new ConsList<Book>(book1, new ConsList<Book>(book2, new ConsList<Book>(book3, mt)));
  
  IListBook<Book> listByTitle = new ConsList<Book>(book3, new ConsList<Book>(book1, new ConsList<Book>(book2, mt)));
  IListBook<Book> listByAuthor = new ConsList<Book>(book2, new ConsList<Book>(book1, new ConsList<Book>(book3, mt)));
  IListBook<Book> listByPrice = new ConsList<Book>(book1, new ConsList<Book>(book3, new ConsList<Book>(book2, mt)));
  
  // test methods for sorted lists
  boolean testSortedLists(Tester t) {
    return
        t.checkExpect(this.listBook1.sort(new BooksByTitle()), this.listByTitle) &&
        t.checkExpect(this.listBook1.sort(new BooksByAuthor()), this.listByAuthor) &&
        t.checkExpect(this.listBook1.sort(new BooksByPrice()), this.listByPrice);
  }
}


