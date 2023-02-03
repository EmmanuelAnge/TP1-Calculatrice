package com.example.calculatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var afterCalcul = false
<<<<<<< HEAD
    private var op = 0
    var verif = ""
    private var SiMoins=false
=======
<<<<<<< HEAD
    private var op = 0
    var verif = ""
    private var SiMoins=false
=======
    private var op=0
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun numberAction(view: View) {

        if (view is Button) {

<<<<<<< HEAD
            if (afterCalcul) {
                opview.text = ""
                afterCalcul = false
=======
<<<<<<< HEAD
            if (afterCalcul) {
                opview.text = ""
                afterCalcul = false
=======
            if (view is Button) {


                if(afterCalcul) {
                    opview.text = ""
                    afterCalcul=false
                }
                    opview.append(view.text)

                canAddOperation = true
                afterCalcul=false
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
            }
            opview.append(view.text)

            canAddOperation = true
            afterCalcul = false
        }
    }


    fun opAction(view: View) {

        if (view is Button && canAddOperation) {
            if (view.text== "-")
                SiMoins=true

            if (op == 1) {
                opview.text = calculs()
                op = 0
            }
            op = op + 1
            opview.append(view.text)
            canAddOperation = false
<<<<<<< HEAD
            afterCalcul = false
=======
<<<<<<< HEAD
            afterCalcul = false
=======
            afterCalcul=false
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde

        }
    }


    fun DelAction(view: View) {
        opview.text = ""
        if (SiMoins){}

    }


<<<<<<< HEAD
    fun ResAction(view: View) {

            val length = opview.length()
=======
<<<<<<< HEAD
    fun ResAction(view: View) {

            val length = opview.length()

>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
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
<<<<<<< HEAD
        }


    fun Sign(view: View) {
        if ( canAddOperation) {
            val displayText = opview.text.toString()
            val pattern = "\\d+".toRegex()
            val lastNumberMatch = pattern.findAll(displayText).lastOrNull()
            if (lastNumberMatch != null) {
                val lastNumberIndex = lastNumberMatch.range.first
                val lastNumber = lastNumberMatch.value.toInt()
                val newDisplayText: String
                if (lastNumberIndex != 0 && displayText[lastNumberIndex-1] == '-') {
                    newDisplayText = displayText.substring(0, lastNumberIndex-1) + lastNumber
                } else {
                    newDisplayText = displayText.substring(0, lastNumberIndex) + "-" + lastNumber
                }
                opview.text = newDisplayText

            }
        }
    }




=======
        }


    fun Sign(view: View) {
        if (verif != opview.text.toString() && canAddOperation) {
            val displayText = opview.text.toString()
            println(displayText.length)
            val pattern = "\\d+".toRegex()
            val lastNumberMatch = pattern.findAll(displayText).lastOrNull()
            if (lastNumberMatch != null) {
                val lastNumberIndex = lastNumberMatch.range.first
                val lastNumber = lastNumberMatch.value.toInt()*(-1)
                val newDisplayText = displayText.substring(0, lastNumberIndex) + lastNumber
                opview.text = newDisplayText
                verif = newDisplayText
            }
        }
=======
    fun Sign(view: View)
    {
        val displayText = opview.text.toString()
        val pattern = "\\d+".toRegex()
        val lastNumberMatch = pattern.findAll(displayText).lastOrNull()
        if (lastNumberMatch != null) {
            val lastNumberIndex = lastNumberMatch.range.first
            val lastNumber = lastNumberMatch.value.toInt()
            val newDisplayText = displayText.substring(0, lastNumberIndex) + -lastNumber
            opview.text = newDisplayText
        }


>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
    }


>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
    fun EqualsAction(view: View) {
        op = 0
        opview.text = calculs()
        afterCalcul = true

    }

    private fun calculs(): String {
        val numb = numbs()
        if (numb.isEmpty()) return ""

        val result = Calculate(numb)

        return result.toString()
    }


<<<<<<< HEAD
=======
<<<<<<< HEAD
=======


>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
    private fun numbs(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentNumber = ""
        var isNegative = false

        for (char in opview.text) {
            if (char == '-' && currentNumber.isEmpty()) {
                isNegative = true
<<<<<<< HEAD
            } else if (char.isDigit()) {
=======
<<<<<<< HEAD
            } else if (char.isDigit()) {
=======
            } else if (char.isDigit() ) {
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
                currentNumber += char
            } else {
                if (isNegative) {
                    currentNumber = "-$currentNumber"
                    isNegative = false
                }

                list.add(currentNumber.toFloat())
                list.add(char)
                currentNumber = ""
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
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






private fun addSous(passedList: MutableList<Any>): Float
{
    var result = passedList[0] as Float

    for(i in passedList.indices)
    {
        if(passedList[i] is Char && i != passedList.lastIndex)
        {
            val op= passedList[i]
            val nextnumb = passedList[i + 1] as Float
            if (op == '+')
                result += nextnumb
            if (op == '-')
                result -= nextnumb
        }
    }


    return result
}

private fun CalctempsDiv(passedList: MutableList<Any>): MutableList<Any>
{
    var list = passedList
    while (list.contains('*') || list.contains('/') || list.contains('%'))
    {
        list = calcDiv(list)
    }
    return list
}

private fun calcDiv(passedList: MutableList<Any>): MutableList<Any>
{
    val newList = mutableListOf<Any>()
    var indice = passedList.size

    for(i in passedList.indices)
    {
        if(passedList[i] is Char && i != passedList.lastIndex && i < indice)
        {
            val op = passedList[i]
            val prenumb = passedList[i - 1] as Float
            val nextnumb = passedList[i + 1] as Float
            when(op)
            {
                '*' ->
                {
                    newList.add(prenumb  * nextnumb)
                    indice = i + 1
                }
                '%' ->
                {
                    newList.add(prenumb  % nextnumb)
                    indice = i + 1
                }
                '/' ->
                {
                    newList.add(prenumb  / nextnumb)
                    indice = i + 1
                }
                else ->
                {
                    newList.add(prenumb )
                    newList.add(op)
                }
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
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


<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
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


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val newaff: CharSequence? = opview.text

        outState.putCharSequence("resultat", newaff)
<<<<<<< HEAD
=======
=======
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val newaff:CharSequence?=opview.text

        outState.putCharSequence("resultat",newaff)
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
        val resultatCharSeq: CharSequence? = savedInstanceState.getCharSequence(
            "resultat",
            0.toString()
        )
        opview.text = resultatCharSeq.toString()
    }

}
<<<<<<< HEAD
=======
=======
        val resultatCharSeq:CharSequence?=savedInstanceState.getCharSequence("resultat",
            0.toString()
        )
        opview.text=resultatCharSeq.toString()
    }

}
>>>>>>> 19c82249e0a06713c92b83f36dd9bf91668490f5
>>>>>>> a88f8291d1df0d771d17f3f66f2c5c4e750c3dde
