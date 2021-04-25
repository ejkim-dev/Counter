package com.example.counter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    /**data binding 으로 데이터들을 다 넘기면 되는데
     * 그 데이터들이 사실상 viewmodel안에 다 들어있음
     * 따라서 viewmodel을 통으로 넘기는게 가장 좋음*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // 액티비티는 화면을 회전할 때마다 초기화가 된다. -> 문제 해결을 위해 viewmodel을 사용해야 함

//        val text = findViewById<TextView>(R.id.counter_text)

        binding.viewModel = viewModel
        // 이 액티비티(or 프래그먼트)의 라이프사이클에 맞춰서 동작하게 설정
        // 액티비티에서 동작하고, 뷰모델에서 값이 변경될 때마다 새로고침 하는 코드
        binding.lifecycleOwner = this

        viewModel.apply {
            // count를 관찰해서 UI에 적용시킴 -> ui 갱신은 여기서 다 해결됨
            /*countLiveData.observe(
                    this@MainActivity, Observer { count ->
                text.text = "$count"
            }
            )*/

        }

    }

    /**
     * 데이터가 파괴될 때 Bundle에다가 저장을 함
     * 그러면 안드로이드 시스템이 가지고 있다가 복원될 때
     * onRestoreInstanceState() 이쪽이나 savedInstanceState
     * 둘 중 하나로 복원을 시켜준다(선택사항)*/
/*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 1. 여기서 저장하고 -> 중요한 데이터를 저장할 수 있음
//        outState.putInt("count", viewModel.count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // 2. 여기서 복원함
//        viewModel.count = savedInstanceState.getInt("count")
    }
*/

}