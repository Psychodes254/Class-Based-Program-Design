import tester.*;

abstract class ABST<T>{
  Comparator<T> order;
  
  ABST(Comparator<T> order){
    this.order = order;
  }
  
  abstract ABST<T> insert(T data);
  abstract boolean present(T data);
  abstract T getLeftmost();
  abstract T getRighttmost();
  abstract T getLeftmostHelper(T best);
  abstract T getRighttmostHelper(T best);
  abstract boolean sameTree(ABST<T> other);
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

  public T getLeftmost(){
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  public T getRighttmost(){
    throw new RuntimeException("No right of an empty tree");
  }

  public T getLeftmostHelper(T best){
    return best;
  }

  public T getRighttmostHelper(T best){
    return best;
  }

  public boolean sameTree(ABST<T> other){
    return other instanceof Leaf<?>;
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
      return new Node<T>(this.order, this.data, this.left.insert(data), this.right);
    }
    else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(data));
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

  public T getLeftmost(){
    return this.left.getLeftmostHelper(this.data);
  }

  public T getRighttmost(){
    return this.right.getRighttmostHelper(this.data);

  }

  public T getLeftmostHelper(T best){
    return this.left.getLeftmostHelper(this.data);
  }

  public T getRighttmostHelper(T best){
    return this.right.getRighttmostHelper(this.data);
  }

  public boolean sameTree(ABST<T> other){
    if (!(other instanceof Node<?>)){
      return false;
    }
    Node<T> otherNode = (Node<T>) other;
    return
      (this.order.compare(this.data, otherNode.data) == 0) &&
      this.left.sameTree(otherNode.left) &&
      this.right.sameTree(otherNode.right);
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
  ABST<Book> tree2 = new Leaf<Book>(byAuthor).insert(book1);
  ABST<Book> tree3 = new Leaf<Book>(byPrice).insert(book1);
  ABST<Book> secondTree = tree.insert(book1).insert(book2);
  ABST<Book> titleTree = tree.insert(book1).insert(book2).insert(book3);
  ABST<Book> authorTree = tree2.insert(book1).insert(book2).insert(book3);
  ABST<Book> priceTree = tree3.insert(book1).insert(book2).insert(book3);

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
        t.checkExpect(this.secondTree.present(book1), true) &&
        t.checkExpect(this.secondTree.present(book3), false);
  }

  // test the Leftmost and Rightmost methods of BST
  boolean testLeftmostRightmost(Tester t){
    return 
    t.checkExpect(this.titleTree.getLeftmost().title, "Cujo") &&
    t.checkExpect(this.titleTree.getRighttmost().title, "Emma") &&
    t.checkExpect(this.authorTree.getLeftmost().author, "Austen") &&
    t.checkExpect(this.authorTree.getRighttmost().author, "King") &&
    t.checkExpect(this.priceTree.getLeftmost().price, 15) &&
    t.checkExpect(this.priceTree.getRighttmost().price, 50);
  }

  // test the same BST method
  boolean testSameTree(Tester t){
    return
    t.checkExpect(this.titleTree.sameTree(titleTree), true) &&
    t.checkExpect(this.authorTree.sameTree(priceTree), false);
  }
}



