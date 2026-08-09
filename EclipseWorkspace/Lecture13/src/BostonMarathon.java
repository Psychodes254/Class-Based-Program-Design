import tester.*;

// to represent ILoRunner interface
interface ILoRunner{
    ILoRunner find(IRunnerPredicate predicate);
    ILoRunner sortBy(IRunnerComparator comp);
    ILoRunner insertBy(IRunnerComparator comp, Runner r);
    Runner findMin(IRunnerComparator comp);
    Runner findMinHelper(IRunnerComparator comp, Runner currentMinRunner);
}

// to represent MtLoRunner class
class MtLoRunner implements ILoRunner{
    MtLoRunner(){}

    public ILoRunner find(IRunnerPredicate predicate){
        return new MtLoRunner();
    }

    public ILoRunner sortBy(IRunnerComparator comp){
        return this;
    }

    public ILoRunner insertBy(IRunnerComparator comp, Runner r){
        return new ConsLoRunner(r, this);
    }

    public Runner findMin(IRunnerComparator comp){
        throw new RuntimeException("No winner of minimum list of Runners!");
    }

    public Runner findMinHelper(IRunnerComparator comp, Runner currentMinRunner){
        return currentMinRunner;
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

    public ILoRunner sortBy(IRunnerComparator comp){
        return this.rest.sortBy(comp).insertBy(comp, this.first);
    }

    public ILoRunner insertBy(IRunnerComparator comp, Runner r){
        if (comp.compare(this.first, r) < 0){
            return new ConsLoRunner(this.first, this.rest.insertBy(comp, r));
        }
        else{
            return new ConsLoRunner(r, this);
        }
    }

    public Runner findMin(IRunnerComparator comp){
        return this.rest.findMinHelper(comp, this.first);
    }

    public Runner findMinHelper(IRunnerComparator comp, Runner currentMinRunner){
        if (comp.compare(this.first, currentMinRunner) < 0){
            return this.rest.findMinHelper(comp, this.first);
        }
        else{
            return this.rest.findMinHelper(comp, currentMinRunner);
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

    ILoRunner sortedByTime = new ConsLoRunner(this.bill,
                            new ConsLoRunner(this.frank,
                            new ConsLoRunner(this.joan,
                            new ConsLoRunner(this.johnny, new MtLoRunner()))));

    ILoRunner sortedByAge = new ConsLoRunner(this.joan,
                            new ConsLoRunner(this.frank,
                            new ConsLoRunner(this.bill,
                            new ConsLoRunner(this.johnny, new MtLoRunner()))));

    boolean testFindMethods(Tester t) {
        return
        t.checkExpect(this.list2.find(new RunnerIsFemale()), femalRunners) &&
        t.checkExpect(this.list2.find(new RunnerIsMale()), maleRunners) &&
        t.checkExpect(this.list2.find(new RunnerIsInFirst50()), posUnder50Runners) &&
        t.checkExpect(this.list2.find(new AndPredicate(new RunnerIsMale(), new RunnerIsInFirst50())), isMaleUnder50) &&
        t.checkExpect(this.list2.find(new AndPredicate(new RunnerIsMale(), new UnderAge40())), isMaleUnderAge40);
    }

    boolean testSortByTime(Tester t){
        return
        t.checkExpect(this.list2.sortBy(new CompareByTime()), sortedByTime) &&
        t.checkExpect(this.list2.sortBy(new CompareByAge()), sortedByAge);
    }

    boolean testFindWinners(Tester t){
        return 
        t.checkExpect(this.list2.findMin(new CompareByTime()), bill) &&
        t.checkExpect(this.list2.findMin(new CompareByAge()), joan) &&
        t.checkExpect(this.list2.findMin(new CompareByPosition()), joan);
    }
}