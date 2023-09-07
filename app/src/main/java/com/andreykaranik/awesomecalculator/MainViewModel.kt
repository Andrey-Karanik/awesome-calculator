package com.andreykaranik.awesomecalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.objecthunter.exp4j.ExpressionBuilder

class MainViewModel : ViewModel() {

    private val _inputExpression: MutableStateFlow<String> = MutableStateFlow("")
    val inputExpression: StateFlow<String> = _inputExpression.asStateFlow()

    private val _outputExpression: MutableStateFlow<String> = MutableStateFlow("")
    val outputExpression: StateFlow<String> = _outputExpression.asStateFlow()



    fun append(symbol: String) {
        _inputExpression.value += symbol
    }

    fun dropLast() {
        _inputExpression.value.dropLast(1)
    }

    fun clearInputExpression() {
        _inputExpression.value = ""
    }

    fun clearOutputExpression() {
        _outputExpression.value = ""
    }

    fun clearAll() {
        _inputExpression.value = ""
        _outputExpression.value = ""
    }

    fun calculate() {
        try {
            val input = ExpressionBuilder(_inputExpression.value.replace(",", ".")).build()
            val output = input.evaluate()
            _outputExpression.value = output.toString().replace(".", ",")
        } catch (e: Exception) {
            //Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }

}