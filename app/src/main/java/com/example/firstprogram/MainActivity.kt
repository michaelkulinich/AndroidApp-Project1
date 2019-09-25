package com.example.firstprogram

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.isVisible
import android.util.Log



class MainActivity : AppCompatActivity() {
    private var count: Long = 0;
    fun getStore() = getPreferences(Context.MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.extras?.get("username")
        if (savedInstanceState != null) {
            updateCounter(savedInstanceState.getLong(myCounter_key, 0))
        } else if (getStore().contains(myCounter_key)) {
            updateCounter(getStore().getLong(myCounter_key, 0))
        }
//            count = savedInstanceState.getLong(myCounter_key, 0)
//            myCounter.text = count.toString();


        myButton.setOnClickListener {
            count++;
            myCounter.text = count.toString();
        }
    }


    override fun onPause() {
       super.onPause()
       getStore().edit().putLong(myCounter_key, count).apply()
   }

    private fun updateCounter(count1: Long)
    {
        count = count1;
        myCounter.text = count.toString()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putLong(myCounter_key, count)
        }
        super.onSaveInstanceState(outState)
    }

    companion object{
        private const val myCounter_key = "myCounterKey"
    }


}
