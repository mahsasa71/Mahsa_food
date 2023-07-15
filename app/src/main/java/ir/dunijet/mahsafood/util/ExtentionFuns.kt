package ir.dunijet.mahsafood.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(str:String){
    Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
}