import tester.*;

// to represent interface ILog
interface ILog { 
  double totalDist();
  ILog oneMonth(int month, int year);
}

// to represent ConsLog list
class ConsLog implements ILog{
  Entry first;
  ILog rest;
  
  // the constructor
  ConsLog(Entry first, ILog rest){
    this.first = first;
    this.rest = rest;
  }
  
  public double totalDist() {
    return this.first.distance + this.rest.totalDist();
  }
  
  public ILog oneMonth(int month, int year) {
    if (this.first.sameMonthAndYear(month, year))
      return
          new ConsLog(this.first, this.rest.oneMonth(month, year));
    else
      return
          this.rest.oneMonth(month, year);
  }
}

// to represent MtLog empty list 
class MtLog implements ILog{
  MtLog(){}
  
  public double totalDist() {
    return 0;
  }
  
  public ILog oneMonth(int month, int year) {
    return this;
  }
}

// to represent class Entry
class Entry{
  Date date;
  double distance; // miles
  int duration; // minutes
  String comment;
  
  // the constructor
  Entry(Date date, double distance, int duration, String comment){
    this.date = date;
    this.distance = distance;
    this.duration = duration;
    this.comment = comment;
  }
  
  //was this entry made in the given month and year?
  boolean sameMonthAndYear(int month, int year) {
    return this.date.sameMonthAndYear(month, year);
  }
}

// to represent class Date
class Date{
  int day;
  int month; 
  int year;
  
  // the constructor
  Date(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }
  
  //is this date in the given month and year?
  boolean sameMonthAndYear(int month, int year) {
    return (this.month == month && this.year == year);
  }
}

// to represent ExamplesRunersLog class
class ExamplesRunersLog { 
  Date d1 = new Date(5, 5, 2026);
  Date d2 = new Date(30, 6, 2026);
  Date d3 = new Date(20, 7, 2026);
  
  Entry e1 = new Entry(d1, 5.0, 25, "Good");
  Entry e2 = new Entry(d2, 3.0, 24, "Tired");
  Entry e3 = new Entry(d3, 26.0, 156, "Great");
  
  ILog mt = new MtLog();
  ILog l1 = new ConsLog(e1,mt);
  ILog l2 = new ConsLog(e2,l1);
  ILog l3 = new ConsLog(e3,l2);
  
  // totalDist method to check total distance in a list
  boolean testTotalDist(Tester t) {
    return
        t.checkExpect(mt.totalDist(), 0.0) &&
        t.checkExpect(l1.totalDist(), 5.0) &&
        t.checkExpect(l2.totalDist(), 8.0) &&
        t.checkExpect(l3.totalDist(), 34.0);
  }
  
 // oneMonth method to extract entries in a list for the given month and year
  boolean testOneMonth(Tester t) {
    return
        t.checkExpect(mt.oneMonth(5, 2026), mt) &&
        t.checkExpect(l1.oneMonth(5, 2026), l1) &&
        t.checkExpect(l2.oneMonth(5, 2026), new ConsLog(e1, mt)) &&
        t.checkExpect(l3.oneMonth(7, 2026), new ConsLog(e3,  mt));
  }
}