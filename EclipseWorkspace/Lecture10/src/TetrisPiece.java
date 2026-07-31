// to represent interface ITetrisPiece
interface ITetrisPiece { 
  int SQUARE_HEIGHT = 30;
}

// to represent abstract class ATetrisPiece 
abstract class ATetrisPiece implements ITetrisPiece{
  int xPos;
  int yPos;
  
  ATetrisPiece(int x, int y){
    this.xPos = x;
    this.yPos = y;
  }
  
  ATetrisPiece(int x){
    this(x, SQUARE_HEIGHT);
  }
}

// to represent class Square
class Square extends ATetrisPiece{
  Square(int topLeftX, int topLeftY){
    super(topLeftX, topLeftY);
  }
  
  Square(int topLeftX){
    super(topLeftX);
  }
}

// to represent class LShape
class LShape extends ATetrisPiece{
  LShape(int cornerX, int cornerY){
    super(cornerX, cornerY);
  }
  
  LShape(int cornerX){
    super(cornerX);
  }

}

// to represent class ExamplesTetris 
class ExamplesTetris {
  ITetrisPiece square1 = new Square(3, 30);
  ITetrisPiece square2 = new Square(3);
  ITetrisPiece lshape1 = new LShape(3, 30);
  ITetrisPiece lshape2 = new LShape(3);
}