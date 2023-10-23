import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import kotlin.test.Test
import kotlin.test.assertEquals

val codeStr =
"""
    fun main() {
        function<Unit>("func") { a, b, c ->
            if (a + b == c) {
                println("We did it!")
            }
        }
    }
    fun <T> function(name: String, block: (Int, Int, Int) -> T) {}

""".trimIndent()

@OptIn(ExperimentalCompilerApi::class)
class IRPluginTest {
    @Test
    fun assertResultExitCodeIsOK() {
        val result = compile(SourceFile.kotlin("main.kt", codeStr))
        assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
    }
}

@OptIn(ExperimentalCompilerApi::class)
fun compile(
    sourceFiles: List<SourceFile>,
    plugin: CompilerPluginRegistrar,
): KotlinCompilation.Result {
    return KotlinCompilation().apply {
        sources = sourceFiles
        useIR = true
        compilerPluginRegistrars = listOf(plugin)
        inheritClassPath = true
    }.compile()
}

@OptIn(ExperimentalCompilerApi::class)
fun compile(
    sourceFile: SourceFile,
    plugin: CompilerPluginRegistrar = CodegenCompilerPluginRegistrar(),
): KotlinCompilation.Result {
    return compile(listOf(sourceFile), plugin)
}