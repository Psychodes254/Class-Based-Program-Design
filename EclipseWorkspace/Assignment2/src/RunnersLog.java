import tester.*;

// to represent interface ILog
interface ILog { 
  double totalDist();
  ILog oneMonth(int month, int year);
  double monthDist(int month, int year);
  double bestDist();
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
  
  public double monthDist(int month, int year) {
    return this.oneMonth(month, year).totalDist();
  }
  
  public double bestDist() {
    if (this.first.distance > this.rest.bestDist())
      return this.first.distance;
    else
      return this.rest.bestDist();
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
  
  public double monthDist(int month, int year) {
      return 0;
    }
  
  public double bestDist() {
      return 0;
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
  Date d4 = new Date(15, 7, 2026);
  
  Entry e1 = new Entry(d1, 5.0, 25, "Good");
  Entry e2 = new Entry(d2, 3.0, 24, "Tired");
  Entry e3 = new Entry(d3, 26.0, 156, "Great");
  Entry e4 = new Entry(d4, 23.0, 131, "Awesome");
  
  ILog mt = new MtLog();
  ILog l1 = new ConsLog(e1,mt);
  ILog l2 = new ConsLog(e2,l1);
  ILog l3 = new ConsLog(e3,l2);
  ILog l4 = new ConsLog(e4, l3);
  
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
  
  // montDist method to output the total distance covered in a month
  boolean testMontDist(Tester t) {
    return 
        t.checkExpect(mt.monthDist(5, 2026), 0.0) &&
        t.checkExpect(l4.monthDist(7, 2026), 49.0);
  }
  
  // bestDist method to check all-time best distance
  boolean testBestDist(Tester t) {
    return 
        t.checkExpect(mt.bestDist(), 0.0) &&
        t.checkExpect(l4.bestDist(), 26.0);
  }
}