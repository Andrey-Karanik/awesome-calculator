package com.andreykaranik.awesomecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.andreykaranik.awesomecalculator.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.keyboard.equalButton.setOnClickListener {
            viewModel.calculate()
        }

        binding.keyboard.button0.setOnClickListener {
            appendOnClick("0")
        }
        binding.keyboard.button1.setOnClickListener {
            appendOnClick("1")
        }
        binding.keyboard.button2.setOnClickListener {
            appendOnClick("2")
        }
        binding.keyboard.button3.setOnClickListener {
            appendOnClick("3")
        }
        binding.keyboard.button4.setOnClickListener {
            appendOnClick("4")
        }
        binding.keyboard.button5.setOnClickListener {
            appendOnClick("5")
        }
        binding.keyboard.button6.setOnClickListener {
            appendOnClick("6")
        }
        binding.keyboard.button7.setOnClickListener {
            appendOnClick("7")
        }
        binding.keyboard.button8.setOnClickListener {
            appendOnClick("8")
        }
        binding.keyboard.button9.setOnClickListener {
            appendOnClick("9")
        }
        binding.keyboard.leftBracketButton.setOnClickListener {
            appendOnClick( "(")
        }
        binding.keyboard.rightBracketButton.setOnClickListener {
            appendOnClick( ")")
        }
        binding.keyboard.divideButton.setOnClickListener {
            appendOnClick( "/")
        }
        binding.keyboard.multiplyButton.setOnClickListener {
            appendOnClick("*")
        }
        binding.keyboard.subtractButton.setOnClickListener {
            appendOnClick( "-")
        }
        binding.keyboard.addButton.setOnClickListener {
            appendOnClick("+")
        }
        binding.keyboard.commaButton.setOnClickListener {
            appendOnClick(",")
        }

        binding.keyboard.clearButton.setOnClickListener {
            viewModel.clearAll()
        }

        binding.keyboard.backButton.setOnClickListener {
            viewModel.dropLast()
            viewModel.clearOutputExpression()
        }

        binding.themeSwitch.setOnCheckedChangeListener { _, p1 ->
            if (p1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.inputExpression
                .onEach {
                    binding.inputField.text = it
                }
                .collect()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.outputExpression
                .onEach {
                    binding.outputField.text = it
                }
                .collect()
        }
    }

    private fun appendOnClick(symbol: String) {
        viewModel.append(symbol)
        viewModel.clearOutputExpression()
    }
}