import tester.*;

// to represent Factoring class
class Factoring{
    private int first;
    private int second;

    Factoring(int first, int second){
        this.first = first;
        this.second = second;
    }

    public int product(){
        return this.first * this.second;
    }

    public boolean sameFactoring(Factoring other){
        return (this.first == other.first) ||
                (this.second == other.second) ||
                (this.first == other.second) ||
                (this.second == other.first);
    }

    public boolean sameProduct(Factoring other){
        return (this.product() == other.product());
    }
}

// to represent ExamplesFactoring for test and examples
class ExamplesFactoring{
    ExamplesFactoring(){}

    Factoring firstFactor = new Factoring(12, 10);
    Factoring secondFactor = new Factoring(5, 8);
    Factoring thirdFactor = new Factoring(4, 10);

    Factoring fourthFactor = new Factoring(20, 6);
    Factoring fifthFactor = new Factoring(4, 11);
    Factoring sixthFactor = new Factoring(3, 9);

    // test the product method
    boolean testProduct(Tester t){
        return
        t.checkExpect(this.firstFactor.product(), 120) &&
        t.checkExpect(this.secondFactor.product(), 40) &&
        t.checkExpect(this.thirdFactor.product(), 40);
    }

    // test the same factoring method
    boolean testSameFactor(Tester t){
        return
        t.checkExpect(this.firstFactor.sameFactoring(thirdFactor), true) &&
        t.checkExpect(this.fifthFactor.sameFactoring(thirdFactor), true) &&
        t.checkExpect(this.secondFactor.sameFactoring(sixthFactor), false) &&
        t.checkExpect(this.fourthFactor.sameFactoring(firstFactor), false);
    }

    // test the same product method
    boolean testSameProduct(Tester t){
        return
        t.checkExpect(this.firstFactor.sameProduct(fourthFactor), true) &&
        t.checkExpect(this.thirdFactor.sameProduct(secondFactor), true) &&
        t.checkExpect(this.thirdFactor.sameProduct(sixthFactor), false) &&
        t.checkExpect(this.sixthFactor.sameProduct(firstFactor), false);
    }
}