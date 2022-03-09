package mx.edu.itm.link.calculatorv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import mx.edu.itm.link.calculatorv2.databinding.ActivityMainBinding
import java.util.function.BinaryOperator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstValue: Double = 0.0
    var operator: Char = '0'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonClear.setOnClickListener {
            binding.editTextCalculations.text.clear()
        }

        binding.buttonDelete.setOnClickListener {
            binding.editTextCalculations.setText(binding.editTextCalculations.text.dropLast(1))
        }

        binding.buttonEquals.setOnClickListener {
            when(operator){
                '÷' -> {
                    binding.editTextCalculations.setText("${firstValue / binding.editTextCalculations.text.toString().toDouble()}")
                }
                '×' -> {
                    binding.editTextCalculations.setText("${firstValue * binding.editTextCalculations.text.toString().toDouble()}")
                }
                '+' -> {
                    binding.editTextCalculations.setText("${firstValue + binding.editTextCalculations.text.toString().toDouble()}")
                }
                '-' -> {
                    binding.editTextCalculations.setText("${firstValue - binding.editTextCalculations.text.toString().toDouble()}")
                }
                else -> binding.editTextCalculations.setText("ERROR")
            }
            operator = '0'
            firstValue = 0.0
        }

        binding.buttonPercentage.setOnClickListener {
            // TODO ejemplo propina 200+10% = 220, 200-10% = 100
            binding.editTextCalculations.setText(percentage(operator))
        }
    }

    fun percentage(operatorToUse: Char) : String {
        when(operatorToUse){
            '+' -> { return "${firstValue + (firstValue*binding.editTextCalculations.text.toString().toDouble()/100)}" }
            '-' -> { return "${firstValue - (firstValue*binding.editTextCalculations.text.toString().toDouble()/100)}" }
            '0' -> { return "${binding.editTextCalculations.text.toString().toDouble()/100}"}
            else -> { return "Error"}
        }
    }

    fun getOperator(view: View){
        val button = view as Button

        operator = button.text.get(0)
/*
        operator = when(button.id){
            binding.buttonDivision.id -> {'÷'}
            binding.buttonMultiplication.id -> {'×'}
            binding.buttonAddition.id -> {'+'}
            binding.buttonSubtraction.id -> {'-'}
            else -> { '0'}
        }
*/
        firstValue = binding.editTextCalculations.text.toString().toDouble()
        binding.editTextCalculations.text.clear()
    }

    fun numberButtonClicked(view: View){
        val button = view as Button

        if (button.id.equals(binding.buttonDecimal.id)){
            if (!binding.editTextCalculations.text.contains('.')){
                binding.editTextCalculations.text.append(button.text)
            }
        }else{
            binding.editTextCalculations.text.append(button.text)
        }
/*
        when(button.id){
            binding.buttonDecimal.id -> {
                if (!binding.editTextCalculations.text.contains('.')){
                    binding.editTextCalculations.text.append(".")
                }
            }
            binding.button0.id -> { binding.editTextCalculations.text.append("0")}
            binding.button1.id -> { binding.editTextCalculations.text.append("1")}
            binding.button2.id -> { binding.editTextCalculations.text.append("2")}
            binding.button3.id -> { binding.editTextCalculations.text.append("3")}
            binding.button4.id -> { binding.editTextCalculations.text.append("4")}
            binding.button5.id -> { binding.editTextCalculations.text.append("5")}
            binding.button6.id -> { binding.editTextCalculations.text.append("6")}
            binding.button7.id -> { binding.editTextCalculations.text.append("7")}
            binding.button8.id -> { binding.editTextCalculations.text.append("8")}
            binding.button9.id -> { binding.editTextCalculations.text.append("9")}
            else -> {
                Toast.makeText(this,"else",Toast.LENGTH_LONG).show()
            }
        }
 */
    }
}