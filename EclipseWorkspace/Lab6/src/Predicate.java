// Represents functions of signature A -> R, for some argument type A and
// result type R
interface IFunc<A, R> {
  R apply(A input);
}

// Represents functions of two type signature A1 and A2, 
// mapping to type R
interface IFunc2<A1, A2, R> {
  R apply(A1 arg1, A2 arg2);
}

// to represent visitor interface for JSON
interface IVisitorJSON<R>{
  R visitJSONBlank(JSONBlank blank);
  R visitJSONNumber(JSONNumber number);
  R visitJSONBool(JSONBool bool);
  R visitJSONString(JSONString string);
  R visitJSONList(JSONList list);
}

// to represent a class that sums integers
class SumIntegers implements IFunc2<Integer, Integer, Integer>{
  public Integer apply(Integer i, Integer sum) {
    return i + sum;
  }
}

//adds the integer-value of a JSON element to a running sum
class SumJSON implements IFunc2<JSON, Integer, Integer> {
  public Integer apply(JSON j, Integer sum) {
   return j.accept(new JSONToNumber()) + sum;
  }
}

class JSONToNumber implements IVisitorJSON<Integer>, IFunc<JSON, Integer>{
  public Integer apply(JSON j) {
    return j.accept(this);
  }
  
  public Integer visitJSONBlank(JSONBlank blank) {
    return 0;
  }
  
  public Integer visitJSONNumber(JSONNumber number) {
    return number.number;
  }
  
  public Integer visitJSONBool(JSONBool bool) {
    if (bool.bool == true) {
      return 1;
    }
    else {
      return 0;
    }
  }
  
  public Integer visitJSONString(JSONString string) {
    return string.str.length();
  }
  
  public Integer visitJSONList(JSONList list) {
    return list.values.foldr(new SumJSON(), 0);
  }
}