package com.example.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// ViewModel 은 액티비티가 살아있는 동안에만 적용된다.
/** handle -> 자동으로 MainActivity의 Bundle을 저장하는 객체가 넘어옴*/
class MainViewModel(private val handle: SavedStateHandle) : ViewModel(){
    // UI에 적용되는 상태 값들을 전부 VIEWMODEL로 옮겨주면 된다.
    // 앱이 강제 종료가 되었을 때 안전하게 살아나는 코드
    private var count = handle.get<Int>("count") ?: 0 //2. 가져오는 곳
        /** setter를 오버라이드하여(setter 재정의) 더 리액트브하게 만들기
        count 가 바뀔 때 바뀌는 값은 value로 들어옴 */
        set(value) {
            field = value
            countLiveData.value = value
            handle.set("count", value) //1. 저장하는 곳
        }
    val countLiveData = MutableLiveData<Int>()

    fun increaseCount(){
        count++
    }
    fun decreaseCount(){
        count--
    }
}