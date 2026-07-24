import tester.*;

// the end of a river
class Mouth{
  Location loc;
  IRiver river;
  int miles;
  
  Mouth(Location loc, IRiver river, int miles){
    this.loc = loc;
    this.river = river;
    this.miles = miles;
  }
  
  int sources() {
    return this.river.sources();
  }
  
  public boolean onRiver(Location aloc) {
    return this.loc.sameLoc(aloc) ||
           this.river.onRiver(aloc);
  }
  
  //the total length of the river system
  int length(){
  return this.miles + this.river.length();
  }
  
  boolean withinGivenRadius(Location aloc, int radius) {
    return this.loc.withinRadius(aloc, radius)
        || this.river.withinGivenRadius(aloc, radius);
  }
  
  int maxLength() {
    return this.miles + this.river.maxLength();
  }
  
  int count() {
    return this.river.count();
  }
  
  ILoLocation locations() {
    return new ConsLoLocation(this.loc, this.river.locations());
  }
}

// to represent ILoLocation interface
interface ILoLocation{ 
  ILoLocation append(ILoLocation other);
}

// to represent List of String
class ConsLoLocation  implements ILoLocation{
  Location first;
  ILoLocation rest;

  ConsLoLocation (Location first, ILoLocation rest){
   this.first = first;
   this.rest = rest;
  }
  
  public ILoLocation append(ILoLocation other) {
    return new ConsLoLocation (this.first, this.rest.append(other));
  }
}

// to represent an empty List
class MtLoLocation implements ILoLocation{
  MtLoLocation(){}
  
  public ILoLocation append(ILoLocation other) {
    return other;
  }
}

// a location on a river
class Location{
  int x;
  int y;
  String name;
  
  Location(int x, int y, String name){
    this.x = x;
    this.y = y;
    this.name = name;
  }
  
  boolean sameLoc(Location aloc) {
    return (this.x == aloc.x && 
           this.y == aloc.y);
  }
  
  boolean withinRadius(Location aloc, int radius) {
    int dx = this.x - aloc.x;
    int dy = this.y - aloc.y;

    return dx * dx + dy * dy <= radius * radius;
  }
}

// a river system
interface IRiver{ 
  int sources();
  
  boolean onRiver(Location aloc);
  
  int length();
  
  boolean withinGivenRadius(Location aloc, int radius);
  
  int maxLength();
  
  int count();
  
  ILoLocation locations();
}

// a source a river
class Sources implements IRiver{
  Location loc;
  int miles;
  
  Sources(Location loc, int miles){
    this.loc = loc;
    this.miles = miles;
  }
  
  public int sources() {
    return 1;
  }
  
  public boolean onRiver(Location aloc) {
    return this.loc.sameLoc(aloc);
  }
  
  public int length(){
    return this.miles;
    }
  
  public boolean withinGivenRadius(Location aloc, int radius) {
    return this.loc.withinRadius(aloc, radius);
  }
  
  public int maxLength() {
    return this.length();
  }
  
  public int count() {
    return 0;
  }
  
  public ILoLocation locations() {
    return new ConsLoLocation(this.loc, new MtLoLocation());
  }
}

// a confluence of two rivers
class Confluence implements IRiver{
  Location loc;
  IRiver left;
  int leftMiles;
  IRiver right;
  int rightMiles;
  
  Confluence(Location loc, IRiver left, IRiver right, int leftMiles, int rightMiles){
    this.loc = loc;
    this.left = left;
    this.right = right;
    this.leftMiles = leftMiles;
    this.rightMiles = rightMiles;
  }
  
  public int sources() {
    return this.left.sources() + this.right.sources();
  }
  
  public boolean onRiver(Location aloc) {
    return this.loc.sameLoc(aloc) ||
           this.left.onRiver(aloc) ||
           this.right.onRiver(aloc);
  }
  
  public int length(){
    return (this.leftMiles + this.rightMiles) +
           this.left.length() + 
           this.right.length();
    }
  
  public boolean withinGivenRadius(Location aloc, int radius) {
    return this.loc.withinRadius(aloc, radius) ||
           this.left.withinGivenRadius(aloc, radius) ||
           this.right.withinGivenRadius(aloc, radius);
  }
  
  public int maxLength() {
    int leftMax = this.leftMiles + this.left.maxLength();
    int rightMax = this.rightMiles + this.right.maxLength();

    if (leftMax > rightMax) {
      return this.leftMiles + leftMax;
    }
    else {
      return this.rightMiles + rightMax;
    }
  }
  
  public int count() {
    return 1 + ((this.left.count()) + (this.right.count()));
  }
  
  public ILoLocation locations() {
    return new ConsLoLocation(this.loc, 
        this.left.locations().append(this.right.locations()));
  }
}


class ExamplesRiver {
   // Locations
   Location sourceA = new Location(0, 10, "Source A");
   Location sourceB = new Location(10, 10, "Source B");
   Location sourceC = new Location(20, 10, "Source C");
  
   Location fork1 = new Location(5, 5, "Fork 1");
   Location fork2 = new Location(15, 3, "Fork 2");
  
   Location mouthLoc = new Location(10, 0, "Mouth");
  
   Location outside = new Location(50, 50, "Outside");
  
   // Sources
   IRiver riverA = new Sources(this.sourceA, 8);
   IRiver riverB = new Sources(this.sourceB, 6);
   IRiver riverC = new Sources(this.sourceC, 10);
  
   // Confluences
   IRiver upperRiver =
       new Confluence(this.fork1,
           this.riverA,
           this.riverB,
           4,
           6); 
  
   IRiver wholeRiver =
       new Confluence(this.fork2,
           this.upperRiver,
           this.riverC,
           5,  
           7); 
  
   // Mouth
   Mouth mouth =
       new Mouth(this.mouthLoc, this.wholeRiver, 3); 
   
   boolean testSameLoc(Tester t) {
     return t.checkExpect(
         this.sourceA.sameLoc(new Location(0, 10, "Another")),
         true)
         &&
  
         t.checkExpect(
             this.sourceA.sameLoc(this.sourceB),
             false)
         &&
  
         t.checkExpect(
             this.mouthLoc.sameLoc(this.mouthLoc),
             true);
   }
   
   boolean testSources(Tester t) {
     return t.checkExpect(this.riverA.sources(), 1)
         &&
  
         t.checkExpect(this.upperRiver.sources(), 2)
         &&
  
         t.checkExpect(this.wholeRiver.sources(), 3)
         &&
  
         t.checkExpect(this.mouth.sources(), 3);
   }

   boolean testLength(Tester t) {
     return t.checkExpect(this.riverA.length(), 8)
         &&
  
         t.checkExpect(this.upperRiver.length(), 24)
         &&
  
         t.checkExpect(this.wholeRiver.length(), 46)
         &&
  
         t.checkExpect(this.mouth.length(), 49);
   }
   
   boolean testOnRiver(Tester t) {
     return t.checkExpect(
         this.riverA.onRiver(this.sourceA),
         true) &&
         t.checkExpect(
             this.upperRiver.onRiver(this.sourceA),
             true) &&
         t.checkExpect(
             this.upperRiver.onRiver(this.sourceB),
             true) &&
         t.checkExpect(
             this.upperRiver.onRiver(this.fork1),
             true) &&
         t.checkExpect(
             this.upperRiver.onRiver(this.sourceC),
             false) &&
         t.checkExpect(
             this.wholeRiver.onRiver(this.sourceC),
             true) &&
         t.checkExpect(
             this.wholeRiver.onRiver(this.fork2),
             true) &&
         t.checkExpect(
             this.wholeRiver.onRiver(this.outside),
             false) &&
         t.checkExpect(
             this.mouth.onRiver(this.mouthLoc),
             true) &&
         t.checkExpect(
             this.mouth.onRiver(this.sourceA),
             true) &&
         t.checkExpect(
             this.mouth.onRiver(this.sourceB),
             true)  &&
         t.checkExpect(
             this.mouth.onRiver(this.sourceC),
             true) &&
         t.checkExpect(
             this.mouth.onRiver(this.outside),
             false);
   }
   
   boolean testWithinRadius(Tester t) {
     return
         t.checkExpect(
             this.sourceA.withinRadius(this.sourceA, 0), true)  &&
         t.checkExpect(
             this.sourceA.withinRadius(this.fork1, 8), true) &&
         t.checkExpect(
             this.sourceA.withinRadius(this.fork1, 5), false) &&
         t.checkExpect(
             this.sourceA.withinRadius(this.fork1, 4), false) &&
         t.checkExpect(
             this.sourceA.withinRadius(this.outside, 20), false);
   }
   
   boolean testSourceWithinGivenRadius(Tester t) {
     return
         t.checkExpect(
             this.riverA.withinGivenRadius(this.sourceA, 0), true) &&
         t.checkExpect(
             this.riverA.withinGivenRadius(this.fork1, 5), false) &&
         t.checkExpect(
             this.riverA.withinGivenRadius(this.outside, 20), false);
   }
   
   boolean testConfluenceWithinGivenRadius(Tester t) {
     return
         t.checkExpect(
             this.upperRiver.withinGivenRadius(this.fork1, 0), true) &&
         t.checkExpect(
             this.upperRiver.withinGivenRadius(this.sourceA, 0), true) &&
         t.checkExpect(
             this.upperRiver.withinGivenRadius(this.sourceB, 0), true) &&
         t.checkExpect(
             this.upperRiver.withinGivenRadius(this.sourceC, 0), false) &&
         t.checkExpect(
             this.wholeRiver.withinGivenRadius(this.sourceC, 0), true) &&
         t.checkExpect(
             this.wholeRiver.withinGivenRadius(this.fork2, 0), true) &&
         t.checkExpect(
             this.wholeRiver.withinGivenRadius(new Location(14, 3, "Nearby"), 1), true) &&
         t.checkExpect(
             this.wholeRiver.withinGivenRadius(this.outside, 10), false);
   }
   
   boolean testMouthWithinGivenRadius(Tester t) {
     return
         t.checkExpect(
             this.mouth.withinGivenRadius(this.mouthLoc, 0), true) &&
         t.checkExpect(
             this.mouth.withinGivenRadius(this.sourceA, 0), true) &&
         t.checkExpect(
             this.mouth.withinGivenRadius(this.sourceB, 0), true) &&
         t.checkExpect(
             this.mouth.withinGivenRadius(this.sourceC, 0), true) &&
         t.checkExpect(
             this.mouth.withinGivenRadius(this.outside, 15), false);
   }

   boolean testMaxLength(Tester t) {
     return
         t.checkExpect(this.riverA.maxLength(), 8) && 
         t.checkExpect(this.upperRiver.maxLength(), 18) && 
         t.checkExpect(this.wholeRiver.maxLength(), 28) && 
         t.checkExpect(this.mouth.maxLength(), 31);
   }
   
   boolean testCount(Tester t) {
     return 
         t.checkExpect(this.riverA.count(), 0) && 
         t.checkExpect(this.upperRiver.count(), 1) && 
         t.checkExpect(this.wholeRiver.count(), 2) && 
         t.checkExpect(this.mouth.count(), 2);
   }
   
  ILoLocation mt = new MtLoLocation();
  
  ILoLocation riverALocs =
    new ConsLoLocation(this.sourceA, this.mt);
  
  ILoLocation upperRiverLocs =
    new ConsLoLocation(this.fork1,
        new ConsLoLocation(this.sourceA,
            new ConsLoLocation(this.sourceB,
                this.mt)));
  
  ILoLocation wholeRiverLocs =
    new ConsLoLocation(this.fork2,
        new ConsLoLocation(this.fork1,
            new ConsLoLocation(this.sourceA,
                new ConsLoLocation(this.sourceB,
                    new ConsLoLocation(this.sourceC,
                        this.mt)))));
  
  ILoLocation mouthLocs =
    new ConsLoLocation(this.mouthLoc,
        this.wholeRiverLocs);
  
   boolean testLocations(Tester t) {
     return 
         t.checkExpect(this.riverA.locations(), this.riverALocs) &&
         t.checkExpect(this.upperRiver.locations(), this.upperRiverLocs) && 
         t.checkExpect(this.wholeRiver.locations(), this.wholeRiverLocs) &&
         t.checkExpect(this.mouth.locations(), this.mouthLocs);
   }
}