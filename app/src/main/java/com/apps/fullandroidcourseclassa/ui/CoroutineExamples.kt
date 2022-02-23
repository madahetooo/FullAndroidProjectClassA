package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.R
import kotlinx.android.synthetic.main.activity_coroutine_examples.*
import kotlinx.coroutines.*

class CoroutineExamples : AppCompatActivity() {
    val TAG ="CoroutineExamples"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_examples)

//        GlobalScope.launch {
//            delay(5000L)
//            Log.d(TAG,"Coroutine says hello from Thread : ${Thread.currentThread().name}")
//        }
//        Log.d(TAG, "hello from Thread : ${Thread.currentThread().name}")


        //OPERATION THREAD (BACKGROUND TASKS)
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG,"Starting coroutine in thread ${Thread.currentThread().name} ") //IO THREAD

           val updatedTextInDesign = doNetworkCall()

            //CONTEXT SWITCH TO "MAIN" THREAD
            withContext(Dispatchers.Main){
                tvDummy.text = updatedTextInDesign
                Log.d(TAG,"Setting coroutine in thread ${Thread.currentThread().name} ")
            }

        }

    }

    suspend fun doNetworkCall():String{
        delay(4000L)
        return "This is the return String"
    }
}