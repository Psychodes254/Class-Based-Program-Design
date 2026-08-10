// To compute a three-way comparison between two Runners
interface IRunnerComparator {
  int compare(Runner r1, Runner r2);
}

class CompareByTime implements IRunnerComparator{
    CompareByTime(){}

    public int compare(Runner r1, Runner r2){
        return r1.time - r2.time;
    }
}

class CompareByAge implements IRunnerComparator{
    CompareByAge(){}

    public int compare(Runner r1, Runner r2){
        return r1.age - r2.age;
    }
}

class CompareByPosition implements IRunnerComparator{
    CompareByPosition(){}

    public int compare(Runner r1, Runner r2){
        return r1.position - r2.position;
    }
}

class ReverseComparator implements IRunnerComparator{
    IRunnerComparator comparator;

    ReverseComparator(IRunnerComparator comparator){
        this.comparator = comparator;
    }

    public int compare(Runner r1, Runner r2){
        return this.comparator.compare(r2, r1);
    }
}

class CompareByName implements IRunnerComparator{
    CompareByName(){}

    public int compare(Runner r1, Runner r2){
        return r1.name.compareTo(r2.name);
    }
}