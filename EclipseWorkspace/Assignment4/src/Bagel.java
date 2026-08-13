import tester.*;

// to represent BagelRecipe class
class BagelRecipe{
    double flour, water, yeast, salt, malt;

    static final double TSP_PER_CUP = 48.0;
    static final double FLOUR_OZ_PER_CUP = 4.25;
    static final double WATER_OZ_PER_CUP = 8.0;
    static final double YEAST_OZ_PER_CUP  = 5.0;
    static final double SALT_OZ_PER_CUP   = 10.0;
    static final double MALT_OZ_PER_CUP   = 11.0;

    static final double TOLERANCE = 0.001;

    BagelRecipe(double flour, double water, double yeast, double salt, double malt){
        if (Math.abs(flour - water) > TOLERANCE){
            throw new IllegalArgumentException (
                "Imperfect recipe: Water (" + water + ") must be equal to Flour (" + flour + ")"
            );
        }

        if (Math.abs(yeast - malt) > TOLERANCE){
            throw new IllegalArgumentException (
                "Imperfect recipe: Yeast (" + yeast + ") must be equal to Malt (" + malt + ")"
            );
        }

        if (Math.abs((salt + yeast) - flour / 20) > TOLERANCE){
            throw new IllegalArgumentException (
                "Imperfect recipe: Salt + Yeast (" + (salt + yeast) + ") must be equal to flour / 20 (" + (flour / 20)+ ")"
            );
        }

        this.flour = flour;
        this.water = water;
        this.yeast = yeast;
        this.salt = salt;
        this.malt = malt;
    }

    BagelRecipe(double flour, double yeast){
        this(flour, flour, yeast, (flour / 20) - yeast, yeast);
    }

    BagelRecipe(double flourCups, double yeastTsp, double saltTsp){
        this(flourCups * FLOUR_OZ_PER_CUP,
             flourCups * FLOUR_OZ_PER_CUP,
            (yeastTsp / TSP_PER_CUP) * YEAST_OZ_PER_CUP,
            (saltTsp  / TSP_PER_CUP) * SALT_OZ_PER_CUP,
            (yeastTsp / TSP_PER_CUP) * YEAST_OZ_PER_CUP);
    }

    boolean sameRecipe(BagelRecipe other) {
    return close(this.flour, other.flour)
        && close(this.water, other.water)
        && close(this.yeast, other.yeast)
        && close(this.salt, other.salt)
        && close(this.malt, other.malt);
    }

    // helper: are two weights equal within TOLERANCE ounces?
    private boolean close(double a, double b) {
        return Math.abs(a - b) <= TOLERANCE;
    }
}

class ExamplesBagelRecipe{
    ExamplesBagelRecipe(){}

      BagelRecipe recipe1;
  BagelRecipe recipe2;
  BagelRecipe recipe3;
  BagelRecipe recipe4;

  void initData() {
    // two recipes built the same way -> should be sameRecipe
    this.recipe1 = new BagelRecipe(20.0, 20.0, 2.0, -1.0, 2.0);
    this.recipe2 = new BagelRecipe(20.0, 2.0); // flour/yeast convenience ctor

    // a recipe that differs slightly, but still within tolerance
    this.recipe3 = new BagelRecipe(20.0, 20.0005, 2.0, -1.0, 2.0);

    // a recipe with clearly different weights
    this.recipe4 = new BagelRecipe(10.0, 1.0);
  }

  // tests for sameRecipe
  boolean testSameRecipe(Tester t) {
    this.initData();
    return t.checkExpect(this.recipe1.sameRecipe(this.recipe2), true)
        && t.checkExpect(this.recipe2.sameRecipe(this.recipe1), true)
        && t.checkExpect(this.recipe1.sameRecipe(this.recipe1), true)
        && t.checkExpect(this.recipe1.sameRecipe(this.recipe3), true)  // within 0.001 tolerance
        && t.checkExpect(this.recipe1.sameRecipe(this.recipe4), false);
  }

  // tests that the main constructor enforces constraints correctly
  boolean testConstructorValidRecipe(Tester t) {
    this.initData();
    return t.checkExpect(this.recipe1.flour, 20.0)
        && t.checkExpect(this.recipe1.water, 20.0)
        && t.checkExpect(this.recipe1.salt, -1.0);
  }

  //tests that bad weight combinations throw IllegalArgumentException
  boolean testConstructorInvalidRecipe(Tester t) {
   return t.checkConstructorException(
             new IllegalArgumentException(
                 "Imperfect recipe: Water (5.0) must be equal to Flour (20.0)"),
             "BagelRecipe", 20.0, 5.0, 2.0, -1.0, 2.0)
       && t.checkConstructorException(
           new IllegalArgumentException(
               "Imperfect recipe: Yeast (2.0) must be equal to Malt (3.0)"),
           "BagelRecipe", 20.0, 20.0, 2.0, -1.0, 3.0);
  }

  //tests the volume-based (cups/tsp) constructor, including a bad-salt case
  boolean testVolumeConstructor(Tester t) {
  // flour: 4 cups -> 17.0 oz flour/water; yeast: 96 tsp -> 10.0 oz yeast/malt
  // salt required: flour/20 - yeast = 17.0/20 - 10.0 = -9.15 oz, but saltTsp=0.0 -> 0.0 oz
  // so salt + yeast = 10.0, which should NOT equal flour/20 = 0.85 -> exception expected
  return t.checkConstructorException(
     new IllegalArgumentException(
         "Imperfect recipe: Salt + Yeast (10.0) must be equal to flour / 20 (0.85)"),
     "BagelRecipe", 4.0, 96.0, 0.0);
  }
}
