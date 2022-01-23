package raul.imashev.photolist.ui.photoList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import raul.imashev.photolist.TEST_OF_LOADING_DATA_CONST
import raul.imashev.photolist.api.ApiFactory

class PhotoListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val photosList = MutableLiveData<List<String>>()

    init {
        loadData()
    }


    fun loadData() {
        val disposable = ApiFactory.apiService.getPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                photosList.value = it
            }, {
                Log.d(TEST_OF_LOADING_DATA_CONST, "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}