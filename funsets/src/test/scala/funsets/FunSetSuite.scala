package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")

  }
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
//    assert(1 + 2 === 4)
    assert(1 + 2 === 3)
  }
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("union every Set and check") {
    new TestSets {
      val s = union(s1, s2)
      val ss = union(s, s3)

      assert(contains(ss, 1), "Union 1")
      assert(contains(ss, 2), "Union 2")
      assert(contains(ss, 3), "Union 3")
    }
  }

  test("intersect test") {
    new TestSets {
      val ss1 = union(s1, s2)
      val ss2 = union(s2, s3)
      val s = intersect(ss1, ss2)

      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 1), "Intersect 1")
    }
  }

  test("diff test") {
    new TestSets {
      val ss1 = union(s1, s2)
      val s = diff(ss1, s2)

      assert(contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
    }
  }

  test("filter test") {
    new TestSets {
      val s4 = singletonSet(4)
      val s5 = singletonSet(5)

      val s = union(union(union(union(s1,s2), s3), s4), s5)

      val sResult = filter(s, x => if(x%2 == 0) true else false)
      assert(contains(sResult, 2), "filter 2")
      assert(!contains(sResult, 3), "filter 3")
      assert(contains(sResult, 4), "filter 4")
      assert(!contains(sResult, 5), "filter 5")
    }
  }

  test("exists test") {
    new TestSets {
      val s400 = singletonSet(400)
      val s100 = singletonSet(-100)

      val s = union(union(union(union(s1,s2), s3), s400), s100)

      assert(exists(s, x => if(x==1) true else false), "exists 1")
      assert(exists(s, x => if(x==2) true else false), "exists 2")
      assert(exists(s, x => if(x==3) true else false), "exists 3")
      assert(exists(s, x => if(x==400) true else false), "exists 400")
      assert(exists(s, x => if(x== -100) true else false), "exists -100")
      assert(!exists(s, x => if(x== -999) true else false), "exists -999")
    }
  }

  test("exists test2") {
    new TestSets {
      val s4 = singletonSet(4)
      val s5 = singletonSet(5)
      val s7 = singletonSet(7)
      val s1000 = singletonSet(1000)

      val s = union(union(union(union(union(s1,s3), s4), s5), s7), s1000)

      assert(exists(s, x => if(x==1) true else false), "exists 1")
      assert(exists(s, x => if(x==3) true else false), "exists 3")
      assert(exists(s, x => if(x==4) true else false), "exists 4")
      assert(exists(s, x => if(x==5) true else false), "exists 5")
      assert(exists(s, x => if(x==7) true else false), "exists 7")
      assert(exists(s, x => if(x==1000) true else false), "exists 1000")
    }
  }



  test("map test") {
    new TestSets {
      val s400 = singletonSet(400)
      val s100 = singletonSet(-100)

      val s = union(union(union(union(s1,s2), s3), s400), s100)
      printSet(s)
      val ss = map(s, x => x + 100)
      printSet(ss)
    }
  }

  test("test -1") {
    //    {1,3,4,5,7,1000}
    val s = union(singletonSet(1), union(singletonSet(3), union(singletonSet(4),
      union(singletonSet(5), union(singletonSet(7), singletonSet(1000))))))
    assert(contains(s, 1), "1")
    assert(contains(s, 3), "3")
    assert(contains(s, 4), "4")
    assert(contains(s, 5), "5")
    assert(contains(s, 7), "7")
    assert(contains(s, 1000), "1000")
    val d = map(s, x => x-1)
    assert(contains(d, 0), "0")
    assert(contains(d, 2), "2")
    assert(contains(d, 3), "3")
    assert(contains(d, 4), "4")
    assert(contains(d, 6), "6")
    assert(contains(d, 999), "999")
  }

}
