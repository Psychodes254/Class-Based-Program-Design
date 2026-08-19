interface Comparator<T>{
  int compare(T t1, T t2);
}

class BooksByTitle implements Comparator<Book>{
  public int compare(Book b1, Book b2) {
    int result = b1.title.compareTo(b2.title);
    
    if (result < 0) {
      return -1;
    }
    else if (result > 0) {
      return 1;
    }
    else {
      return 0;
    }
  }
}

class BooksByAuthor implements Comparator<Book>{
  public int compare(Book b1, Book b2) {
    int result = b1.author.compareTo(b2.author);
    
    if (result < 0) {
      return -1;
    }
    else if (result > 0) {
      return 1;
    }
    else {
      return 0;
    }
  }
}

class BooksByPrice  implements Comparator<Book>{
public int compare(Book b1, Book b2) {
    if (b1.price < b2.price) {
      return -1;
    }
    else if (b1.price > b2.price){
      return 1;
    }
    else {
      return 0;
    }
  }
}