package com.example.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel 은 액티비티가 살아있는 동안에만 적용된다.
class MainViewModel : ViewModel(){
    // UI에 적용되는 상태 값들을 전부 VIEWMODEL로 옮겨주면 된다.
    private var count = 0
        /** setter를 오버라이드하여(setter 재정의) 더 리액트브하게 만들기
        count 가 바뀔 때 바뀌는 값은 value로 들어옴 */
        set(value) {
            field = value
            countLiveData.value = value
        }
    val countLiveData = MutableLiveData<Int>()

    fun increaseCount(){
        count++
    }
    fun decreaseCount(){
        count--
    }
}