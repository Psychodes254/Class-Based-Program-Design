import tester.*;

// to represent ILoRunner interface
interface ILoRunner{
    ILoRunner find(IRunnerPredicate predicate);
}

// to represent MtLoRunner class
class MtLoRunner implements ILoRunner{
    MtLoRunner(){}

    public ILoRunner find(IRunnerPredicate predicate){
        return new MtLoRunner();
    }
}

// to represent ConsLoRunner class
class ConsLoRunner implements ILoRunner{
    Runner first;
    ILoRunner rest;

    ConsLoRunner(Runner first, ILoRunner rest){
        this.first = first;
        this.rest = rest;
    }

    public ILoRunner find(IRunnerPredicate predicate){
        if (predicate.apply(this.first)){
            return new ConsLoRunner(this.first, this.rest.find(predicate));
        }
        else{
            return this.rest.find(predicate);
        }
    }
}

// tu represent Runner class
class Runner{
    String name;
    int age;
    int bib;
    boolean isMale;
    int position;
    int time;

    Runner(String name, int age, int bib, boolean isMale, int position, int time){
        this.name = name;
        this.age = age;
        this.bib = bib;
        this.isMale = isMale;
        this.position = position;
        this.time = time;
    }
}

// to represent ExamplesBostonMarathon.java class
class ExamplesBostonMarathon {
    ExamplesBostonMarathon(){}

    Runner johnny = new Runner("Kelly", 97, 999, true, 30, 360);
    Runner frank  = new Runner("Shorter", 32, 888, true, 245, 130);
    Runner bill = new Runner("Rogers", 36, 777, true, 119, 129);
    Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);
 
    ILoRunner mtlist = new MtLoRunner();
    ILoRunner list1 = new ConsLoRunner(johnny, new ConsLoRunner(joan, mtlist));
    ILoRunner list2 = new ConsLoRunner(frank, new ConsLoRunner(bill, list1));

    ILoRunner femalRunners = new ConsLoRunner(this.joan, new MtLoRunner());

    ILoRunner maleRunners = new ConsLoRunner(this.frank,
                            new ConsLoRunner(this.bill,
                            new ConsLoRunner(this.johnny, new MtLoRunner())));

    ILoRunner posUnder50Runners = new ConsLoRunner(this.johnny,
                                  new ConsLoRunner(this.joan, new MtLoRunner()));

    ILoRunner isMaleUnder50 = new ConsLoRunner(this.johnny, new MtLoRunner());

    ILoRunner isMaleUnderAge40 = new ConsLoRunner(this.frank,
                                 new ConsLoRunner(this.bill, new MtLoRunner()));

    boolean testFindMethods(Tester t) {
        return
        t.checkExpect(this.list2.find(new RunnerIsFemale()), femalRunners) &&
        t.checkExpect(this.list2.find(new RunnerIsMale()), maleRunners) &&
        t.checkExpect(this.list2.find(new RunnerIsInFirst50()), posUnder50Runners) &&
        t.checkExpect(this.list2.find(new AndPredicate(new RunnerIsMale(), new RunnerIsInFirst50())), isMaleUnder50) &&
        t.checkExpect(this.list2.find(new AndPredicate(new RunnerIsMale(), new UnderAge40())), isMaleUnderAge40);
    }
}