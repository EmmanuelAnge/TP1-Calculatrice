package com.example.calculatrice

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var afterCalcul = false
    private var SiMoins = false
    private var op = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        opview.setOnClickListener{
            copyTest(opview.text.toString())
            true
        }
    }




    fun numberAction(view: View) {

        if (view is Button) {

            if (afterCalcul) {
                opview.text = ""
                afterCalcul = false
            }
            opview.append(view.text)

            canAddOperation = true
            afterCalcul = false
        }
    }


    fun opAction(view: View) {

        if (view is Button && canAddOperation) {
            if (view.text == "-")
                SiMoins = true

            if (op == 1) {
                if (opview.text.contains(".")||opview.text.contains("/")){
                    opview.text = calculs()
                    op = 0
                }else{
                    opview.text = calculsEntier()
                    op = 0
                }

            }
            op = op + 1
            opview.append(view.text)
            canAddOperation = false
            afterCalcul = false

        }
    }


    fun DelAction(view: View) {
        opview.text = ""

    }


    fun ResAction(view: View) {

        val length = opview.length()
        if (length >= 2) {
            val value = opview.text[length - 2].toString()
            if (value == "-") {
                val final = opview.text[length - 1].toString()
                if (final.toIntOrNull() != null) {
                    opview.text = opview.text.subSequence(0, length - 2)
                }
            } else {
                opview.text = opview.text.subSequence(0, length - 1)
            }
        } else if (length == 1) {
            opview.text = opview.text.subSequence(0, length - 1)
        }
    }



    fun Sign(view: View) {
        if (canAddOperation) {
            val displayText = opview.text.toString()
            val pattern = "\\d+(\\.\\d+)?".toRegex()
            val lastNumberMatch = pattern.findAll(displayText).lastOrNull()
            if (lastNumberMatch != null) {
                val lastNumberIndex = lastNumberMatch.range.first
                val lastNumber = lastNumberMatch.value.toInt()

                val newDisplayText: String
                try{
                    if (lastNumberIndex != 0 && displayText[lastNumberIndex-1] == '-') {
                        newDisplayText = displayText.substring(0, lastNumberIndex-1) + lastNumber
                    } else {
                        newDisplayText = displayText.substring(0, lastNumberIndex) + "-" + lastNumber
                    }
                    opview.text = newDisplayText
                } catch(e: Exception) {
                    print(e.message)
                }


            }
        }
    }


    fun EqualsAction(view: View) {

        if (opview.text.endsWith("+")||opview.text.endsWith("/")
            ||opview.text.endsWith("%")||opview.text.endsWith("*")
            ||opview.text.endsWith("-")){
            opview.text=opview.text
        }else{
            op = 0
        if (opview.text.contains(".")|| opview.text.contains("/")){
            opview.text = calculs()
        }else{
            opview.text = calculsEntier()
        }

        afterCalcul = true

    }
    }

    private fun calculsEntier(): String {
        val numb = numbs()
        if (numb.isEmpty()) return ""
        val result = CalculateEntier(numb)

        return result.toString()
    }

    private fun calculs(): String {
        val numb = numbs()
        if (numb.isEmpty()) return ""
        val result = Calculate(numb)

        return result.toString()
    }

    private fun numbs(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentNumber = ""
        var isNegative = false

        for (char in opview.text) {
            if (char == '-' && currentNumber.isEmpty()) {
                isNegative = true

            } else if (char.isDigit() || char=='.') {

                currentNumber += char
            } else {
                if (isNegative) {
                    currentNumber = "-$currentNumber"
                    isNegative = false
                }

                list.add(currentNumber.toFloat())
                list.add(char)
                currentNumber = ""
            }
        }

        if (isNegative) {
            currentNumber = "-$currentNumber"
        }

        if (currentNumber.isNotEmpty()) {
            list.add(currentNumber.toFloat())
        }

        return list
    }


    private fun CalculateEntier(passedList: MutableList<Any>): Int {
        var result = (passedList[0] as Float).toInt()

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val op = passedList[i]
                val nextnumb = (passedList[i + 1] as Float).toInt()
                if (op == '+')
                    result += nextnumb
                if (op == '-')
                    result -= nextnumb
                if (op == '*')
                    result *= nextnumb

                if (op == '%')
                    result = result % nextnumb
            }
        }
        return result
    }



    private fun Calculate(passedList: MutableList<Any>): Double {
        var result = (passedList[0] as Float).toDouble()

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val op = passedList[i]
                val nextnumb = (passedList[i + 1] as Float).toDouble()
                if (op == '+')
                    result += nextnumb
                if (op == '-')
                    result -= nextnumb
                if (op == '*')
                    result *= nextnumb
                if (op == '/') {
                    if (nextnumb == 0.0) {
                        println("Division par zéro")
                        return Double.NaN
                    } else
                        result /= nextnumb
                }
                if (op == '%')
                    result = result.toInt().toDouble() % nextnumb.toInt().toDouble()
            }
        }
        return result
    }

    private fun copyTest(text : String)
    {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("tvInput", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this,"copié",Toast.LENGTH_SHORT).show()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val newaff: CharSequence? = opview.text

        outState.putCharSequence("resultat", newaff)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val resultatCharSeq: CharSequence? = savedInstanceState.getCharSequence(
            "resultat",
            0.toString()
        )
        opview.text = resultatCharSeq.toString()
    }

}



