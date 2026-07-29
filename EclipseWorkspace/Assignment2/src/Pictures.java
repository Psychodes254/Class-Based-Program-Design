import tester.*;

// to represent IPicture interface 
interface IPicture { }

// to represent Shape class
class Shape implements IPicture {
  String kind;
  int size;
  
  // the constructor
  Shape(String kind, int size){
    this.kind = kind;
    this.size = size;
  }
}

// to represent Combo class
class Combo implements IPicture {
  String description;
  IOperation operation;
  
  // the constructor 
  Combo(String description, IOperation operation){
    this.description = description;
    this.operation = operation;
  }
}

// to represent IOperation interface 
interface IOperation { }

// to represent Scale class
class Scale implements IOperation {
  IPicture picture;
  
  // the constructor
  Scale(IPicture picture){
    this.picture = picture;
  }
}

// to represent Beside class 
class Beside implements IOperation {
  IPicture left;
  IPicture right;
  
  // the constructor
  Beside(IPicture left, IPicture right){
    this.left = left;
    this.right = right;
  }
}

// to represent Overlay class
class Overlay implements IOperation {
  IPicture top;
  IPicture bottom;
  
  // the constructor
  Overlay(IPicture top, IPicture bottom){
    this.top = top;
    this.bottom = bottom;
  }
}

//to represent tests and examples for ExamplesPictures class
class ExamplesPictures{
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  
  IPicture bigCircle = new Combo("big circle", new Scale(this.circle));
  
  IPicture squareOnCircle = new Combo("square on circle", new Overlay(this.square, this.circle));
  
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle", new Scale(this.squareOnCircle));
}