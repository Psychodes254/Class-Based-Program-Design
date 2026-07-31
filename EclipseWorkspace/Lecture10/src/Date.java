import tester.*;

// to represent interface IDate
interface IDate{ }

// to represent class Date
class Date implements IDate{
  int year;
  int month;
  int day;
  
  Date(int year, int month, int day){
    this.year = (new Utils()).checkRange(year, 1500, 2100, "Invalid Year: " + Integer.toString(year));
    this.month = (new Utils()).checkRange(month, 1, 12, "Invalid month: " + Integer.toString(month));
    this.day = (new Utils()).checkRange(day, 1, 31, "Invalid day: " + Integer.toString(day));
  }
  
}

// to represent class Utils
class Utils{
  Utils(){}
  
  int checkRange(int value, int low, int high, String message) {
    if (value >= low && value <= high)
      return value;
    else
      throw new IllegalArgumentException(message);
  }
}

// to represent ExamplseDates class
class ExamplesDates{
  ExamplesDates(){}
  //Good dates
  Date d20100228 = new Date(2010, 2, 28);  
  Date d20091012 = new Date(2009, 10, 12);  
  
  boolean testBadYear(Tester t) {
    return t.checkConstructorException( new IllegalArgumentException("Invalid Year: -30"), "Date", -30, 2, 23) &&
           t.checkConstructorException( new IllegalArgumentException("Invalid month: -33"), "Date", 2010, -33, 23) &&
           t.checkConstructorException( new IllegalArgumentException("Invalid day: -35"), "Date", 2010, 2, -35) &&
           t.checkConstructorException( new IllegalArgumentException("Invalid Year: 2101"), "Date", 2101, 2, 23) &&
           t.checkConstructorException( new IllegalArgumentException("Invalid month: 13"), "Date", 2010, 13, 23) &&
           t.checkConstructorException( new IllegalArgumentException("Invalid day: 32"), "Date", 2010, 2, 32);
  }
}