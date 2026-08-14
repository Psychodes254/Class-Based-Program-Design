import tester.*;  
import javalib.worldimages.*;
import javalib.funworld.*;  
import javalib.worldcanvas.WorldCanvas;
import java.awt.Color;   

class Rectangle{
  int base, height;
  OutlineMode mode;
  Color color;
  
  Rectangle(int base, int height, OutlineMode fill, Color color){
    this.base = base;
    this.height = height;
    this.mode = fill;
    this.color = color;
  }
  
  WorldImage draw() {
    return new RectangleImage(this.base, this.height, this.mode, this.color);
  }
}

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

class ExamplesImages{
  ExamplesImages(){}
  
  Rectangle rect1 = new Rectangle(300, 200, OutlineMode.SOLID, Color.GRAY);
  
  MyPosn p1 = new MyPosn(3, 5);
  MyPosn p2 = new MyPosn(10, 4);
  MyPosn p3 = new MyPosn(501, 501);
  MyPosn result = new MyPosn(13, 9);

  WorldCanvas c = new WorldCanvas(500, 500);
  WorldScene s = new WorldScene(500, 500);
  
  boolean testDrawTree(Tester t) {    
    return c.drawScene(s.placeImageXY(rect1.draw(), 250, 250))  && c.show();
  } 
  
  boolean testPosn(Tester t) {
    return 
        t.checkExpect(this.p1.add(p2), result);
  }
  
  boolean testOffscreen(Tester t) {
    return 
        t.checkExpect(this.result.isOffscreen(s), false) &&
        t.checkExpect(this.p3.isOffscreen(s), true);
  }
}

