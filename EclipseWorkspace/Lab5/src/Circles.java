import tester.*;  
import javalib.worldimages.*;
import javalib.funworld.*;  
import javalib.worldcanvas.WorldCanvas;
import java.awt.Color; 

// to represent ILoCircle interface
interface ILoCircle{
  ILoCircle moveAll();
  ILoCircle isOffscreen(WorldScene scene);
  ILoCircle removeOffscreen(WorldScene scene);
  WorldScene placeAll(WorldScene scene);
}

// to represent an empty list of circles
class MtList implements ILoCircle{
  MtList(){}
  
  public ILoCircle moveAll() {
    return new MtList();
  }
  
  public ILoCircle isOffscreen(WorldScene scene) {
    return new MtList();
  }
  
  public ILoCircle removeOffscreen(WorldScene scene) {
    return new MtList();
  }
  
  public WorldScene placeAll(WorldScene scene) {
    return scene;
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
  
  public ILoCircle isOffscreen(WorldScene scene) {
    if (this.first.isOffscreen(scene)) {
       return new ConsList(this.first, this.rest.isOffscreen(scene));
    }
    else {
      return this.rest.isOffscreen(scene);
    }
  }
  
  public ILoCircle removeOffscreen(WorldScene scene) {
    if (this.first.isOffscreen(scene) == false) {
      return new ConsList(this.first, this.rest.removeOffscreen(scene));
   }
   else {
     return this.rest.removeOffscreen(scene);
   }
  }
  
  public WorldScene placeAll(WorldScene scene) {
    WorldScene newScene = scene.placeImageXY(
        this.first.draw(), 
        this.first.position.x,
        this.first.position.y);
    return this.rest.placeAll(newScene);
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
  
  WorldImage draw() {
    return new CircleImage(this.radius, this.fill, this.color);
  }
  
  // produce this circle after one tick
  Circle move(){
    return new Circle(this.radius, this.fill, this.color, this.position.add(velocity), this.velocity);
  }
  
  boolean isOffscreen(WorldScene frame) {
    return (position.x > frame.width ||
            position.y > frame.height);
  }
}

class ExamplesCircles{
  ExamplesCircles(){}
  
  WorldCanvas c = new WorldCanvas(500, 500);
  WorldScene s = new WorldScene(500, 500);
  
  Circle circle1 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(100, 50), new MyPosn(10, 5));
  Circle circle2 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(45, 30), new MyPosn(15, 10));
  Circle circle3 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(180, 120), new MyPosn(-60, 10));
  Circle circle4 = new Circle(50, OutlineMode.OUTLINE, Color.BLUE, new MyPosn(450, 380), new MyPosn(100, 150));
  
  Circle moveCircle1 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(110, 55), new MyPosn(10, 5));
  Circle moveCircle2 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(60, 40), new MyPosn(15, 10));
  Circle moveCircle3 = new Circle(100, OutlineMode.SOLID, Color.BLACK, new MyPosn(120, 130), new MyPosn(-60, 10));
  Circle moveCircle4 = new Circle(50, OutlineMode.OUTLINE, Color.BLUE, new MyPosn(550, 530), new MyPosn(100, 150));
  
  ILoCircle mt = new MtList();
  
  ILoCircle circleList = new ConsList(circle1, new ConsList(circle2, new ConsList(circle3, new ConsList(circle4, mt))));
  ILoCircle movedCircleList = new ConsList(moveCircle1, new ConsList(moveCircle2, new ConsList(moveCircle3, new ConsList(moveCircle4, mt))));
  ILoCircle OffscreenCircleList = new ConsList(moveCircle4, mt);
  ILoCircle removedCircleList = new ConsList(moveCircle1, new ConsList(moveCircle2, new ConsList(moveCircle3, mt)));
  
  boolean testMoveCircle(Tester t) {    
    return
        t.checkExpect(this.circle1.move(), circle2) &&
        t.checkExpect(this.circleList.moveAll(), movedCircleList) &&
        t.checkExpect(this.circle4.move(), moveCircle4);
  }
  
  boolean testIsOffscreen(Tester t) {
    return
        t.checkExpect(this.circleList.isOffscreen(s), mt) &&
        t.checkExpect(this.movedCircleList.isOffscreen(s), OffscreenCircleList);
  }
  
  boolean testRemoveOffscreen(Tester t) {
    return 
        t.checkExpect(this.circleList.removeOffscreen(s), circleList) &&
        t.checkExpect(this.movedCircleList.removeOffscreen(s), removedCircleList);
  }
  
  boolean testDrawTree(Tester t) {    
    return c.drawScene(s.placeImageXY(circle1.draw(), 250, 250))  && c.show();
  }
  
  boolean testPlaceAll(Tester t) {
    return c.drawScene(circleList.placeAll(s)) && c.show();
  }
}



