import tester.*;

abstract class ABST<T>{
  Comparator<T> order;
  
  ABST(Comparator<T> order){
    this.order = order;
  }
  
  abstract ABST<T> insert(T data);
  abstract boolean present(T data);
}

class Leaf<T> extends ABST<T>{
  Leaf(Comparator<T> order){
    super(order);
  }
  
  public ABST<T> insert(T data){
    return new Node<T>(this.order, data, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }
  
  public boolean present(T data){
    return false;
  }
}

class Node<T> extends ABST<T>{
  T data;
  ABST<T> left;
  ABST<T> right;
  
  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right){
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  public ABST<T> insert(T data){
    if (this.order.compare(data, this.data) <= 0){
      return new Node<T>(this.order, data, this.left.insert(data), this.right);
    }
    else {
      return new Node<T>(this.order, data, this.left, this.right.insert(data));
    }
  }
  
  public boolean present(T data){
    int presence = this.order.compare(data, this.data);
    
    if (presence == 0) {
      return true;
    }
    else if (presence < 0) {
      return this.left.present(data);
    }
    else {
      return this.right.present(data);
    }
  }
}

class ExamplesBSTs{
  ExamplesBSTs(){}
  
  Book book1 = new Book("Dune", "Herbert", 50);
  Book book2 = new Book("Emma", "Austen", 48);
  Book book3 = new Book("Cujo", "King", 15);
  
  Comparator<Book> byTitle = new BooksByTitle();
  Comparator<Book> byAuthor = new BooksByAuthor();
  Comparator<Book> byPrice = new BooksByPrice();
  
  ABST<Book> tree = new Leaf<Book>(byTitle).insert(book1);
  ABST<Book> wholeTree = tree.insert(book1).insert(book2);
  ABST<Book> expected =
      new Node<Book>(byTitle, book1, 
          new Leaf<Book>(byTitle), new Leaf<Book>(byTitle));
  
  // test the insert method
  boolean testInsert(Tester t) {
    return
        t.checkExpect(tree, expected);
  }
  
  // test comparators directly
  boolean testByTitle(Tester t) {
    return
        t.checkExpect(byTitle.compare(book2, book1), 1) &&
        t.checkExpect(byTitle.compare(book1, book2), -1) &&
        t.checkExpect(byTitle.compare(book1, book1), 0);
  }
  
  boolean testByAuthor(Tester t) {
    return
        t.checkExpect(byAuthor.compare(book1, book2), 1) &&
        t.checkExpect(byAuthor.compare(book2, book1), -1) &&
        t.checkExpect(byAuthor.compare(book2, book2), 0);
  }
  
  boolean testByPrice(Tester t) {
    return
        t.checkExpect(byPrice.compare(book2, book1), -1) &&
        t.checkExpect(byPrice.compare(book1, book2), 1) &&
        t.checkExpect(byPrice.compare(book1, book1), 0);
  }
  
  // test the present method
  boolean testPresent(Tester t) {
    return
        t.checkExpect(this.wholeTree.present(book1), true) &&
        t.checkExpect(this.wholeTree.present(book3), false);
  }
}


