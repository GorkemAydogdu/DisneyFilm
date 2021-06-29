package com.h5190063_gorkemaydogdu_but.ui.disneylist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponseItem
import com.h5190063_gorkemaydogdu_but.data.repository.DisneyRepository
import com.h5190063_gorkemaydogdu_but.util.ResourceStatus
import kotlinx.coroutines.launch

class DisneyViewModel : ViewModel() {
    private val disneyRepository: DisneyRepository = DisneyRepository()

    init {
        filmleriGetir()
    }
    /*var filmlerLiveData: MutableLiveData<DisneyResponse>? = null
    var error :    MutableLiveData<Throwable>? = null
    var loading :    MutableLiveData<Boolean>? = null*/

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var filmlerLiveData = MutableLiveData<List<DisneyResponseItem>>()
    var error = MutableLiveData<Throwable>()

    fun filmleriGetir() = viewModelScope.launch {

        disneyRepository.filmleriGetir()
                .asLiveData(viewModelScope.coroutineContext).observeForever {
                    when (it.status) {
                        ResourceStatus.LOADING -> {
                            loading?.postValue(true)
                        }
                        ResourceStatus.SUCCESS -> {
                            filmlerLiveData.postValue(it.data!!)
                            loading?.postValue(false)
                        }
                        ResourceStatus.ERROR -> {
                            Log.e("ERROR", "${it.throwable}")
                            error?.postValue(it.throwable!!)
                            loading?.postValue(false)
                        }
                    }
                }
    }
}

