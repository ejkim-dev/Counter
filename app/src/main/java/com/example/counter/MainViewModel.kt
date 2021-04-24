package com.example.counter

import androidx.lifecycle.ViewModel

// ViewModel 은 액티비티가 살아있는 동안에만 적용된다.
class MainViewModel : ViewModel(){
    // UI에 적용되는 상태 값들을 전부 VIEWMODEL로 옮겨주면 된다.
    var count = 0
}