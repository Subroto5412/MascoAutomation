package com.bd.mascogroup.automation.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.ValidationModel
import com.bd.mascogroup.automation.data.remote.ApiServiceRepository
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import java.util.*

abstract class BaseViewModel<N>(
    val dataManager: IDataManager,
    val schedulerProvider: ISchedulerProvider
) : ViewModel() {
        var isLoading = ObservableBoolean(false)

        val compositeDisposable: CompositeDisposable

        private var mNavigator: WeakReference<N>? = null

        val viewModelError: List<ValidationModel>
            get() {
                Collections.sort(mViewModelError) { o1, o2 ->
                    Integer.compare(
                        Integer.valueOf(o1.sortOrder),
                        Integer.valueOf(o2.sortOrder)
                    )
                }
                return mViewModelError
            }

        private val mViewModelError: MutableList<ValidationModel>

        var navigator: N?
            get() = if (mNavigator != null) {
                mNavigator!!.get()
            } else {
                null
            }
            set(navigator) {
                this.mNavigator = WeakReference<N>(navigator)
            }

        init {
            this.compositeDisposable = CompositeDisposable()
            mViewModelError = ArrayList()
        }

        override fun onCleared() {
            compositeDisposable.dispose()
            super.onCleared()
        }

        open fun setIsLoading(isLoading: Boolean) {
            this.isLoading.set(isLoading)
        }

        fun setViewModelError(validationModel: ValidationModel) {
            if (!mViewModelError.contains(validationModel)) {
                mViewModelError.add(validationModel)
            }
        }

        fun removeViewModelError(validationModel: ValidationModel) {
            if (mViewModelError.contains(validationModel)) {
                mViewModelError.remove(validationModel)
            }
        }

}