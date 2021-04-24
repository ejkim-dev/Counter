package com.example.counter

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 액티비티는 화면을 회전할 때마다 초기화가 된다. -> 문제 해결을 위해 viewmodel을 사용해야 함
        Log.d(TAG, "onCreate: ")

        val text = findViewById<TextView>(R.id.counter_text)

        text.text = "${ viewModel.count }"

        findViewById<Button>(R.id.add_button).setOnClickListener {
            viewModel.count++
            text.text = "${viewModel.count}"
        }
        findViewById<Button>(R.id.sub_button).setOnClickListener {
            viewModel.count--
            text.text = "${viewModel.count}"
        }

        // 안드로이드 Q 부터 가능한 콜백함수
        // kotlin에서는 object라는 키워드로 익명클래스를 구현하는데, 자바에서 new와 비슷한 역할을 함
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(TAG, "onActivityCreated: ")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG, "onActivityStarted: ")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG, "onActivityResumed: ")
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG, "onActivityPaused: ")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG, "onActivityStopped: ")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d(TAG, "onActivitySaveInstanceState: ")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(TAG, "onActivityDestroyed: ")
            }

        })

    }

    /**
     * 데이터가 파괴될 때 Bundle에다가 저장을 함
     * 그러면 안드로이드 시스템이 가지고 있다가 복원될 때
     * onRestoreInstanceState() 이쪽이나 savedInstanceState
     * 둘 중 하나로 복원을 시켜준다(선택사항)*/
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 1. 여기서 저장하고 -> 중요한 데이터를 저장할 수 있음
        outState.putInt("count", viewModel.count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // 2. 여기서 복원함
        viewModel.count = savedInstanceState.getInt("count")
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}