class test {
}

typealias IntPredicate1 = (i:Int) -> Boolean
typealias IntPredicate2 = (i:Int) -> Boolean

fun test(predicate: IntPredicate1) = predicate.invoke(1)

