import tester.*;

class Book{
    String title;
    int price;
    int quantity;
    Author author;

    Book(String title, int price, int quantity, Author author){
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.author = author;
    }

    boolean sameBook(Book other){
        return
        this.title.equals(other.title) &&
        this.price == other.price &&
        this.quantity == other.quantity &&
        this.author == other.author;
    }
}

class ExamplesBooks{
    ExamplesBooks(){}

    Author james;
    Author jkRowling;
    Book atomic;
    Book hp1;
    
    void initialConditions() {
      this.james = new Author("James", "Clear", 1986, null);
      this.jkRowling = new Author("Jk", "Rowling", 1965, null);
      this.atomic = new Book("Atomic Habits", 15, 1, james);
      this.hp1 = new Book("Harry Potter", 15, 1, jkRowling);
    }

    void testSameBook(Tester t){
      initialConditions();
      james.updateBook(atomic);
      t.checkExpect(this.james.book, atomic);
      t.checkExpect(james.book.author, james);
    }
    
    void testSameBook2(Tester t){
      initialConditions();
      jkRowling.updateBook(hp1);
      t.checkExpect(this.jkRowling.book, hp1);
      t.checkExpect(jkRowling.book.author, jkRowling);
  }
} 