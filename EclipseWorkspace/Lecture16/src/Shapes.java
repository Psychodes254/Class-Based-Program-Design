// to represent IShape interface
interface IShape{}

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
  IShape triangle3 = new Triangle(2, 1, 4, 6, "yellow");
  
  IShape square1 = new Square(5, 7, 4, "grey");
  IShape square2 = new Square(3, 2, 5, "black");
  IShape square3 = new Square(1, 4, 6, "magenta");
}


