package com.socialevoeding.bap.ui.categories

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.socialevoeding.bap.R
import com.socialevoeding.bap.domain.model.Category

@BindingAdapter("bg_cat")
fun LinearLayout.setBackground(category: Category?) {
    category.let {
        if(category?.id == 1){
            setBackgroundColor(resources.getColor(R.color.pastel_yellow))
        }
        if(category?.id == 2){
            setBackgroundColor(resources.getColor(R.color.pastel_blue))
        }
        if(category?.id == 3){
            setBackgroundColor(resources.getColor(R.color.pastel_green))
        }
        if(category?.id == 4){
            setBackgroundColor(resources.getColor(R.color.pastel_purple))
        }
        if(category?.id == 5){
            setBackgroundColor(resources.getColor(R.color.pastel_red))
        }
        if(category?.id == 6){
            setBackgroundColor(resources.getColor(R.color.pastel_darkblue))
        }
    }
}