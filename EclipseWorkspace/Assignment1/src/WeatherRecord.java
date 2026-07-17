import tester.*;

// to represent a WeatherRecord class
class WeatherRecord {
  double high;
  double low;
  
  // the constructor
  WeatherRecord(double high, double low){
    this.high = high;
    this.low = low;
  }
  
  boolean withinRange() {
    return ((this.high >= 25 && this.high <= 28)
            && (this.low >= 14 && this.low <= 15));
  }
  
  boolean rainyDay() {
    return ((this.high >= 20 && this.high <= 24)
            && (this.low >= 10 && this.low <= 13));
  }
  
  boolean recordDay() {
    return (this.high > 50 && this.low < -60);
  }
}

class ExamplesWeatherRecord {

  // withinRange
  WeatherRecord wr1 = new WeatherRecord(25, 14);     
  WeatherRecord wr2 = new WeatherRecord(28, 15);    
  WeatherRecord wr3 = new WeatherRecord(26.5, 14.5);
  WeatherRecord wr4 = new WeatherRecord(24.9, 14); 
  WeatherRecord wr5 = new WeatherRecord(29, 16);      

  // record day
  WeatherRecord wr6 = new WeatherRecord(51, -61); 
  WeatherRecord wr7 = new WeatherRecord(50, -61); 
  WeatherRecord wr8 = new WeatherRecord(51, -60);  

  // rainy examples
  WeatherRecord wr9 = new WeatherRecord(22, 11);

  // test withinRange
  boolean testWithinRange(Tester t) {
    return t.checkExpect(wr1.withinRange(), true)
        && t.checkExpect(wr2.withinRange(), true)
        && t.checkExpect(wr3.withinRange(), true)
        && t.checkExpect(wr4.withinRange(), false)
        && t.checkExpect(wr5.withinRange(), false);
  }

  // test rainyDay
  boolean testRainyDay(Tester t) {
    return t.checkExpect(wr1.rainyDay(), false)
        && t.checkExpect(wr5.rainyDay(), false)
        && t.checkExpect(wr9.rainyDay(), true);
  }

  // test recordDay
  boolean testRecordDay(Tester t) {
    return t.checkExpect(wr6.recordDay(), true)
        && t.checkExpect(wr7.recordDay(), false)
        && t.checkExpect(wr8.recordDay(), false)
        && t.checkExpect(wr1.recordDay(), false);
  }
}