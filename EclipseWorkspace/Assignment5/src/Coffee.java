import tester.*;

// represents bulk coffee for sale
class Coffee {
    private String origin;
    private int price;

    public Coffee(String origin, int price) {
        this.origin = origin;
        this.price = price;
    }

    // is this the same Coffee as other?
    public boolean same(Coffee other) {
        return this.origin.equals(other.origin) && this.price==other.price;
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

class ExamplesCoffee {
    ExamplesCoffee(){}

    Coffee ethi = new Coffee("Ethiopian",1200);
    Coffee kona = new Coffee("Kona",2095);
    Coffee ethi1300 = (new Coffee("Ethiopian",1300));
    Decaf decaf1 = new Decaf("Ethiopian",1200,99);
    Decaf decaf2 = new Decaf("Ethiopian",1200,98);

    // test the sameness of the coffee method
    boolean testSameCoffee(Tester t){
        return
        t.checkExpect(this.ethi.same(this.ethi), true) &&
        t.checkExpect(this.kona.same(this.ethi), false) &&
        t.checkExpect(this.ethi.same(this.ethi1300), false);
    }

    // test the sameness of the decaffeinated coffee
    boolean testSameDecaf(Tester t){
        return 
        t.checkExpect(this.decaf2.same(this.decaf1), false) &&
        t.checkExpect(this.decaf1.same(this.decaf1), true);
    }
}