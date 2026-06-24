import tester.*;
/**
 * HtDC Lectures
 * Lecture 5: Methods for unions of classes
 * 
 * Copyright 2013 Viera K. Proulx
 * This program is distributed under the terms of the 
 * GNU Lesser General Public License (LGPL)
 * 
 * @since 29 August 2013
 */


/*
 +----------------------------+                    
 | IShape                     |                    
 +----------------------------+                    
 | double area()              |                    
 | double distToOrigin()      |                    
 | IShape grow(int)           |                    
 | boolean biggerThan(IShape) |                    
 | boolean contains(CartPt)   |                    
 +----------------------------+                    
                    |                                    
                   / \                                   
                   ---                                   
                    |                                    
             -----------------------------------------------------------------------------------------------------                  
             |                                   |                                |                               |
   +----------------------------+   +----------------------------+   +----------------------------+   +----------------------------+
   | Circle                     |   | Square                     |   | Triangle                   |   | Rectangle                  |
   +----------------------------+   +----------------------------+   +----------------------------+   +----------------------------+
 +-| CartPt center              | +-| CartPt nw                  | +-| CartPt ne                  | +-| CartPt sw                  |
 | | int radius                 | | | int size                   | | | int base                   | | | int height                 |
 | |                            | | |                            | | | int height                 | | | int base                   |
 | | String color               | | | String color               | | | String color               | | | String color               |
 | +----------------------------+ | +----------------------------+ | +----------------------------+ | +----------------------------+
 | | double area()              | | | double area()              | | | double area()              | | | double area()              |
 | | double distToOrigin()      | | | double distToOrigin()      | | | double distToOrigin()      | | | double distToOrigin()      |
 | | IShape grow(int)           | | | IShape grow(int)           | | | IShape grow(int)           | | | IShape grow(int)           |
 | | boolean biggerThan(IShape) | | | boolean biggerThan(IShape) | | | boolean biggerThan(IShape) | | | boolean biggerThan(IShape) |
 | | boolean contains(CartPt)   | | | boolean contains(CartPt)   | | | boolean contains(CartPt)   | | | boolean contains(CartPt)   |
 | +----------------------------+ | +----------------------------+ | +----------------------------+ | +----------------------------+
 +----+ +-------------------------+--------------------------------+--------------------------------+
      | |
      v v                                                                     
 +-----------------------+
 | CartPt                |
 +-----------------------+
 | int x                 |
 | int y                 |
 +-----------------------+
 | double distToOrigin() |
 | double distTo(CartPt) |
 +-----------------------+ 
 */

// to represent a geometric shape
interface IShape {
    // to compute the area of this shape
    double area();
    
    // to compute the distance form this shape to the origin
    double distToOrigin();
    
    // to increase the size of this shape by the given increment
    IShape grow(int inc);
    
    // is the area of this shape bigger than the area of the given shape?
    boolean biggerThan(IShape that);
    
    // does this shape (including the boundary) contain the given point?
    boolean contains(CartPt pt);
}

// to represent a circle
class Circle implements IShape {
    CartPt center;
    int radius;
    String color;
    
    Circle(CartPt center, int radius, String color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }
    
    // to compute the area of this shape
    public double area(){
        return Math.PI * this.radius * this.radius;
    }
    
    // to compute the distance form this shape to the origin
    public double distToOrigin(){
        return this.center.distToOrigin() - this.radius;
    }
    
    // to increase the size of this shape by the given increment
    public IShape grow(int inc){
        return new Circle(this.center, this.radius + inc, this.color);
    }
    
    // is the area of this shape bigger than the area of the given shape?
    public boolean biggerThan(IShape that){
        return this.area() >= that.area();
    }
    
    // does this shape (including the boundary) contain the given point?
    public boolean contains(CartPt pt){
        return this.center.distTo(pt) <= this.radius;
    }
    
}

// to represent a square
class Square implements IShape {
    CartPt nw;
    int size;
    String color;
    
    Square(CartPt nw, int size, String color) {
        this.nw = nw;
        this.size = size;
        this.color = color;
    }
    
    // to compute the area of this shape
    public double area(){
        return this.size * this.size;
    }
    
    // to compute the distance form this shape to the origin
    public double distToOrigin(){
        return this.nw.distToOrigin();
    }
    
    // to increase the size of this shape by the given increment
    public IShape grow(int inc){
        return new Square(this.nw, this.size + inc, this.color);
    }
    
    // is the area of this shape bigger than the area of the given shape?
    public boolean biggerThan(IShape that){
        return this.area() >= that.area();
    }
    
    // does this shape (including the boundary) contain the given point?
    public boolean contains(CartPt pt){
        return (this.nw.x <= pt.x) && (pt.x <= this.nw.x + this.size) &&
        (this.nw.y <= pt.y) && (pt.y <= this.nw.y + this.size);            
    }
}

// to represent a triangle
class Triangle implements IShape {
    CartPt ne;
    int base;
    int height;
    String color;

    Triangle(CartPt ne, int base, int height, String color) {
        this.ne = ne;
        this.base = base;
        this.height = height;
        this.color = color;
    }

    // to compute the area of this shape
    public double area() {
        return (this.base * this.height) / 2.0;
    }

    // to compute the distance form this shape to the origin
    public double distToOrigin() {
        return this.ne.distToOrigin();
    }

    // to increase the size of this shape by the given increment
    public IShape grow(int inc) {
        return new Triangle(this.ne, this.base + inc, this.height + inc, this.color);
    }

    // is the area of this shape bigger than the area of the given shape?
    public boolean biggerThan(IShape that) {
        return this.area() >= that.area();  
    }

    // does this shape (including the boundary) contain the given point?
    public boolean contains(CartPt pt) {
        return (this.ne.x - this.base <= pt.x) && (pt.x <= this.ne.x) &&
               (this.ne.y <= pt.y) && (pt.y <= this.ne .y + this.height);
    }
}

// to represent a rectangle
class Rectangle implements IShape {
    CartPt sw;
    int base;   
    int height;
    String color;

    Rectangle(CartPt sw, int base, int height, String color) {
        this.sw = sw;
        this.base = base;
        this.height = height;
        this.color = color;
    }

    // to compute the area of this shape
    public double area() {
        return this.base * this.height;
    }

    // to compute the distance form this shape to the origin
    public double distToOrigin() {
        return this.sw.distToOrigin();
    }

    // to increase the size of this shape by the given increment
    public IShape grow(int inc) {
        return new Rectangle(this.sw, this.base + inc, this.height + inc, this.color);
    }

    // is the area of this shape bigger than the area of the given shape?
    public boolean biggerThan(IShape that) {
        return this.area() >= that.area();  
    }

    // does this shape (including the boundary) contain the given point?
    public boolean contains(CartPt pt) {
        return (this.sw.x <= pt.x) && (pt.x <= this.sw.x + this.base) &&
               (this.sw.y <= pt.y) && (pt.y <= this.sw.y + this.height);
    }
}

/*
 +--------+
 | CartPt |
 +--------+
 | int x  |
 | int y  |
 +--------+
 
 */

// to represent a Cartesian point
class CartPt {
    int x;
    int y;
    
    CartPt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // to compute the distance form this point to the origin
    public double distToOrigin(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    
    // to compute the distance form this point to the given point
    public double distTo(CartPt pt){
        return Math.sqrt((this.x - pt.x) * (this.x - pt.x) + 
                         (this.y - pt.y) * (this.y - pt.y));
    }
}

class ExamplesShapes {
    ExamplesShapes() {}
    
    CartPt pt1 = new CartPt(0, 0);
    CartPt pt2 = new CartPt(3, 4);
    CartPt pt3 = new CartPt(7, 1);
    
    IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
    IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
    IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");
    
    IShape s1 = new Square(new CartPt(50, 50), 30, "red");
    IShape s2 = new Square(new CartPt(50, 50), 50, "red");
    IShape s3 = new Square(new CartPt(20, 40), 10, "green");
    
    // test the method distToOrigin in the class CartPt
    boolean testDistToOrigin(Tester t) { 
        return
        t.checkInexact(this.pt1.distToOrigin(), 0.0, 0.001) &&
        t.checkInexact(this.pt2.distToOrigin(), 5.0, 0.001);
    }
    
    // test the method distTo in the class CartPt
    boolean testDistTo(Tester t) { 
        return
        t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001) &&
        t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
    }
    
    // test the method area in the class Circle
    boolean testCircleArea(Tester t) { 
        return
        t.checkInexact(this.c1.area(), 314.15, 0.01);
    }
    
    // test the method grow in the class Circle
    boolean testSquareArea(Tester t) { 
        return
        t.checkInexact(this.s1.area(), 900.0, 0.01);
    }
    
    // test the method distToOrigin in the class Circle
    boolean testCircleDistToOrigin(Tester t) { 
        return
        t.checkInexact(this.c1.distToOrigin(), 60.71, 0.01) &&
        t.checkInexact(this.c3.distToOrigin(), 74.40, 0.01);
    }
    
    // test the method distTo in the class Circle
    boolean testSquareDistToOrigin(Tester t) { 
        return
        t.checkInexact(this.s1.distToOrigin(), 70.71, 0.01) &&
        t.checkInexact(this.s3.distToOrigin(), 44.72, 0.01);
    }
    
    // test the method grow in the class Circle
    boolean testCircleGrow(Tester t) { 
        return
        t.checkExpect(this.c1.grow(20), this.c2);
    }
    
    // test the method grow in the class Circle
    boolean testSquareGrow(Tester t) { 
        return
        t.checkExpect(this.s1.grow(20), this.s2);
    }
    
    // test the method biggerThan in the class Circle
    boolean testCircleBiggerThan(Tester t) { 
        return
        t.checkExpect(this.c1.biggerThan(this.c2), false) && 
        t.checkExpect(this.c2.biggerThan(this.c1), true) && 
        t.checkExpect(this.c1.biggerThan(this.s1), false) && 
        t.checkExpect(this.c1.biggerThan(this.s3), true);
    }
    
    // test the method biggerThan in the class Square
    boolean testSquareBiggerThan(Tester t) { 
        return
        t.checkExpect(this.s1.biggerThan(this.s2), false) && 
        t.checkExpect(this.s2.biggerThan(this.s1), true) && 
        t.checkExpect(this.s1.biggerThan(this.c1), true) && 
        t.checkExpect(this.s3.biggerThan(this.c1), false);
    }
    
    // test the method contains in the class Circle
    boolean testCircleContains(Tester t) { 
        return
        t.checkExpect(this.c1.contains(new CartPt(100, 100)), false) && 
        t.checkExpect(this.c2.contains(new CartPt(40, 60)), true);
    }
    
    
    // test the method contains in the class Square
    boolean testSquareContains(Tester t) { 
        return
        t.checkExpect(this.s1.contains(new CartPt(100, 100)), false) && 
        t.checkExpect(this.s2.contains(new CartPt(55, 60)), true);
    }
}
