import tester.*;

// to represent IShape interface
interface IShape{
  <R> R accept(IVisitorShape<R> visitor);
}

// to represent Circle class
class Circle implements IShape{
  int x, y, radius;
  String color;
  
  Circle(int x, int y, int radius, String color){
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
  }
  
  public <R> R accept(IVisitorShape<R> visitor) {
    return visitor.visitCircle(this);
  }
}

// to represent Rectangle class
class Rectangle implements IShape{
  int x, y, base, height;
  String color;
  
  Rectangle(int x, int y, int base, int height, String color){
    this.x = x;
    this.y = y;
    this.base = base;
    this.height = height;
    this.color = color;
  }
  
  public <R> R accept(IVisitorShape<R> visitor) {
    return visitor.visitRectangle(this);
  }
}

// to represent Triangle class
class Triangle implements IShape{
  int x, y, base, height;
  String color;
  
  Triangle(int x, int y, int base, int height, String color){
    this.x = x;
    this.y = y;
    this.base = base;
    this.height = height;
    this.color = color;
  }
  
  public <R> R accept(IVisitorShape<R> visitor) {
    return visitor.visitTriangle(this);
  }
}

// to represent Square class
class Square implements IShape{
  int x, y, size;
  String color;
  
  Square(int x, int y, int size, String color){
    this.x = x;
    this.y = y;
    this.size = size;
    this.color = color;
  }
  
  public <R> R accept(IVisitorShape<R> visitor) {
    return visitor.visitSquare(this);
  }
}

// to represent ExamplesShapes class
class ExamplesShapes{
  ExamplesShapes(){}
  
  IShape circle1 = new Circle(3, 4, 2, "red");
  IShape circle2 = new Circle(4, 5, 6, "blue");
  IShape circle3 = new Circle(7, 4, 1, "white");
  
  IShape rect1 = new Rectangle(2, 4, 5, 6, "purple");
  IShape rect2 = new Rectangle(6, 5, 2, 4, "green");
  IShape rect3 = new Rectangle(9, 7, 4, 2, "maroon");
  
  IShape triangle1 = new Triangle(1, 2, 4, 6, "orange");
  IShape triangle2 = new Triangle(3, 5, 8, 6, "pink");
  IShape triangle3 = new Triangle(2, 1, 5, 6, "yellow");
  
  IShape square1 = new Square(5, 7, 4, "grey");
  IShape square2 = new Square(3, 2, 5, "black");
  IShape square3 = new Square(1, 4, 6, "magenta");

  IList<IShape> circle = new ConsList<IShape>(circle1, new ConsList<IShape>(circle2, new ConsList<IShape>(circle3, new MtList<IShape>())));
    
  IList<IShape> rectangle = new ConsList<IShape>(rect1, new ConsList<IShape>(rect2, new ConsList<IShape>(rect3, new MtList<IShape>())));
    
  IList<IShape> triangle = new ConsList<IShape>(triangle1, new ConsList<IShape>(triangle2, new ConsList<IShape>(triangle3, new MtList<IShape>())));
    
  IList<IShape> square = new ConsList<IShape>(square1, new ConsList<IShape>(square2, new ConsList<IShape>(square3, new MtList<IShape>())));
    
  IList<Double> circlesArea = new ConsList<Double>(12.56, new ConsList<Double>(113.1, new ConsList<Double>(3.14, new MtList<Double>())));
  IList<Double> rectangleArea = new ConsList<Double>(30.0, new ConsList<Double>(8.0, new ConsList<Double>(8.0, new MtList<Double>())));
  IList<Double> triangleArea = new ConsList<Double>(12.0, new ConsList<Double>(24.0, new ConsList<Double>(15.0, new MtList<Double>())));
  IList<Double> squareArea = new ConsList<Double>(16.0, new ConsList<Double>(25.0, new ConsList<Double>(36.0, new MtList<Double>())));
  
  IList<String> circlesColors = new ConsList<String>("red", new ConsList<String>("blue", new ConsList<String>("white", new MtList<String>())));
  IList<String> rectangleColors = new ConsList<String>("purple", new ConsList<String>("green", new ConsList<String>("maroon", new MtList<String>())));
  IList<String> triangleColors = new ConsList<String>("orange", new ConsList<String>("pink", new ConsList<String>("yellow", new MtList<String>())));
  IList<String> squareColors = new ConsList<String>("grey", new ConsList<String>("black", new ConsList<String>("magenta", new MtList<String>())));
  
  // test the visitor method from all shapes
  boolean testVisitorArea(Tester t) {
    return 
        t.checkInexact(this.circle1.accept(new VisitShapeArea()), 12.57, 0.01) &&
        t.checkInexact(this.rect2.accept(new VisitShapeArea()), 8.0, 0.01) &&
        t.checkInexact(this.triangle3.accept(new VisitShapeArea()), 15.0, 0.01) &&
        t.checkInexact(this.square2.accept(new VisitShapeArea()), 25.0, 0.01);
  }
    
  // test the area methods of the shapes
  boolean testMapArea(Tester t) {
    return
        t.checkInexact(this.circle.map(new VisitShapeArea()), circlesArea, 0.01) &&
        t.checkInexact(this.rectangle.map(new VisitShapeArea()), rectangleArea, 0.01) &&
        t.checkInexact(this.triangle.map(new VisitShapeArea()), triangleArea, 0.01) &&
        t.checkInexact(this.square.map(new VisitShapeArea()), squareArea, 0.01);
  }
  
  // test the color methods of the shapes
  boolean testMapColor(Tester t) {
    return 
        t.checkExpect(this.circle.map(new VisitShapeColor()), circlesColors) &&
        t.checkExpect(this.rectangle.map(new VisitShapeColor()), rectangleColors) &&
        t.checkExpect(this.triangle.map(new VisitShapeColor()), triangleColors) &&
        t.checkExpect(this.square.map(new VisitShapeColor()), squareColors);
  }
  
  // test the find methods for specific shapes
  boolean testFindColor(Tester t) {
    return 
        t.checkExpect(this.circle.find(new VisitShapeFind()), new ConsList<IShape>(circle2, new MtList<IShape>())) &&
        t.checkExpect(this.rectangle.find(new VisitShapeFind()), new ConsList<IShape>(rect3, new MtList<IShape>())) &&
        t.checkExpect(this.triangle.find(new VisitShapeFind()), new ConsList<IShape>(triangle1, new MtList<IShape>())) &&
        t.checkExpect(this.square.find(new VisitShapeFind()), new ConsList<IShape>(square3, new MtList<IShape>()));
  }
}


