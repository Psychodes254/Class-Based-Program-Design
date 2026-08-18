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

// to represent examples and tests for the json objects
class ExamplesJSON{
  ExamplesJSON(){}
  
  JSON blank = new JSONBlank();
  JSON number = new JSONNumber(8);
  JSON bool = new JSONBool(true);
  JSON string = new JSONString("home");
  
  IList<JSON> jsonValues = 
      new ConsList<JSON>(blank, 
          new ConsList<JSON>(number, 
              new ConsList<JSON>(bool, 
                  new ConsList<JSON>(string,  
                      new MtList<JSON>()))));
  
  JSON jsonList = new JSONList(jsonValues);
  
  IList<Integer> jsonIntegers = 
      new ConsList<Integer>(0, 
          new ConsList<Integer>(8, 
              new ConsList<Integer>(1, 
                  new ConsList<Integer>(4, 
                      new MtList<Integer>()))));
  
  // test JSONToNumber method mapping them to IList
  boolean testJSONToNumber(Tester t) {
    return 
        t.checkExpect(this.jsonValues.map(new JSONToNumber()), jsonIntegers);
  }
  
  // test the sum of all integers in the list 
  boolean testSumjSONList(Tester t) {
    return 
        t.checkExpect(this.jsonList.accept(new JSONToNumber()), 13);
  }
}



