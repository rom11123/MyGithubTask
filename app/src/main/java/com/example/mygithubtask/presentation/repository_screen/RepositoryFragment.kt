package com.example.mygithubtask.presentation.repository_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mygithubtask.databinding.FragmentRepositoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepositoryFragment : Fragment() {
    private val viewModel by viewModel<RepositoryViewModel>()
//    private val repoViewModel by viewModel<RepoViewModel>()
    lateinit var repositoryAdapter: RepositoryAdapter


    private lateinit var binding: FragmentRepositoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
//        getRepo()
//        updateData()

        update()
        updateCash()

    }

    fun initRecycler() {
        repositoryAdapter = RepositoryAdapter()
        binding.rvRepository.adapter = repositoryAdapter

    }


    fun update() {

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {

                repositoryAdapter.submitList(it.data)
                binding.progressBar.isVisible = false
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }

                if (it.isLoading) {
                    binding.progressBar.isVisible = true
                }

            }


        }
    }

    fun updateCash(){
        viewModel.readAllData.observe(viewLifecycleOwner,Observer{
            repositoryAdapter.submitList(it)
            binding.progressBar.isVisible = false

        })
    }

//    fun getRepo(){
//        repoViewModel.liveData.observe(viewLifecycleOwner,Observer{
//            binding.rvRepository.isVisible = true
//            repositoryAdapter.submitList(it)
//        })
//    }


}





