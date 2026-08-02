import tester.*;

// to represent IShape interface
interface IShape{ 
  boolean sameShape(IShape other);
  boolean sameCircle(Circle other);
  boolean sameRectangle(Rectangle other);
  boolean sameSquare2(Square2 other);
  boolean sameTriangle(Triangle other);
}

// to represent abstract class AShape
abstract class AShape implements IShape{
  int x, y;
  
  AShape(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public boolean sameCircle(Circle other) {
    return false;
  }
  
  public boolean sameRectangle(Rectangle other) {
    return false;
  }
  
  public boolean sameSquare2(Square2 other) {
    return false;
  }
  
  public boolean sameTriangle(Triangle other) {
    return false;
  }
}

// to represent Circle class
class Circle extends AShape{
  int radius;
  
  Circle(int x, int y, int radius){
    super(x, y);
    this.radius = radius;
  }
  
  public boolean sameShape(IShape other) {
    return other.sameCircle(this);
  }
  
  public boolean sameCircle(Circle other) {
    if (this.x == other.x &&
        this.y == other.y &&
        this.radius == other.radius)
      return true;
    else
      return false;
  }
}

// to represent Rectangle class
class Rectangle extends AShape{
  int width;
  int length;
  
  Rectangle(int x, int y, int width, int length){
    super(x, y);
    this.width = width;
    this.length = length;
  }
  
  public boolean sameShape(IShape other) {
    return other.sameRectangle(this);
  }
  
  public boolean sameRectangle(Rectangle other) {
    if (this.x == other.x &&
        this.y == other.y &&
        this.width == other.width &&
        this.length == other.width)
      return true;
    else
      return false;
  }
}

// to represent Square class
class Square2 extends Rectangle{  
  Square2(int x, int y, int length){
    super(x, y, length, length);
  }
  
  public boolean sameShape(IShape other) {
    return other.sameSquare2(this);
  }
  
  public boolean sameSquare2(Square2 other) {
    if (this.x == other.x &&
        this.y == other.y &&
        this.length == other.length)
      return true;
    else
      return false;
  }
  
  public boolean sameRectangle(Rectangle other) {
    return false;
  }
}

// to represent Triangle class
class Triangle extends AShape{
  int base;
  int height;
  
  Triangle(int x, int y, int base, int height){
    super(x, y);
    this.base = base;
    this.height = height;
  }
  
  public boolean sameShape(IShape other) {
    return other.sameTriangle(this);
  }
  
  public boolean sameTriangle(Triangle other) {
    if (this.x == other.x &&
        this.y == other.y &&
        this.base == other.base &&
        this.height == other.height)
      return true;
    else
      return false;
  }
}

// to represent ExamplesShape class
class ExamplesShape{
  //In test method in an Examples class
  IShape c1 = new Circle(3, 4, 5);
  IShape c2 = new Circle(4, 5, 6);
  IShape c3 = new Circle(3, 4, 5);
  
  IShape r1 = new Rectangle(3, 4, 5, 5);
  IShape r2 = new Rectangle(4, 5, 6, 7);
  IShape r3 = new Rectangle(3, 4, 5, 5);
  
  IShape s1 = new Square2(3, 4, 5);
  IShape s2 = new Square2(4, 5, 6);
  IShape s3 = new Square2(3, 4, 5);
  
  IShape t1 = new Triangle(3, 4, 5, 6);
  IShape t2 = new Triangle(3, 4, 5, 6);
  
  boolean testerSameness(Tester t) {
    return
    t.checkExpect(c1.sameShape(c2), false) &&
    t.checkExpect(c2.sameShape(c1), false) &&
    t.checkExpect(c1.sameShape(c3), true) &&
    t.checkExpect(c3.sameShape(c1), true) &&
    
    t.checkExpect(r1.sameShape(r2), false) &&
    t.checkExpect(r2.sameShape(r1), false) &&
    t.checkExpect(r1.sameShape(r3), true) && 
    t.checkExpect(r3.sameShape(r1), true) &&
    
    t.checkExpect(s1.sameShape(s2), false) &&
    t.checkExpect(s2.sameShape(s1), false) &&
    t.checkExpect(s1.sameShape(s3), true) &&
    t.checkExpect(s3.sameShape(s1), true) &&
    
    t.checkExpect(s1.sameShape(r1), false) &&
    t.checkExpect(r1.sameShape(s1), false) &&
    
    t.checkExpect(t1.sameShape(c1), false) &&
    t.checkExpect(t1.sameShape(r1), false) &&
    t.checkExpect(t1.sameShape(s1), false) &&
    
    t.checkExpect(c1.sameShape(t1), false) &&
    t.checkExpect(r1.sameShape(t1), false) &&
    t.checkExpect(s1.sameShape(t1), false) &&
    
    t.checkExpect(t1.sameShape(t2), true) &&
    t.checkExpect(t2.sameShape(t1), true);
  }
}

