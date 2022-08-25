package com.example.mygithubtask.common

import androidx.fragment.app.Fragment
import com.example.mygithubtask.R
import com.example.mygithubtask.presentation.users_detail_screen.BACK_STACK

fun Fragment.addFragment(fragment: Fragment) {
    parentFragmentManager
        .beginTransaction()
        .add(R.id.fragmentContainerView, fragment)
        .addToBackStack(BACK_STACK)
        .commit()
}