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
}

// to represent Runner class
class Runner{
    String name;
    int age;
    int bib;
    boolean isMale;
    int position;
    int time;

    Runner(String name, int age, int bib, boolean isMale, int position, int time){
        this.name = name;
        this.age = age;
        this.bib = bib;
        this.isMale = isMale;
        this.position = position;
        this.time = time;
    }
}