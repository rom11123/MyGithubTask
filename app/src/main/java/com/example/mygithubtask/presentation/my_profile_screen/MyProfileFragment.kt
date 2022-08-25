package com.example.mygithubtask.presentation.my_profile_screen

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mygithubtask.R
import com.example.mygithubtask.databinding.FragmentMyProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyProfileFragment : Fragment() {
    private lateinit var binding: FragmentMyProfileBinding
    private val viewModel by viewModel<MyProfileViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
        update()
        binding.ivProfile.setOnClickListener {
            pickImageFromGallery()

        }

    }

    private fun setupToolBar() {
      binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myProfileFragment -> {

                  findNavController().navigate(R.id.action_myProfileFragment_to_settingsFragment)


                    true
                }

                else -> false
            }
        }
    }


    private fun update() {

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                binding.tvName.text = it.user?.name.toString()
                binding.tvUsername.text = it.user?.username.toString()
                binding.tvEmail.text = it.user?.email.toString()

                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                if (it.isLoading) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

            }


        }
    }


    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.ivProfile.setImageURI(data?.data)
        }
    }

}