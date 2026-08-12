import tester.*;

// to represent IList interface
interface IList<T>{
  IList<T> find(IPredicate<T> predicate);
  <U> IList<U> map(IFunction<T, U> function);
  <U> U foldr(IFunction2<T, U, U> function, U initialValue);
}

// to represent MtList class
class MtList<T> implements IList<T>{
  public <U> IList<U> map(IFunction<T, U> function){
    return new MtList<U>();
  }
  
  public IList<T> find(IPredicate<T> predicate){
    return this;
  }
  
  public <U> U foldr(IFunction2<T, U, U>function, U initialValue) {
    return initialValue;
  }
}

// to represent ConsList class 
class ConsList<T> implements IList<T>{
  T first;
  IList<T> rest;
  
  ConsList(T first, IList<T> rest){
    this.first = first;
    this.rest = rest;
  }
  
  public <U> IList<U> map(IFunction<T, U> function){
    return new ConsList<U>(function.apply(this.first), this.rest.map(function));
  }
  
  public IList<T> find(IPredicate<T> predicate){
    if (predicate.apply(this.first)) {
      return new ConsList<T>(this.first, this.rest.find(predicate));
    }
    else {
      return this.rest.find(predicate);
    }
  }
  
  public <U> U foldr(IFunction2<T, U, U>function, U initialValue) {
    return function.apply(this.first, this.rest.foldr(function, initialValue));
  }
}

// to represent ExamplesIList class 
class ExamplesIList{
  Circle circle1 = new Circle(3, 4, 2, "red");
  Circle circle2 = new Circle(4, 5, 6, "blue");
  Circle circle3 = new Circle(7, 4, 1, "white");
  
  Rectangle rect1 = new Rectangle(2, 4, 5, 6, "purple");
  Rectangle rect2 = new Rectangle(6, 5, 2, 4, "green");
  Rectangle rect3 = new Rectangle(9, 7, 4, 2, "maroon");
  
  Triangle triangle1 = new Triangle(1, 2, 4, 6, "orange");
  Triangle triangle2 = new Triangle(3, 5, 8, 6, "pink");
  Triangle triangle3 = new Triangle(2, 1, 5, 6, "yellow");
  
  Square square1 = new Square(5, 7, 4, "grey");
  Square square2 = new Square(3, 2, 5, "black");
  Square square3 = new Square(1, 4, 6, "magenta");
  
  IList<Circle> circle = new ConsList<Circle>(circle1, new ConsList<Circle>(circle2, new ConsList<Circle>(circle3, new MtList<Circle>())));
  
  IList<Rectangle> rectangle = new ConsList<Rectangle>(rect1, new ConsList<Rectangle>(rect2, new ConsList<Rectangle>(rect3, new MtList<Rectangle>())));
  
  IList<Triangle> triangle = new ConsList<Triangle>(triangle1, new ConsList<Triangle>(triangle2, new ConsList<Triangle>(triangle3, new MtList<Triangle>())));
  
  IList<Square> square = new ConsList<Square>(square1, new ConsList<Square>(square2, new ConsList<Square>(square3, new MtList<Square>())));
  
  IList<Double> circlesArea = new ConsList<Double>(12.56, new ConsList<Double>(113.1, new ConsList<Double>(3.14, new MtList<Double>())));
  IList<Integer> rectangleArea = new ConsList<Integer>(30, new ConsList<Integer>(8, new ConsList<Integer>(8, new MtList<Integer>())));
  IList<Double> triangleArea = new ConsList<Double>(12.0, new ConsList<Double>(24.0, new ConsList<Double>(15.0, new MtList<Double>())));
  IList<Integer> squareArea = new ConsList<Integer>(16, new ConsList<Integer>(25, new ConsList<Integer>(36, new MtList<Integer>())));
  
  // test the area methods of the shapes
  boolean testMap(Tester t) {
    return
        t.checkInexact(this.circle.map(new CircleArea()), circlesArea, 0.01) &&
        t.checkInexact(this.rectangle.map(new RectangleArea()), rectangleArea, 0.01) &&
        t.checkInexact(this.triangle.map(new TriangleArea()), triangleArea, 0.01) &&
        t.checkInexact(this.square.map(new SquareArea()), squareArea, 0.01);
  }
  
  // test the find methods of the shapes
  boolean testFind(Tester t) {
    return 
        t.checkExpect(this.circle.find(new CircleColor()), new ConsList<Circle>(circle2, new MtList<Circle>())) &&
        t.checkExpect(this.rectangle.find(new RectangleColor()), new ConsList<Rectangle>(rect3, new MtList<Rectangle>())) &&
        t.checkExpect(this.triangle.find(new TriangleColor()), new ConsList<Triangle>(triangle1, new MtList<Triangle>())) &&
        t.checkExpect(this.square.find(new SquareColor()), new ConsList<Square>(square3, new MtList<Square>()));
  }
  
  // test the fold methods of the shapes
  boolean testFoldr(Tester t) {
    return 
        t.checkInexact(this.circle.foldr(new CircleLengths(), 0.0), 9.0, 0.01) &&
        t.checkExpect(this.rectangle.foldr(new RectangleLengths(), 0), 23) &&
        t.checkInexact(this.triangle.foldr(new TriangleLengths(), 0.0), 35.0, 0.01) &&
        t.checkExpect(this.square.foldr(new SquareLengths(), 0), 15);
  }
}
