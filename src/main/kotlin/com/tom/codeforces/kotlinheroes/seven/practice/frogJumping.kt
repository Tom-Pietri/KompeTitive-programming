
fun main() {
    val nbCase = readLine()!!.toInt()
    repeat(nbCase) {
        val (a, b, nbJump) = readLine()!!.split(" ").map { it.toLong() }
        val i = nbJump / 2
        if(nbJump % 2 == 0L) {
            println(a * i - b * i)
        } else {
            println(a + a * i - b * i)
        }
    }
}