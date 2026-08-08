// to represent IRunnerPredicate interface
interface IRunnerPredicate{
    boolean apply(Runner r);
}

// to represent class RunnerIsMale
class RunnerIsMale implements IRunnerPredicate{
    RunnerIsMale(){}

    public boolean apply(Runner r){
        return r.isMale;
    }
}

// to represent class RunnerIsFemale
class RunnerIsFemale implements IRunnerPredicate{
    RunnerIsFemale(){}

    public boolean apply(Runner r){
        return !r.isMale;
    }
}

// to represent class RunnerIsInFirst50 
class RunnerIsInFirst50 implements IRunnerPredicate{
    RunnerIsInFirst50(){}

    public boolean apply(Runner r){
        return r.position <= 50;
    }
}

// to represent class UnderAge40
class UnderAge40 implements IRunnerPredicate{
    UnderAge40(){}

    public boolean apply(Runner r){
        return r.age <= 40;
    }
}

// to represent class AndPredicate 
class AndPredicate implements IRunnerPredicate{
    IRunnerPredicate left, right;

    AndPredicate(IRunnerPredicate left, IRunnerPredicate right){
        this.left = left;
        this.right = right;
    }

    public boolean apply(Runner r){
        return this.left.apply(r) && this.right.apply(r);
    }
}