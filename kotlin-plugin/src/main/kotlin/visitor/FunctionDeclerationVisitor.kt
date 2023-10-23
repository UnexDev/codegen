package visitor

import model.FunctionDecleration
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.backend.js.utils.valueArguments
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression
import org.jetbrains.kotlin.ir.util.render
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

class FunctionDeclerationVisitor : IrElementVisitor<Unit, IrPluginContext> {

    override fun visitCall(expression: IrCall, data: IrPluginContext) {
        if (expression.render().startsWith("CALL 'public final fun function")) {
            println("IT IS A CALL")
            if (expression.valueArguments[1] is IrFunctionExpression) {
                val lambda = expression.valueArguments[1] as IrFunctionExpression
                val function = FunctionDecleration(
                    modality = lambda.function.modality,
                    name = expression.valueArguments[0].toString(),
                    visibility = lambda.function.visibility,
                    args = lambda.function.valueParameters,
                    returnType = lambda.function.returnType,
                    body = lambda.function.body!!,
                    ctx = data
                )

                function.prepare()
            }
        } else {
            println("IT IS NOT A CALL")
        }
        super.visitCall(expression, data)
    }

    override fun visitElement(element: IrElement, data: IrPluginContext) {
        if (element is IrCall) {
            visitCall(element, data)
        }
    }
}