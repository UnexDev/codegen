import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.util.dump

fun IrElement.print() {
    print("\n".repeat(5))
    println(dump())
    print("\n".repeat(5))
}