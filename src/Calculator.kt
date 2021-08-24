import java.util.*
import ExpressionComponent.*
import java.util.regex.Pattern

object Calculator {

    fun calculate(expression: String): Int {
        val components = expression.listOfExpressionComponent()
        return when (components.size) {
            0 -> 0
            1 -> (components.first() as Operand).number
            else -> {
                val componentStack = Stack<ExpressionComponent>()
                val operatorStack = Stack<Operator>()
                components.forEach { component ->
                    when (component) {
                        is Operand -> componentStack.pushOperand(component)
                        is NegativeSign -> componentStack.push(component)
                        is OpenParenthesis -> componentStack.push(component)
                        is CloseParenthesis -> {
                            while (true) {
                                val secondOperand = componentStack.pop()
                                val firstOperand = componentStack.pop()
                                if (firstOperand is OpenParenthesis) {
                                    componentStack.pushOperand(secondOperand as Operand)
                                    break
                                } else {
                                    val operator = operatorStack.pop()
                                    val result = operator.operate(firstOperand as Operand, secondOperand as Operand)
                                    componentStack.pushOperand(result)
                                }
                            }
                        }
                        is Operator -> {
                            if (operatorStack.isEmpty()) {
                                operatorStack.push(component)
                            } else {
                                while (operatorStack.isNotEmpty()
                                    && !component.isPriorTo(operatorStack.peek())) {
                                    val secondOperand = componentStack.pop()
                                    val firstOperand = componentStack.pop()
                                    if (firstOperand is Operand && secondOperand is Operand) {
                                        val operator = operatorStack.pop()
                                        val result = operator.operate(firstOperand, secondOperand)
                                        componentStack.pushOperand(result)
                                    } else {
                                        componentStack.push(firstOperand)
                                        componentStack.push(secondOperand)
                                        break
                                    }
                                }

                                operatorStack.push(component)
                            }
                        }
                    }
                }

                while (operatorStack.isNotEmpty()) {
                    val operator = operatorStack.pop()
                    val secondOperand = componentStack.pop() as Operand
                    val firstOperand = componentStack.pop() as Operand
                    val result = operator.operate(firstOperand, secondOperand)
                    componentStack.pushOperand(result)
                }

                (componentStack.pop() as Operand).number
            }
        }
    }


    private fun String.listOfExpressionComponent(): List<ExpressionComponent> {
        val matchResults = matchResults("[-+*/()]|[0-9]+")
        val list = mutableListOf<ExpressionComponent>()

        matchResults.forEach { result ->
            when (result) {
                "(" -> list.add(OpenParenthesis)
                ")" -> list.add(CloseParenthesis)
                in listOf("+", "-", "*", "/") -> {
                    if (list.lastOrNull() is Operand || list.lastOrNull() is CloseParenthesis) {
                        list.add(Operator.operator(result))
                    } else {
                        list.add(NegativeSign)
                    }
                }
                else -> list.add(Operand(result.toInt()))
            }
        }

        return list.toList()
    }

    private fun Stack<ExpressionComponent>.pushOperand(operand: Operand) {
        if (isNotEmpty() && peek() is NegativeSign) {
            pop()
            push(Operand(-operand.number))
        } else {
            push(operand)
        }
    }
}

