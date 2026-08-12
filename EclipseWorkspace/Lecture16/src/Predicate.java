// to represent IPredicate interface
interface IPredicate<T>{
  boolean apply(T t);
}

// to represent IFunction interface
interface IFunction<A, R>{
  R apply(A arg);
}

// to represent IFunction2 interface
interface IFunction2<A1, A2, R>{
  R apply(A1 arg1, A2 arg2);
}

// to represent IVisitorShape interface
interface IVisitorShape<R>{
  R visitCircle(Circle circle);
  R visitRectangle(Rectangle rect);
  R visitTriangle(Triangle triangle);
  R visitSquare(Square square);
}

// to represent class VisitShapeArea for getting area of the shapes
class VisitShapeArea implements IVisitorShape<Double>, IFunction<IShape, Double>{
  public Double apply(IShape shape) {
    return shape.accept(this);
  }
  
  public Double visitCircle(Circle circle) {
    return Math.PI * circle.radius * circle.radius;
  }
  
  public Double visitRectangle(Rectangle rect) {
    return rect.base * rect.height * 1.0;
  }
  
  public Double visitTriangle(Triangle triangle) {
    return triangle.base * triangle.height * 0.5;
  }
  
  public Double visitSquare(Square square) {
    return square.size * square.size * 1.0;
  }
}

//to represent class VisitShapeArea for getting area of the shapes
class VisitShapeColor implements IVisitorShape<String>, IFunction<IShape, String>{
  public String apply(IShape shape) {
   return shape.accept(this);
  }
  
  public String visitCircle(Circle circle) {
   return circle.color;
  }
  
  public String visitRectangle(Rectangle rect) {
   return rect.color;
  }
  
  public String visitTriangle(Triangle triangle) {
   return triangle.color;
  }
  
  public String visitSquare(Square square) {
   return square.color;
  }
}

//to represent class VisitShapeArea for getting area of the shapes
class VisitShapeFind implements IVisitorShape<Boolean>, IPredicate<IShape>{
  public boolean apply(IShape shape) {
   return shape.accept(this);
  }
  
  public Boolean visitCircle(Circle circle) {
   return circle.color.equals("blue");
  }
  
  public Boolean visitRectangle(Rectangle rect) {
   return rect.color.equals("maroon");
  }
  
  public Boolean visitTriangle(Triangle triangle) {
   return triangle.color.equals("orange");
  }
  
  public Boolean visitSquare(Square square) {
   return square.color.equals("magenta");
  }
}



