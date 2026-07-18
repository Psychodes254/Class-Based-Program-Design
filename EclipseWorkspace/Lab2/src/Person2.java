import tester.*;

// to represent a pet owner
class Person2 {
    String name;
    IPet pet;
    int age;
    boolean perish;
 
    Person2(String name, IPet pet, int age, boolean perish) {
        this.name = name;
        this.pet = pet;
        this.age = age;
        this.perish = perish;
    }
    
    // is this Person older than the given Person?
    boolean isOlder(Person2 other) {
      return (this.age > other.age);
    }
    
    // check whether this pet’s name matches the given name
    boolean samePet(String name) {
      return this.pet.hasName(name);
  }
}

// to represent a pet
interface IPet { 
  boolean hasName(String name);
}
 
// to represent a pet cat
class Cat implements IPet {
    String name;
    String kind;
    boolean longhaired;
 
    Cat(String name, String kind, boolean longhaired) {
        this.name = name;
        this.kind = kind;
        this.longhaired = longhaired;
    }
    
    // check whether this pet’s name matches the given name
    public boolean hasName(String name) {
      return (this.name.equals(name));
    }
}
 
// to represent a pet dog
class Dog implements IPet {
    String name;
    String kind;
    boolean male;
 
    Dog(String name, String kind, boolean male) {
        this.name = name;
        this.kind = kind;
        this.male = male;
    }
    
    // check whether this pet’s name matches the given name
    public boolean hasName(String name) {
      return (this.name.equals(name));
    }
}

// to represent a no pet
class NoPet implements IPet{
  NoPet(){}
  
  //check whether this pet’s name matches the given name
  public boolean hasName(String name) {
    return false;
  }
}


class ExamplesPerson2{
  // examples of cats
  IPet whiskers = new Cat("Whiskers", "Siamese", false);
  IPet luna = new Cat("Luna", "Persian", true);

  // examples of dogs
  IPet max = new Dog("Max", "Golden Retriever", true);
  IPet bella = new Dog("Bella", "Beagle", false);

  // example of no pet
  IPet none = new NoPet();

  // examples of people
  Person2 alice = new Person2("Alice", whiskers, 22, false);
  Person2 brian = new Person2("Brian", luna, 35, true);
  Person2 carol = new Person2("Carol", max, 28, false);
  Person2 david = new Person2("David", bella, 41, true);
  Person2 emma = new Person2("Emma", none, 19, false);
  Person2 frank = new Person2("Frank", none, 41, true);

  // Tests for isOlder
  boolean testIsOlder(Tester t) {
      return
          t.checkExpect(alice.isOlder(brian), false)
          && t.checkExpect(brian.isOlder(carol), true)
          && t.checkExpect(carol.isOlder(david), false)
          && t.checkExpect(david.isOlder(alice), true)
          && t.checkExpect(david.isOlder(frank), false)
          && t.checkExpect(emma.isOlder(alice), false);
  }
      
  // Tests for samePet
  boolean testSamePet(Tester t) {
      return
          t.checkExpect(alice.samePet("Whiskers"), true)
          && t.checkExpect(brian.samePet("Luna"), true)
          && t.checkExpect(carol.samePet("Max"), true)
          && t.checkExpect(david.samePet("Bella"), true)
          && t.checkExpect(alice.samePet("Luna"), false)
          && t.checkExpect(carol.samePet("Bella"), false)
          && t.checkExpect(emma.samePet("Whiskers"), false)
          && t.checkExpect(frank.samePet("Max"), false);
  }

  // Tests for perish field (simple field checks)
  boolean testPerish(Tester t) {
      return
          t.checkExpect(alice.perish, false)
          && t.checkExpect(brian.perish, true)
          && t.checkExpect(carol.perish, false)
          && t.checkExpect(david.perish, true)
          && t.checkExpect(emma.perish, false)
          && t.checkExpect(frank.perish, true);
  }
}