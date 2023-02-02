package com.example.calculatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true
    private var afterCalcul = false
    private var Sign = false
    private var op=0



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun numberAction(view: View)
    {


            if (view is Button) {


                if(afterCalcul) {
                    opview.text = ""
                    afterCalcul=false
                }

                if (view.text == ".") {
                    if (canAddDecimal)
                        opview.append(view.text)

                    canAddDecimal = false
                } else
                    opview.append(view.text)

                canAddOperation = true
                afterCalcul=false
            }
    }


    fun opAction(view: View)
    {

        if(view is Button && canAddOperation)
        {
            if(op==1) {
                opview.text = calculs()
                op=0
            }
            op = op + 1
            opview.append(view.text)
            canAddOperation = false
            canAddDecimal = true
            afterCalcul=false

        }
    }


    fun DelAction(view: View)
    {
        opview.text = ""
    }

    fun ResAction(view: View)
    {
        val length = opview.length()
        if(length > 0)
            opview.text = opview.text.subSequence(0, length - 1)
    }

    fun Sign(view: View)
    {
        val displayText = opview.text.toString()
        val pattern = "-?\\d+".toRegex()
        val lastNumberMatch = pattern.findAll(displayText).lastOrNull()
        if (lastNumberMatch != null) {
            val lastNumberIndex = lastNumberMatch.range.first
            val lastNumber = lastNumberMatch.value.toInt()
            val newDisplayText = displayText.substring(0, lastNumberIndex) + -lastNumber
            opview.text = newDisplayText
        }

        /* val displayText = opview.text.toString()
         val lastNumberIndex = displayText.lastIndexOf(Regex("-?\\d+"))
         if (lastNumberIndex != -1) {
             val lastNumber = displayText.substring(lastNumberIndex).toInt()
             val newDisplayText = displayText.substring(0, lastNumberIndex) + -lastNumber
             opview.text = newDisplayText
         }


         if(opview.text[0]=='-')
             return

         else{
             opview.text = "-" + opview.text
             Sign = true
         }*/
    }

    fun EqualsAction(view: View)
    {
        op = 0
        opview.text = calculs()
        afterCalcul=true

    }

    private fun calculs(): String
    {
        val numb = numbs()
        if(numb.isEmpty()) return ""

        val tempsDiv = CalctempsDiv(numb)
        if(tempsDiv.isEmpty()) return ""

        val result = addSous(tempsDiv)


        return result.toString()
    }


/* private fun numbs(): MutableList<Any>
{
    val list = mutableListOf<Any>()
    var neg = false
    var oldnumb = ""
    for(char in opview.text)
    {
        if(opview.text[0]=='-' && !neg) {
            oldnumb += '-'
            neg=true
        }else {


            if (char.isDigit() || char == '.') {
                oldnumb += char

            } else {

                list.add(oldnumb.toFloat())
                oldnumb = ""
                list.add(char)
            }
        }
    }

    if(oldnumb != "") {
        list.add(oldnumb.toFloat())
    }


    return list
}*/


private fun numbs(): MutableList<Any>
{
    val list = mutableListOf<Any>()
    var neg = false
    var oldnumb = ""
    for(char in opview.text)
    {
        if(opview.text[0]=='-' && !neg) {
            oldnumb += '-'
            neg=true
        }else {


            if (char.isDigit() || char == '.') {
                oldnumb += char

            } else {

                if (neg) {
                    list.add(-oldnumb.toFloatOrNull()!! ?: 0f)
                } else {
                    list.add(oldnumb.toFloatOrNull() ?: 0f)
                }
                oldnumb = ""
                list.add(char)
            }
        }
    }

    if(oldnumb != "") {
        if (neg) {
            list.add(-oldnumb.toFloatOrNull()!! )
        } else {
            list.add(oldnumb.toFloatOrNull() ?: 0f)
        }
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
            }
        }

        if(i > indice)
            newList.add(passedList[i])
    }

    return newList
}

}