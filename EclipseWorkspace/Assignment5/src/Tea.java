class Tea implements IItem {
  private String kind;
  private int price;
  
  Tea(String kind, int price) {
    this.kind = kind;
    this.price = price;
  }
  
  public boolean isTea() {
    return true;
  }
  
  public boolean isCoffee() {
    return false;
  }
  
  public Tea toTea() {
    return this;
  }
  
  public Coffee toCoffee() {
    throw new IllegalArgumentException("not a coffee");
  }
  
  public boolean same(IItem other) {
    return other.isTea()
    && other.toTea().same(this);
  }
  
  // is this the same Tea as other?
  private boolean same(Tea other) {
    return
    this.kind.equals(other.kind)
    && this.price == other.price;
  }
}
