import javalib.worldimages.*;
import javalib.funworld.*;   

class MyPosn extends Posn {
  
  // standard constructor
  MyPosn(int x, int y) {
    super(x, y);
  }
 
  // constructor to convert from a Posn to a MyPosn
  MyPosn(Posn p) {
    this(p.x, p.y);
  }
  
  MyPosn add(Posn other) {
    return new MyPosn(this.x + other.x,
                      this.y + other.y);
  }
  
  boolean isOffscreen(WorldScene frame) {
    return (this.x > frame.width &&
            this.y > frame.height);
  }
}