// represents bulk coffee for sale
class Coffee extends AItem{
    String origin;

    public Coffee(String origin, int price) {
      super(price);
      this.origin = origin;
    }
    
    
    public boolean isCoffee() {
       return true;
    }
    
    @Override
    public Coffee toCoffee() {
      return this;
    }
    
    public boolean same(IItem other) {
      return (other.isCoffee())
      && other.toCoffee().same(this);
    }
    
    // is this the same Coffee as other?
    private boolean same(Coffee other) {
      return
      this.origin.equals(other.origin)
      && this.price == other.price;
    }
}

class Decaf extends Coffee {
    private int quality; // between 97 and 99

    Decaf(String origin, int price, int quality) {
        super(origin,price);
        this.quality = quality;
    }

    @Override
    public boolean same(IItem other) {
        return other.isCoffee()
            && other.toCoffee() instanceof Decaf
            && this.sameDecaf((Decaf) other.toCoffee());
    }

    private boolean sameDecaf(Decaf other) {
        return this.origin.equals(other.origin)
            && this.price == other.price
            && this.quality == other.quality;
    }
}
