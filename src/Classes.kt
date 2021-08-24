sealed class ExpressionComponent {
    class Operand(val number: Int) : ExpressionComponent() {
        override fun toString() = number.toString()
    }

    abstract class Operator : ExpressionComponent() {
        companion object {
            fun operator(operator: String) =
                when (operator) {
                    "+" -> Addition()
                    "-" -> Subtraction()
                    "*" -> Multiplication()
                    "/" -> Division()
                    else -> throw NotOperatorException()
                }
        }

        abstract fun operate(firstOperand: Operand, secondOperand: Operand): Operand
        abstract fun isPriorTo(other: Operator): Boolean
    }

    object OpenParenthesis : ExpressionComponent() {
        override fun toString() = "("
    }
    object CloseParenthesis : ExpressionComponent() {
        override fun toString() = ")"
    }

    object NegativeSign : ExpressionComponent() {
        override fun toString() = "(-)"
    }
}

class Addition : ExpressionComponent.Operator() {
    override fun operate(firstOperand: Operand, secondOperand: Operand) =
        Operand(firstOperand.number + secondOperand.number)

    override fun isPriorTo(other: Operator) = false

    override fun toString() = "+"
}

class Subtraction : ExpressionComponent.Operator() {
    override fun operate(firstOperand: Operand, secondOperand: Operand) =
        Operand(firstOperand.number - secondOperand.number)

    override fun isPriorTo(other: Operator) = false

    override fun toString() = "-"
}

class Multiplication : ExpressionComponent.Operator() {
    override fun operate(firstOperand: Operand, secondOperand: Operand) =
        Operand(firstOperand.number * secondOperand.number)

    override fun isPriorTo(other: Operator) =
        when (other) {
            is Addition, is Subtraction -> true
            else -> false
        }

    override fun toString() = "*"
}

class Division : ExpressionComponent.Operator() {
    override fun operate(firstOperand: Operand, secondOperand: Operand) =
        Operand(firstOperand.number / secondOperand.number)

    override fun isPriorTo(other: Operator) =
        when (other) {
            is Addition, is Subtraction -> true
            else -> false
        }

    override fun toString() = "/"
}

class NotOperatorException : Exception()