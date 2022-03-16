package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityCloudMessagingAppBinding
import com.apps.fullandroidcourseclassa.databinding.ActivityCoroutineExamplesBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineExamples : AppCompatActivity() {
    val TAG = "CoroutineExamples"
    private lateinit var binding: ActivityCoroutineExamplesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineExamplesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        GlobalScope.async(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }
                Log.d(TAG,"Answer 1 is ${answer1.await()}")
                Log.d(TAG,"Answer 2 is ${answer2.await()}")
            }
            Log.d(TAG,"Request took  $time  ms")
        }
        runBlocking {
            launch {

            }
        }
    }

    suspend fun networkCall1():String{
        delay(3000L)
        return "Answer 1"
    }
    suspend fun networkCall2():String{
        delay(3000L)
        return "Answer 2"
    }
}
//var answer1 :String? =null
//            var answer2 :String? =null
//            val job1 = launch {
//                answer1 = networkCall1()
//            }
//            val job2 = launch {
//                answer2 = networkCall2()
//            }
//            job1.join()
//            job2.join()
//            Log.d(TAG,"Answer 1 is $answer1")
//            Log.d(TAG,"Answer 2 is $answer2")