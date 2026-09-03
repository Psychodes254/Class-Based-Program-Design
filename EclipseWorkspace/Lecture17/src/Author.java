class Author{
    String first;
    String last;
    int yob;
    Book book;

    Author(String first, String last, int yob, Book book){
        this.first = first;
        this.last = last;
        this.yob = yob;
        this.book = book;
    }

    boolean sameAuthor(Author other){
        return this.first.equals(other.first) &&
               this.last.equals(other.last) &&
               this.yob == other.yob;
    }
    
    void updateBook(Book b) {
      this.book = b;
    }
}