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

// to represent predicate classes functions
class CircleColor implements IPredicate<Circle>{
  public boolean apply(Circle c) {
    return c.color.equals("blue");
  }
}

class RectangleColor implements IPredicate<Rectangle>{
  public boolean apply(Rectangle r) {
    return r.color.equals("maroon");
  }
}

class TriangleColor implements IPredicate<Triangle>{
  public boolean apply(Triangle t) {
    return t.color.equals("orange");
  }
}

class SquareColor implements IPredicate<Square>{
  public boolean apply(Square s) {
    return s.color.equals("magenta");
  }
}

// to represent Area of IShape shapes
class CircleArea implements IFunction<Circle, Double>{
  public Double apply(Circle c) {
    return Math.PI * c.radius * c.radius;
  }
}

class RectangleArea implements IFunction<Rectangle, Integer>{
  public Integer apply(Rectangle r) {
    return r.base * r.height;
  }
}

class TriangleArea implements IFunction<Triangle, Double>{
  public Double apply(Triangle t) {
    return t.base * t.height * 0.5;
  }
}

class SquareArea implements IFunction<Square, Integer>{
  public Integer apply(Square s) {
    return s.size * s.size;
  }
}

// classes that represent total areas of the shapes
class CircleLengths implements IFunction2<Circle, Double, Double>{
  public Double apply(Circle c, Double initial) {
    return c.radius + initial;
  }
}

class RectangleLengths implements IFunction2<Rectangle, Integer, Integer>{
  public Integer apply(Rectangle r, Integer initial) {
    return (r.base + r.height) + initial;
  }
}

class TriangleLengths implements IFunction2<Triangle, Double, Double>{
  public Double apply(Triangle t, Double initial) {
    return (t.base + t.height) + initial;
  }
}

class SquareLengths implements IFunction2<Square, Integer, Integer>{
  public Integer apply(Square s, Integer initial) {
    return s.size + initial;
  }
}


