import tester.Tester;

//to represent IGamePiece interface
interface IGamePiece { 
  int getValue();
  IGamePiece merge(IGamePiece other);
  boolean isValid();
}

//to represent BaseTile class
class BaseTile implements IGamePiece{
  int value;
  
  BaseTile(int value){
    this.value = value;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public IGamePiece merge(IGamePiece other){
    return new MergeTile(this, other);
  }
  
  public boolean isValid(){
    return true;
  }
}

//to represent MergeTile class
class MergeTile implements IGamePiece{
  IGamePiece piece1;
  IGamePiece piece2;
  
  MergeTile(IGamePiece piece1, IGamePiece piece2){
   this.piece1 = piece1;
   this.piece2 = piece2;
  }
  
  public int getValue() {
     return this.piece1.getValue() + 
            this.piece2.getValue();
  }
  
  public IGamePiece merge(IGamePiece other){
    return new MergeTile(this, other);
  }
  
  public boolean isValid(){
    return this.piece1.getValue() == 
           this.piece2.getValue() &&
           this.piece1.isValid() &&
           this.piece2.isValid();
  }
}

//to represent ExamplesGamePiece class
class ExamplesGamePiece{
  IGamePiece two = new BaseTile(2);
  IGamePiece three = new BaseTile(3);
  IGamePiece four = new BaseTile(4);
  
  IGamePiece twoTwo = two.merge(two);
  IGamePiece twoFour = two.merge(four);
  IGamePiece validTree = twoTwo.merge(four);
  IGamePiece invalidTree = twoFour.merge(new BaseTile(6));
  IGamePiece merged = three.merge(two);
  
  boolean testGetValue(Tester t) {
   return
       t.checkExpect(merged.getValue(), 5);
  }

  boolean testIsValid(Tester t) {
    return
        t.checkExpect(this.two.isValid(), true)
     && t.checkExpect(this.four.isValid(), true)
     && t.checkExpect(this.twoTwo.isValid(), true)
     && t.checkExpect(this.twoFour.isValid(), false)
     && t.checkExpect(this.validTree.isValid(), true)
     && t.checkExpect(this.invalidTree.isValid(), false);
  }
}