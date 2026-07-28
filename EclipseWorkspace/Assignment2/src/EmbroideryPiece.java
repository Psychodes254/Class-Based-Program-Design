import tester.*;

/*
+-----------------+
| EmbroideryPiece |
+-----------------+
| String name     |
| IMotif motif    |--+
+-----------------+  |
                     |
                 +--------+
                 | IMotif |
                 +--------+
                     |
          +------------------------------------------------+
          |                       |                        |
+--------------------+  +--------------------+    +--------------------+
| CrossStitchMotif   |  | ChainStitchMotif   |    | GroupMotif         |
+--------------------+  +--------------------+    +--------------------+
| String description |  | String description |    | String description |
| double difficulty  |  | double difficulty  |    | ILoMotif motifs    |
+--------------------+  +--------------------+    +--------------------+
                                                           |
                                +--------------------------+
                                |
                           +----------+     
                           | ILoMotif |
                           +----------+
                                 |
                            +--------------+     
                            |              |
                    +-----------+    +---------------+             
                    | MtLoMotif |    | ConsLoMotif   |
                    +-----------+    +---------------+
                    |           |    | IMotif first  |
                    |           |    | ILoMotif rest |
                    +-----------+    +---------------+
 */

// to represent EmbroideryPiece class
class EmbroideryPiece{
  String name;
  IMotif motif;
  
  // the constructor
  EmbroideryPiece(String name, IMotif motif){
    this.name = name;
    this.motif = motif;
  }
  
  double averageDifficulty() {
    return this.motif.averageDifficulty();
  }
  
  String embroideryInfo() {
    return this.name + ": " + this.motif.embroideryInfo() + ".";
  }
}

// to represent IMotif interface
interface IMotif {
  double sumDifficulty();
  int count();
  double averageDifficulty();
  String embroideryInfo();
}

// to represent CrossStitch class 
class CrossStitchMotif implements IMotif{
  String description;
  double difficulty;
  
  // the constructor
  CrossStitchMotif(String description, double difficulty){
    this.description = description;
    this.difficulty = difficulty;
  }
  
  public double averageDifficulty() {
    return this.sumDifficulty() / count();
  }
  
  public String embroideryInfo() {
    return this.description + " (cross stitch)";
  }
  
  public double sumDifficulty() {
    return this.difficulty;
  }
  
  public int count() {
    return 1;
  }
}

// to represent ChainStitch class
class ChainStitchMotif implements IMotif{
  String description;
  double difficulty;
  
  // the constructor
  ChainStitchMotif(String description, double difficulty){
    this.description = description;
    this.difficulty = difficulty;
  }
  
  public double averageDifficulty() {
    return this.sumDifficulty() / count();
  }
  
  public String embroideryInfo() {
    return this.description + " (chain stitch)";
  }
  
  public double sumDifficulty() {
    return this.difficulty;
  }
  
  public int count() {
    return 1;
  }
}

// to represent GroupMotif class
class GroupMotif implements IMotif{
  String description;
  ILoMotif motifs;
  
  // the constructor
  GroupMotif(String description, ILoMotif motifs){
    this.description = description;
    this.motifs = motifs;
  }
  
  public double averageDifficulty() {
    return this.motifs.averageDifficulty();
  }
  
  public String embroideryInfo() {
    return this.motifs.embroideryInfo();
  }
  
  public double sumDifficulty() {
    return this.motifs.sumDifficulty();
  }
  
  public int count() {
    return this.motifs.count();
  }
}

// to represent ILoMotif interface
interface ILoMotif{
  double averageDifficulty();
  double sumDifficulty();
  int count();
  String embroideryInfo();
  String embroideryInfoRest();
}

// to represent a list of ILoMotif
class ConsLoMotif implements ILoMotif{
  IMotif first;
  ILoMotif rest;
  
  // the constructor
  ConsLoMotif(IMotif first, ILoMotif rest){
    this.first = first;
    this.rest = rest;
  }
  
  public double averageDifficulty() {
    return this.sumDifficulty() / this.count();
  }
  
  public double sumDifficulty() {
    return first.sumDifficulty() + this.rest.sumDifficulty();
  }
  
  public int count() {
    return first.count() + rest.count();
  }
  
  public String embroideryInfo() {
    return this.first.embroideryInfo() + this.rest.embroideryInfoRest();
  }
  
  public String embroideryInfoRest() {
    return ", " + this.first.embroideryInfo() + this.rest.embroideryInfoRest();
  }
}

// to represent an empty list ILoMotif
class MtLoMotif implements ILoMotif{
  MtLoMotif() {}
  
  public double averageDifficulty() {
    return 0.0;
  }
  
  public int count() {
    return 0;
  }
  
  public double sumDifficulty() {
    return 0.0;
  }
  
  public String embroideryInfo() {
    return "";
  }
  
  public String embroideryInfoRest() {
    return "";
  }
}

// to represent tests and examples for ExamplesEmbroidery class
class ExamplesEmbroidery{
  IMotif bird = new CrossStitchMotif("bird", 4.5);
  IMotif tree = new ChainStitchMotif("tree", 3.0);
  IMotif rose = new CrossStitchMotif("rose", 5.0);
  IMotif poppy = new ChainStitchMotif("poppy", 4.75);
  IMotif daisy = new CrossStitchMotif("daisy", 3.2);
  
  ILoMotif empty = new MtLoMotif();
  
  ILoMotif lo1 = new ConsLoMotif(rose, new ConsLoMotif(poppy, new ConsLoMotif(daisy, empty)));
  
  IMotif flowers = new GroupMotif("flowers", lo1);
  

  ILoMotif lo2 = new ConsLoMotif(bird, new ConsLoMotif(tree, new ConsLoMotif(flowers, empty)));
  
  IMotif nature = new GroupMotif("nature", lo2);
  
  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", nature);
  
  // test the averageDifficulty method
  boolean testAverageDifficulty(Tester t) {
    return
    t.checkInexact(empty.averageDifficulty(), 0.0, 0.1) &&
    t.checkInexact(lo1.averageDifficulty(), 4.3, 0.1) &&
    t.checkInexact(lo2.averageDifficulty(), 4.09, 0.1);
  }
  
  //test the embroideryInfo method
   boolean testEmbroideryInfo(Tester t) {
     return 
     t.checkExpect(empty.embroideryInfo(), "") &&
     t.checkExpect(pillowCover.embroideryInfo(), 
     "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
 }
}
