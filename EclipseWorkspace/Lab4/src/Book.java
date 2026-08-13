import tester.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
// to represent IBook interface
interface IBook{
  // to produce the number of days the book is overdue
  long daysOverdue();
  
  // informs whether the book is overdue on the given day
  boolean isOverdue();
  
  // computes the fine for the book, if the book is returned on the given day
  boolean computeFine();
}

// to represent an abstract class ABook 
abstract class ABook implements IBook{
  String title;
  LocalDate dateTaken;
  LocalDate startDate = LocalDate.of(2001, 1, 1);
  
  ABook(String title, LocalDate dateTaken){
    this.title = title;
    this.dateTaken = dateTaken;
  }
  
  public long daysOverdue() {
    return (ChronoUnit.DAYS.between(startDate, LocalDate.now())) - 
           (ChronoUnit.DAYS.between(startDate, dateTaken));
  }

  public boolean isOverdue() {
    if (this.daysOverdue() > 14) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean computeFine() {
    return !isOverdue();
  }
}

// to represent ReferenceBook class
class ReferenceBook extends ABook{
  ReferenceBook(String title, LocalDate dateTaken){
    super(title, dateTaken);
  }
  
  @Override
  public boolean isOverdue() {
    if (this.daysOverdue() > 2) {
      return true;
    }
    else {
      return false;
    }
  }
}

// to represent AudioBook class
class AudioBook extends ABook{
  String author;
  
  AudioBook(String author, String title, LocalDate dateTaken){
    super(title, dateTaken);
    this.author = author;
  }
}

// to represent RegularBook class
class RegularBook extends ABook{
  String author;
  
  RegularBook(String author, String title, LocalDate dateTaken){
    super(title, dateTaken);
    this.author = author;
  }
}

class ExamplesBooks{
  ExamplesBooks(){}
  
  IBook refBook1 = new ReferenceBook("Animal Kingdom", LocalDate.of(2026, 8, 12));
  IBook refBook2 = new ReferenceBook("Hobbit", LocalDate.of(2026, 8, 1));
  
  IBook audBook1 = new AudioBook("James Clear", "Atomic Habits", LocalDate.of(2026, 7, 29));
  IBook audBook2 = new AudioBook("David Goggins", "Can't Hurt Me", LocalDate.of(2026, 8, 5));
  
  IBook regBook1 = new RegularBook("Gorge Orwel", "Animal Farm", LocalDate.of(2026, 8, 10));
  IBook regBook2 = new RegularBook("Gorge Orwel", "1984", LocalDate.of(2026, 7, 15));
  
  // test method daysOverdue
  boolean testDaysOverdue(Tester t) {
    return 
        t.checkExpect(refBook1.daysOverdue(), 1L) &&
        t.checkExpect(refBook2.daysOverdue(), 12L) &&
        t.checkExpect(audBook1.daysOverdue(), 15L) &&
        t.checkExpect(audBook2.daysOverdue(), 8L) &&
        t.checkExpect(regBook1.daysOverdue(), 3L) &&
        t.checkExpect(regBook2.daysOverdue(), 29L);
      }
  
  // test method isOverdue
  boolean testIsOverdue(Tester t) {
    return 
        t.checkExpect(refBook1.isOverdue(), false) &&
        t.checkExpect(refBook2.isOverdue(), true) &&
        t.checkExpect(audBook1.isOverdue(), true) &&
        t.checkExpect(audBook2.isOverdue(), false) &&
        t.checkExpect(regBook1.isOverdue(), false) &&
        t.checkExpect(regBook2.isOverdue(), true);
  }
  
  // test method computeFine
  boolean testComputeFine(Tester t) {
    return 
        t.checkExpect(refBook1.computeFine(), true) &&
        t.checkExpect(refBook2.computeFine(), false) &&
        t.checkExpect(audBook1.computeFine(), false) &&
        t.checkExpect(audBook2.computeFine(), true) &&
        t.checkExpect(regBook1.computeFine(), true) &&
        t.checkExpect(regBook2.computeFine(), false);
  }      
}


