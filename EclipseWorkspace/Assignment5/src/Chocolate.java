// to represent class Chocolate
class Chocolate extends AItem{
  private String sweetness;
  
  Chocolate(String sweetness, int price){
    super(price);
    this.sweetness = sweetness;
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
  
  @Override
  public Chocolate toChocolate() {
    return this; 
  }
}