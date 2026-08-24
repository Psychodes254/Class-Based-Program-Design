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

    // test the same book mutation
    boolean testSameBook(Tester t){
        Author james = new Author("James", "Clear", 1986, null);
        Book atomic = new Book("Atomic Habits", 15, 1, james);
        james.book = atomic;
        return
        t.checkExpect(james.book.author, james);
    }
} 