package com.example.githubusersandconverter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.githubusersandconverter.R
import com.example.githubusersandconverter.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var context: FragmentActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context = requireActivity()
        context.window?.statusBarColor = resources.getColor(R.color.green)
        context.window?.navigationBarColor = resources.getColor(R.color.green)

        binding.gitHubUsers.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_gitHubUsersFragment)
        }

        binding.currencies.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_currenciesFragment)
        }
    }
}