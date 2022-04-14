package com.example.githubusersandconverter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githubusersandconverter.R
import com.example.githubusersandconverter.adapters.CurrenciesAdapter
import com.example.githubusersandconverter.adapters.GitHubUsersAdapter
import com.example.githubusersandconverter.databinding.FragmentGitHubUsersBinding
import com.example.githubusersandconverter.models.Currencies
import com.example.githubusersandconverter.models.GitHubUsers
import com.example.githubusersandconverter.network.NetworkHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GitHubUsersFragment : Fragment() {
    lateinit var binding: FragmentGitHubUsersBinding
    lateinit var context: FragmentActivity
    lateinit var checkNetwork: NetworkHelper
    lateinit var requestQueue: RequestQueue
    lateinit var adapter: GitHubUsersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGitHubUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context = requireActivity()
        checkNetwork = NetworkHelper(context)
        requestQueue = Volley.newRequestQueue(context)
        context.window?.statusBarColor = resources.getColor(R.color.background1)
        context.window?.navigationBarColor = resources.getColor(R.color.background1)
        binding.shimmerLayout.startShimmer()

        if (checkNetwork.isNetworkConnected()) {
            loadArray("https://api.github.com/users")
        } else {
            Toast.makeText(context,
                "Something went wrong, please check your internet connection",
                Toast.LENGTH_LONG).show()
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.isGone = true
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun loadArray(url: String) {
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET,
                url,
                null,
                {
                    val str = it.toString()
                    val type = object : TypeToken<ArrayList<GitHubUsers>>() {}.type
                    val list = Gson().fromJson<ArrayList<GitHubUsers>>(str, type)

                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.isGone = true
                    binding.recyclerView.isVisible = true

                    adapter = GitHubUsersAdapter(list)
                    binding.recyclerView.adapter = adapter
                },

                {
                    binding.shimmerLayout.isGone = true
                    Toast.makeText(context,
                        "Something went wrong",
                        Toast.LENGTH_LONG).show()
                }
            )
        requestQueue.add(jsonArrayRequest)
    }
}