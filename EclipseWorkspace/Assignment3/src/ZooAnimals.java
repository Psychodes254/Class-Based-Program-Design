// to represent interface IZooAnimal
interface IZooAnimal{}

// to represent abstract class AZooAnimal
abstract class AZooAnimal implements IZooAnimal{
  String name;
  int weight;
  
  AZooAnimal(String name, int weight){
    this.name = name;
    this.weight = weight;
  }
}

// to represent class Lion
class Lion extends AZooAnimal{
  int meat;
  
  Lion(String name, int weight, int meat){
    super(name, weight);
    this.meat = meat;
  }
}

// to represent class Snake
class Snake extends AZooAnimal{
  int length;
  
  Snake(String name, int weight, int length){
    super(name, weight);
    this.length = length;
    
  }
}

// to represent class Monkey
class Monkey extends AZooAnimal{
  String food;
  
  Monkey(String name, int weight, String food){
    super(name, weight);
    this.food = food;
  }
}

// to represent class ExamplesIZooAnimal
class ExamplesZooAnimals{
  IZooAnimal leo = new Lion("Leo", 300, 5);
  IZooAnimal boa = new Snake("Ana", 150, 5);
  IZooAnimal george = new Monkey("George", 150, "kiwi");
}
