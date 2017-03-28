## Representation

이번에는 정수의 집합을 가지고 문제를 풀 것입니다.
  
예제로 모든 음의 정수의 집합을 나타내는 방법은 무엇일까? 

모든 것을 나열 할 수는 없다. 그렇지만 한가지 방법이 있다면 하나의 숫자를 받는다면 이것이 집합에 속하는지 아닌지 확인하는것이다.

예를 들어서 3에 대해서는 아니오라고 말하고 -1에 대해서는 예라고 말하는 것이다.

정수를 인자로 받고 해당 인자가 집합에 속하는지 아닌지를 판단하여 boolen을 반환하는 함수를 호출한다.

예를 들어 음의 정수를 판단한는 함수는 다음고 같다 (x: Int) => x < 0
 
특성함수로 집합을 표현하고 이 표현에 대한 대표 유형 별칭을 정의한다.


```scala
type Set = Int => Boolean
```

이런 representation을 사용하여 집합안에서 값의 존재를 테스트하는 함수를 정의한다.  


```scala
def contains(s: Set, elem: Int): Boolean = s(elem)
```


### 2.1 Basic Functions on Sets

이제 집합의 기본 함수 정의를 시작하도록 하겠다.  

Define a function singletonSet which creates a singleton set from one integer value: the set represents the set of the one given element. Now that we have a way to create singleton sets, we want to define a function that allow us to build bigger sets from smaller ones.
Define the functions union,intersect, and diff, which takes two sets, and return, respectively, their union, intersection and differences. diff(s, t) returns a set which contains all the elements of the set s that are not in the set t.
Define the function filter which selects only the elements of a set that are accepted by a given predicate p. The filtered elements are returned as a new set.

### 2.2 Queries and Transformations on Sets

In this part, we are interested in functions used to make requests on elements of a set. The first function tests whether a given predicate is true for all elements of the set. This forall function has the following signature:



```scala
def forall(s: Set, p: Int => Boolean): Boolean

```
Note that there is no direct way to find which elements are in a set. contains only allows to know whether a given element is included. Thus, if we wish to do something to all elements of a set, then we have to iterate over all integers, testing each time whether it is included in the set, and if so, to do something with it. Here, we consider that an integer x has the property -1000 <= x <= 1000 in order to limit the search space.

Implement forall using linear recursion. For this, use a helper function nested inforall.
Using forall, implement a function exists which tests whether a set contains at least one element for which the given predicate is true. Note that the functions forall and exists behave like the universal and existential quantifiers of first-order logic.
Finally, write a function map which transforms a given set into another one by applying to each of its elements the given function.
Extra Hints

Be attentive in the video lectures on how to write anonymous functions in Scala.
Sets are represented as functions. Think about what it means for an element to belong to a set, in terms of function evaluation. For example, how do you represent a set that contains all numbers between 1 and 100?
Most of the solutions for this assignment can be written as one-liners. If you have more, you probably need to rethink your solution. In other words, this assignment needs more thinking (whiteboard, pen and paper) than coding ;-).
If you are having some trouble with terminology, have a look at the glossary.