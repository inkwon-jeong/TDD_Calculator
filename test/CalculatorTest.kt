import kotlin.test.assertEquals

fun main() {
    noExpression()
    numberExpression()
    additionExpression()
    subtractionExpression()
    multiplicationExpression()
    divisionExpression()
    multiAdditionExpression()
    multiSubtractionExpression()
    multiMultiplicationExpression()
    multiDivisionExpression()
    differentOperationExpression()
//    parenthesisExpression()
//    noSpaceExpression()
}

fun noExpression() {
    val expression1 = ""
    val result1 = Calculator.calculate(expression1)
    assertEquals(0, result1)

    val expression2 = " "
    val result2 = Calculator.calculate(expression2)
    assertEquals(0, result2)

    val expression3 = "\n\t"
    val result3 = Calculator.calculate(expression3)
    assertEquals(0, result3)
}

fun numberExpression() {
    val expression1 = "1"
    val result1 = Calculator.calculate(expression1)
    assertEquals(1, result1)

    val expression2 = "-1"
    val result2 = Calculator.calculate(expression2)
    assertEquals(-1, result2)
}

fun additionExpression() {
    val expression1 = "1 + 2"
    val result1 = Calculator.calculate(expression1)
    assertEquals(3, result1)

    val expression2 = "-21 + 12"
    val result2 = Calculator.calculate(expression2)
    assertEquals(-9, result2)
}

fun subtractionExpression() {
    val expression = "21 - 11"
    val result = Calculator.calculate(expression)
    assertEquals(10, result)
}

fun multiplicationExpression() {
    val expression = "21 * 12"
    val result = Calculator.calculate(expression)
    assertEquals(252, result)
}

fun divisionExpression() {
    val expression = "21 / 7"
    val result = Calculator.calculate(expression)
    assertEquals(3, result)
}

fun multiAdditionExpression() {
    val expression = "-1 + 22 + -5 + 4"
    val result = Calculator.calculate(expression)
    assertEquals(20, result)
}

fun multiSubtractionExpression() {
    val expression = "-1 - 22 - -5 - 4"
    val result = Calculator.calculate(expression)
    assertEquals(-22, result)
}

fun multiMultiplicationExpression() {
    val expression = "-1 * 22 * -5 * 4"
    val result = Calculator.calculate(expression)
    assertEquals(440, result)
}

fun multiDivisionExpression() {
    val expression = "100 / -2 / -5 / 2"
    val result = Calculator.calculate(expression)
    assertEquals(5, result)
}

fun differentOperationExpression() {
    val expression = "10 - -5 * -2 - 20 / 10"
    val result = Calculator.calculate(expression)
    assertEquals(-2, result)
}

fun parenthesisExpression() {
    val expression = "( 10 + 2 ) + -4 * ( ( 1 + 2 ) / 3 - 10 )"
    val result = Calculator.calculate(expression)
    assertEquals(48, result)
}

fun noSpaceExpression() {
    val expression = "-(10+2)+-4*((1+2)/3-10)"
    val result = Calculator.calculate(expression)
    assertEquals(24, result)
}