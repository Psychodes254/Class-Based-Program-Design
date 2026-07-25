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
}

// to represent IMotif interface
interface IMotif { }

// to represent CrossStitch class 
class CrossStitchMotif implements IMotif{
  String description;
  double difficulty;
  
  // the constructor
  CrossStitchMotif(String description, double difficulty){
    this.description = description;
    this.difficulty = difficulty;
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
}

// to represent ILoMotif interface
interface ILoMotif{ }

// to represent a list of ILoMotif
class ConsLoMotif implements ILoMotif{
  IMotif first;
  ILoMotif rest;
  
  // the constructor
  ConsLoMotif(IMotif first, ILoMotif rest){
    this.first = first;
    this.rest = rest;
  }
}

// to represent an empty list ILoMotif
class MtLoMotif implements ILoMotif{
  MtLoMotif() {}
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
}
