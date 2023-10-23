import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import visitor.FunctionDeclerationVisitor

class CodegenIRGenerationExtention(val messageCollector: MessageCollector, private val enabled: Boolean = true) : IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        moduleFragment.accept(FunctionDeclerationVisitor(), pluginContext)
    }
}