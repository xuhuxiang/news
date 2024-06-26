package com.example.anew.ui.ViewModel

import com.example.anew.ui.Entity.News


sealed class NewsState{
    class Success(val news: News):NewsState()
    class Failure(val error:Throwable):NewsState()
    object Loading:NewsState()
    object Empty:NewsState()
}
