package com.example.mygithubtask.presentation.users_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mygithubtask.R
import com.example.mygithubtask.common.Result
import com.example.mygithubtask.common.addFragment
import com.example.mygithubtask.data.model.Users
import com.example.mygithubtask.databinding.FragmentRepositoryBinding
import com.example.mygithubtask.databinding.FragmentUsersBinding
import com.example.mygithubtask.presentation.repository_screen.RepositoryAdapter
import com.example.mygithubtask.presentation.repository_screen.RepositoryViewModel
import com.example.mygithubtask.presentation.users_detail_screen.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {
    private val viewModel by viewModel<UsersViewModel>()
//    private val viewModel by viewModel<NewViewModel>()

    //    private val repoViewModel by viewModel<RepoViewModel>()
    lateinit var usersAdapter: UsersAdapter


    private lateinit var binding: FragmentUsersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        update()
        addCash()


    }

    private fun initRecycler() {
        usersAdapter = UsersAdapter()
        usersAdapter.setOnItemClickListener {
            addFragment(DetailFragment.newInstance(it))
        }
        binding.rvUsers.adapter = usersAdapter

    }


    private fun update() {

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                usersAdapter.submitList(it.users)
                binding.progressBar.isVisible = false
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                if (it.isLoading) {
                    Log.d("tag", "update")
                }

            }


        }
    }

    private fun addCash() {
        viewModel.allData.observe(viewLifecycleOwner, Observer {
            usersAdapter.submitList(it)
            binding.progressBar.isVisible = false
        })
    }

//    fun updateUser() {
//        viewModel.users.observe(viewLifecycleOwner, Observer { result ->
//
//            usersAdapter.submitList(result.data)
//
//            binding.progressBar.isVisible = result is Result.Loadingg && result.data.isNullOrEmpty()
//            binding.textViewError.isVisible =
//                result is Result.Errorr && result.data.isNullOrEmpty()
//            binding.textViewError.text = result.error?.localizedMessage
//        })
//    }


}