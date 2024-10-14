package com.example.chatbot

class Test {
    fun tinhTong(tong: Int, callback: (Int, Boolean) -> Unit) {
        val tong2 = tong + 5
        callback(tong2, true)
    }
}

class Test2 {
    val test = Test()

    fun ketQua() {
        test.tinhTong(12) { tong, kq ->
            println(tong)
            if(kq){
                //
            }
        }
    }
}