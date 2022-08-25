package com.example.mygithubtask.presentation.users_detail_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mygithubtask.R
import com.example.mygithubtask.data.model.Users
import com.example.mygithubtask.databinding.FragmentDetailBinding


private const val ARG = "ARG"
const val BACK_STACK = "backStack"


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        val users: Users? = arguments?.getSerializable(ARG) as? Users

        Glide.with(binding.detailImage.context)
            .load(users?.avatarUrl)
            .into(binding.detailImage)
        binding.detailLogin.text = users?.login.toString()
    }

    private fun setToolbar() {
      binding.toolbarDetails?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }


    fun Fragment.addFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerView, fragment)
            .addToBackStack(BACK_STACK)
            .commit()
    }

    companion object {
        fun newInstance(users: Users) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG, users)

                }
            }
    }
}