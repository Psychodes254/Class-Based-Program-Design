import tester.*;

// a json value
interface JSON {
  <R> R accept(IVisitorJSON<R> visitor);
}
 
// no value
class JSONBlank implements JSON {
  public <R> R accept(IVisitorJSON<R> visitor) {
    return visitor.visitJSONBlank(this);
  }
}
 
// a number
class JSONNumber implements JSON {
  int number;
 
  JSONNumber(int number) {
    this.number = number;
  }
  
  public <R> R accept(IVisitorJSON<R> visitor) {
    return visitor.visitJSONNumber(this);
  }
}
 
// a boolean
class JSONBool implements JSON {
  boolean bool;
 
  JSONBool(boolean bool) {
    this.bool = bool;
  }
  
  public <R> R accept(IVisitorJSON<R> visitor) {
    return visitor.visitJSONBool(this);
  }
}
 
// a string
class JSONString implements JSON {
  String str;
 
  JSONString(String str) {
    this.str = str;
  }
  
  public <R> R accept(IVisitorJSON<R> visitor) {
    return visitor.visitJSONString(this);
  }
}

//a list of JSON values
class JSONList implements JSON {
  IList<JSON> values;

  JSONList(IList<JSON> values) {
    this.values = values;
  }
  
  public <R> R accept(IVisitorJSON<R> visitor) {
    return visitor.visitJSONList(this);
  }
}

// a list of JSON pairs
class JSONObject implements JSON {
  IList<Pair<String, JSON>> pair;
 
  JSONObject(IList<Pair<String, JSON>> pair) {
    this.pair = pair;
  }

  public <R> R accept(IVisitorJSON<R> visitor){
    return visitor.visitJSONObject(this);
  }
}
 
// generic pairs
class Pair<X, Y> {
  X x;
  Y y;
 
  Pair(X x, Y y) {
    this.x = x;
    this.y = y;
  }
}

// to represent examples and tests for the json objects
class ExamplesJSON{
  ExamplesJSON(){}
  
  JSON blank = new JSONBlank();
  JSON number = new JSONNumber(8);
  JSON bool = new JSONBool(true);
  JSON string = new JSONString("home");

  JSON blank2 = new JSONBlank();
  JSON number2 = new JSONNumber(20);
  JSON bool2 = new JSONBool(false);
  JSON string2 = new JSONString("Stephen");

  Pair<String, JSON> pair1 = new Pair<String, JSON>("age", number2);
  Pair<String, JSON> pair2 = new Pair<String, JSON>("name", string2);
  Pair<String, JSON> pair3 = new Pair<String, JSON>("active", bool);

  IList<Pair<String, JSON>> pairs =  
    new ConsList<Pair<String, JSON>>(pair1, 
      new ConsList<Pair<String, JSON>>(pair2, 
        new ConsList<Pair<String, JSON>>(pair3,   
          new MtList<Pair<String, JSON>>())));

  JSON object = new JSONObject(pairs);
  
  IList<JSON> jsonValues = 
      new ConsList<JSON>(blank, 
          new ConsList<JSON>(number, 
              new ConsList<JSON>(bool, 
                  new ConsList<JSON>(string,  
                      new MtList<JSON>()))));

  IList<JSON> jsonValues2 = 
      new ConsList<JSON>(blank2, 
          new ConsList<JSON>(number2, 
              new ConsList<JSON>(bool2, 
                  new ConsList<JSON>(string2,  
                      new MtList<JSON>()))));
  
  JSON jsonList = new JSONList(jsonValues);
  JSON jsonList2 = new JSONList(jsonValues2);

  
  IList<Integer> jsonIntegers = 
      new ConsList<Integer>(0, 
          new ConsList<Integer>(8, 
              new ConsList<Integer>(1, 
                  new ConsList<Integer>(4, 
                      new MtList<Integer>()))));

  IList<Integer> jsonIntegers2 = 
      new ConsList<Integer>(0, 
          new ConsList<Integer>(20, 
              new ConsList<Integer>(0, 
                  new ConsList<Integer>(7, 
                      new MtList<Integer>()))));
  
  // test JSONToNumber method mapping them to IList
  boolean testJSONToNumber(Tester t) {
    return 
        t.checkExpect(this.jsonValues.map(new JSONToNumber()), jsonIntegers) &&
        t.checkExpect(this.jsonValues2.map(new JSONToNumber()), jsonIntegers2);
  }
  
  // test the sum of all integers in the list 
  boolean testSumjSONList(Tester t) {
    return 
        t.checkExpect(this.jsonList.accept(new JSONToNumber()), 13) &&
        t.checkExpect(this.jsonList2.accept(new JSONToNumber()), 27);
  }

  // test the finder method in a list
  boolean testFindSolution(Tester t){
    return
    t.checkExpect(this.jsonValues.findSolutionOrElse(new JSONToNumber(), new VisitJSONFind(), 0), 0) &&
    t.checkExpect(this.jsonValues2.findSolutionOrElse(new JSONToNumber(), new VisitJSONFind(), 0), 20);
  }

  // test the sum of items in object class
  boolean testJSONObjectToNumber(Tester t) {
    return t.checkExpect(this.object.accept(new JSONToNumber()), 28);
  }
}