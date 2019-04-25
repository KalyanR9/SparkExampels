var x = (x:Int) => x+1

println(x(10))

val f = (_: Int) + (_: Int)

println(f(2,3))

def sum(a: Int, b: Int, c: Int) = a + b + c
val a = sum _
println(a(1,2,3))

val b = sum(1, _: Int, 3)
val s = 10
println(s)

val someNumbers = List(-11, -10, -5, 0, 5, 10)
var value = 0
someNumbers.foreach(value+= _)
value

def printMoreValues(y:Int) = (x:Int) => y+x
printMoreValues(10)(12)

