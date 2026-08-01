import tester.*;

// to represent IShape interface
interface IShape{ 
  boolean sameCircle(Circle other);
  boolean sameRectangle(Rectangle other);
  boolean sameSquare2(Square2 other);
  boolean sameShape(IShape other);
  boolean isCircle();
  boolean isRectangle();
  boolean isSquare2();
}

// to represent Circle class
class Circle implements IShape{
  int x, y;
  int radius;
  
  Circle(int x, int y, int radius){
    this.x = x;
    this.y = y;
    this.radius = radius;
  }
  
  public boolean sameShape(IShape other) {
    if (other.isCircle())
      return this.sameCircle((Circle) other);
    else
      return false;
  }
  
  public boolean sameCircle(Circle other) {
    if (this.x == other.x &&
        this.y == other.y &&
        this.radius == other.radius)
      return true;
    else
      return false;
  }
  
  public boolean sameRectangle(Rectangle other) {
    return false;
  }
  
  public boolean sameSquare2(Square2 other) {
    return false;
  }
  
  public boolean isCircle() {
    return true;
  }
  public boolean isRectangle() {
    return false;
  }
  public boolean isSquare2() {
    return false;
  }
}

// to represent Rectangle class
class Rectangle implements IShape{
  int x, y;
  int width;
  int length;
  
  Rectangle(int x, int y, int width, int length){
    this.x = x;
    this.y = y;
    this.width = width;
    this.length = length;
  }
  
  public boolean sameShape(IShape other) {
    if (other.isRectangle())
      return this.sameRectangle((Rectangle) other);
    else
      return false;
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
  
  public boolean sameCircle(Circle other) {
    return false;
  }
  
  public boolean sameSquare2(Square2 other) {
    return false;
  }
  
  public boolean isCircle() {
    return false;
  }
  
  public boolean isRectangle() {
    return true;
  }
  
  public boolean isSquare2() {
    return false;
  }
}

// to represent Square class
class Square2 extends Rectangle{  
  Square2(int x, int y, int length){
    super(x, y, length, length);
  }
  
  public boolean sameShape(IShape other) {
    if (other.isSquare2())
      return this.sameSquare2((Square2) other);
    else
      return false;
  }
  
  public boolean sameSquare2(Square2 other) {
    if (this.x == other.x &&
        this.y == other.y &&
        this.length == other.length)
      return true;
    else
      return false;
  }
  
  public boolean sameCircle(Circle other) {
    return false;
  }
  
  public boolean sameRectangle(Rectangle other) {
    return false;
  }
  
  public boolean isCircle() {
    return false;
  }
  
  public boolean isRectangle() {
    return false;
  }
  
  public boolean isSquare2() {
    return true;
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
    t.checkExpect(r1.sameShape(s1), false);
  }
}

