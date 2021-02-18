package com.udacity.shoestore.viewModels

import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val shoesList = mutableListOf<Shoe>()

    private val _shoeList = MutableLiveData<List<Shoe>>()

    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = shoesList
    }

    fun addShoe(shoe: Shoe) {
        shoesList.add(shoe)
    }
}