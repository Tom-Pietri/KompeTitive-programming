/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args : Array<String>) {
//    val input = readLine().map { Character.getNumericValue(it) }
    val (crops ,n, d) = readLine()!!.split(" ").map { it.toInt() }
//    val crops = input.nextInt()
//    val tomatoes = input.nextInt()
//    val delay = input.nextInt()
//    val garden = input.next()
    val garden = readLine()!!
    var produced = garden.filter { Character.getNumericValue(it) <= d }.length

    if(produced >= n) {
        println("YOU_CAN_MAKE_A_SOUP_IN_${d}_DAYS")
    } else {
        println("YOU_CANNOT_MAKE_A_SOUP_IN_${d}_DAYS")

    }
    // Write an answer using println()
    // To debug: System.err.println("Debug messages...");

}