// to represent class Chocolate
class Chocolate implements IItem{
  String sweetness;
  int price;
  
  Chocolate(String sweetness, int price){
    this.sweetness = sweetness;
    this.price = price;
  }
  
  public boolean isTea() {
    return false;
  }
  
  public boolean isCoffee() {
    return false;
  }
  
  public Tea toTea() {
    throw new IllegalArgumentException("not a tea");
  }
  
  public Coffee toCoffee() {
    throw new IllegalArgumentException("not a coffee");
  }
  
  public boolean same(IItem other) {
    return other.isChocolate()
    && other.toChocolate().same(this);
  }
  
  // is this the same Tea as other?
  private boolean same(Chocolate other) {
    return
    this.sweetness.equals(other.sweetness)
    && this.price == other.price;
  }
  
  public boolean isChocolate() {
    return true;
  }
  
  public Chocolate toChocolate() {
    return this; 
  }
}
