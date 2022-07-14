package br.com.luisfelipeas5.watchlist.arch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    fun <V> LiveData<V>.postValue(value: V) {
        if (this is MutableLiveData<V>) {
            postValue(value)
        } else {
            throw Throwable(".postValue must be used just by MutableLiveData")
        }
    }

}