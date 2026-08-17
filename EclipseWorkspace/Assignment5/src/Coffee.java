// represents bulk coffee for sale
class Coffee implements IItem{
    private String origin;
    private int price;

    public Coffee(String origin, int price) {
        this.origin = origin;
        this.price = price;
    }
    
    public boolean isCoffee() {
        return true;
      }
    
      public boolean isTea() {
        return false;
      }
      
      public Coffee toCoffee() {
        return this;
      }
      
      public Tea toTea() {
        throw new IllegalArgumentException("not a tea");
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

    public boolean same(Decaf other) {
        return super.same(other) && this.quality == other.quality;
    }
}
