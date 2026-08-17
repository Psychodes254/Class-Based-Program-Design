class Tea extends AItem {
  private String kind;
  
  Tea(String kind, int price) {
    super(price);
    this.kind = kind;
  }
  
  public boolean isTea() {
    return true;
  }
  
  @Override
  public Tea toTea() {
    return this;
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
