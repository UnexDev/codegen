package model

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.builders.declarations.buildFun
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name
import print

data class FunctionDecleration(
    val modality: Modality,
    val name: String,
    val visibility: DescriptorVisibility,
    val args: List<IrValueParameter>,
    val returnType: IrType,
    val body: IrBody,
    val ctx: IrPluginContext
) {
    fun prepare() {
        val newFun = ctx.irFactory.buildFun {
            name = Name.identifier(this@FunctionDecleration.name)
            visibility = this@FunctionDecleration.visibility
            modality = this@FunctionDecleration.modality
            returnType = this@FunctionDecleration.returnType
        }

        newFun.body = body
        newFun.valueParameters = args

        newFun.print()
    }
}