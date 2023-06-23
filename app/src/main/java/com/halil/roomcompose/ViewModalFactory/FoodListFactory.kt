package com.halil.roomcompose.ViewModalFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.halil.roomcompose.View.FoodListPage
import com.halil.roomcompose.ViewModal.FoodListViewModal


class FoodListFactory(
    var context:Context
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FoodListViewModal(context) as T
}