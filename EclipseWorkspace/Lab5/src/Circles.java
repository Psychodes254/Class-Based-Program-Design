import tester.*;  
import javalib.worldimages.*;
import javalib.funworld.*;  
import javalib.worldcanvas.WorldCanvas;
import java.awt.Color; 

// to represent ILoCircle interface
interface ILoCircle{
  ILoCircle moveAll();
}

// to represent an empty list of circles
class MtList implements ILoCircle{
  MtList(){}
  
  public ILoCircle moveAll() {
    return new MtList();
  }
}

// to represent list of circles
class ConsList implements ILoCircle{
  Circle first;
  ILoCircle rest;
  
  ConsList(Circle first, ILoCircle rest){
    this.first = first;
    this.rest = rest;
  }
  
  public ILoCircle moveAll() {
    return new ConsList(this.first.move(), this.rest.moveAll());
  }
}

// to represent class Circle 
class Circle {
  int radius;
  OutlineMode fill;
  Color color;
  MyPosn position; // in pixels
  MyPosn velocity; // in pixels/tick
  
  Circle(int radius, OutlineMode fill, Color color, MyPosn position, MyPosn velocity){
    this.radius = radius;
    this.fill = fill;
    this.color = color;
    this.position = position;
    this.velocity = velocity;
  }
  
  // produce this circle after one tick
  Circle move(){
    return new Circle(this.radius, this.fill, this.color, this.position.add(velocity), this.velocity);
  }
}

class ExamplesCircles{
  ExamplesCircles(){}
  
  WorldCanvas c = new WorldCanvas(500, 500);
  WorldScene s = new WorldScene(500, 500);
  
  Circle circle1 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(100, 50), new MyPosn(10, 5));
  Circle circle2 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(45, 30), new MyPosn(15, 10));
  Circle circle3 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(180, 120), new MyPosn(-60, 10));
  
  Circle moveCircle1 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(110, 55), new MyPosn(10, 5));
  Circle moveCircle2 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(60, 40), new MyPosn(15, 10));
  Circle moveCircle3 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(120, 130), new MyPosn(-60, 10));
  
  ILoCircle mt = new MtList();
  
  ILoCircle circleList = new ConsList(circle1, new ConsList(circle2, new ConsList(circle3, mt)));
  ILoCircle movedCircleList = new ConsList(moveCircle1, new ConsList(moveCircle2, new ConsList(moveCircle3, mt)));
  
  boolean testMoveCircle(Tester t) {    
    return
        t.checkExpect(this.circle1.move(), circle2) &&
        t.checkExpect(this.circleList.moveAll(), movedCircleList);
  }
}