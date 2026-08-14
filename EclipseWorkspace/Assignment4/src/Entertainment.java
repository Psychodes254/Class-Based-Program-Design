import tester.*;

interface IEntertainment {
    //compute the total price of this Entertainment
    double totalPrice();
    //computes the minutes of entertainment of this IEntertainment
    int duration();
    //produce a String that shows the name and price of this IEntertainment
    String format();
    //is this IEntertainment the same as that one?
    boolean sameEntertainment(IEntertainment that);
}

abstract class AEntertainment implements IEntertainment{
  String name;
  
  //represents price per issue in podcast and magazine, per episode in TvSeries
  double price; 
  
  //number of episodes in the series and Podcast, issues per year in magazine
  int installments; 
  
  AEntertainment(String name, double price, int installments){
    this.name = name;
    this.price = price;
    this.installments = installments;
  }
  
  public double totalPrice() {
    return this.price * this.installments;
  }

  public int duration() {
      return 50 * this.installments;
  }

  public String format() {
      return 
      this.name + ", sold for " + this.price + ", per episode. Total of " +  this.installments + " episodes.";
  }
  
  public boolean sameEntertainment(IEntertainment that) {
    return (this.getClass() == that.getClass());
  }
}

class Magazine extends AEntertainment {
    String genre;
    int pages;
    
    Magazine(String name, double price, int installments, String genre, int pages) {
      super(name, price, installments);
        this.genre = genre;
        this.pages = pages;
    }
    
    @Override
    public int duration() {
      return 5 * this.pages;
    }
    
    @Override
    public String format() {
      return
      this.name + ", sold for " + this.price + ", per page. Total of " +  this.pages + " pages.";
  }
}

class TVSeries extends AEntertainment {
    String corporation;
    
    TVSeries(String name, double price, int installments, String corporation) {
      super(name, price, installments);
        this.corporation = corporation;
    }
}

class Podcast extends AEntertainment {    
    Podcast(String name, double price, int installments) {
      super(name, price, installments);
    }
}

class ExamplesEntertainment {
    IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, 12, "Music", 60);
    IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
    IEntertainment serial = new Podcast("Serial", 0.0, 8);
    IEntertainment celebrityCorner = new Magazine("Celebrity Corner", 3.8, 24, "Entertaiment", 160);
    IEntertainment gameOfThrones = new TVSeries("Game of Thrones", 12.5, 73, "HBO");
    IEntertainment joeRogan = new Podcast("Joe Rogan", 5.0, 568);
    
    //testing total price method
    boolean testTotalPrice(Tester t) {
        return
           t.checkInexact(this.rollingStone.totalPrice(), 30.6, .0001) 
        && t.checkInexact(this.houseOfCards.totalPrice(), 68.25, .0001)
        && t.checkInexact(this.serial.totalPrice(), 0.0, .0001)
        && t.checkInexact(this.celebrityCorner.totalPrice(), 91.2, .0001) 
        && t.checkInexact(this.gameOfThrones.totalPrice(), 912.5, .0001)
        && t.checkInexact(this.joeRogan.totalPrice(), 2840.0, .0001);
    }
    
    //testing duration method
    boolean testTotalDuration(Tester t) {
      return
         t.checkExpect(this.rollingStone.duration(), 300) 
      && t.checkExpect(this.houseOfCards.duration(), 650)
      && t.checkExpect(this.serial.duration(), 400)
      && t.checkExpect(this.celebrityCorner.duration(), 800) 
      && t.checkExpect(this.gameOfThrones.duration(), 3650)
      && t.checkExpect(this.joeRogan.duration(), 28400);
  }
    
    //testing same entertainment method
    boolean testSameEntertainment(Tester t) {
      return
         t.checkExpect(this.rollingStone.sameEntertainment(celebrityCorner), true) 
      && t.checkExpect(this.houseOfCards.sameEntertainment(gameOfThrones), true)
      && t.checkExpect(this.serial.sameEntertainment(joeRogan), true)
      && t.checkExpect(this.celebrityCorner.sameEntertainment(joeRogan), false) 
      && t.checkExpect(this.gameOfThrones.sameEntertainment(rollingStone), false)
      && t.checkExpect(this.joeRogan.sameEntertainment(houseOfCards), false);
  }
    
    //testing string format method
    boolean testFormat(Tester t) {
      return
         t.checkExpect(this.rollingStone.format(),
         "Rolling Stone" + ", sold for " + 2.55 + ", per page. Total of " +  60 + " pages.") 
      && t.checkExpect(this.houseOfCards.format(),
         "House of Cards" + ", sold for " + 5.25 + ", per episode. Total of " +  13 + " episodes.")
      && t.checkExpect(this.serial.format(),
         "Serial" + ", sold for " + 0.0 + ", per episode. Total of " +  8 + " episodes.")
      && t.checkExpect(this.celebrityCorner.format(),
         "Celebrity Corner" + ", sold for " + 3.8 + ", per page. Total of " +  160 + " pages.") 
      && t.checkExpect(this.gameOfThrones.format(), 
         "Game of Thrones" + ", sold for " + 12.5 + ", per episode. Total of " +  73 + " episodes.")
      && t.checkExpect(this.joeRogan.format(),
         "Joe Rogan" + ", sold for " + 5.0 + ", per episode. Total of " +  568 + " episodes.");
  }
}