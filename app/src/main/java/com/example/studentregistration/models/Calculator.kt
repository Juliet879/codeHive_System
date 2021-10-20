package com.example.studentregistration.models

class Calculator {
    fun addition(num1:Int,num2:Int):Int{
        return num1 + num2
    }

    fun subtraction(num1:Int,num2:Int):Int{
        return num1 - num2
    }

    fun returnSomething( age:Int):String{
        if (age>=18){
            return "adult"
        }
        else {
            return "Minor"
        }
    }


}