package com.example.githubusersandconverter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githubusersandconverter.R
import com.example.githubusersandconverter.adapters.CurrenciesAdapter
import com.example.githubusersandconverter.databinding.BottomSheetItemDialogBinding
import com.example.githubusersandconverter.databinding.FragmentCurrenciesBinding
import com.example.githubusersandconverter.interfaces.CurrenciesItemClick
import com.example.githubusersandconverter.models.Currencies
import com.example.githubusersandconverter.network.NetworkHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrenciesFragment : Fragment(), CurrenciesItemClick {
    private lateinit var checkNetwork: NetworkHelper
    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: FragmentCurrenciesBinding
    private lateinit var adapter: CurrenciesAdapter
    private lateinit var flagsList: ArrayList<Int>
    lateinit var context: FragmentActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCurrenciesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context = requireActivity()
        checkNetwork = NetworkHelper(context)
        requestQueue = Volley.newRequestQueue(context)
        context.window?.statusBarColor = resources.getColor(R.color.background3)
        context.window?.navigationBarColor = resources.getColor(R.color.background3)
        binding.shimmerLayout.startShimmer()
        loadFlags()

        if (checkNetwork.isNetworkConnected()) {
            loadArray("http://cbu.uz/uzc/arkhiv-kursov-valyut/json/")
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
                    val type = object : TypeToken<ArrayList<Currencies>>() {}.type
                    val list = Gson().fromJson<ArrayList<Currencies>>(str, type)

                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.isGone = true
                    binding.recyclerView.isVisible = true

                    adapter = CurrenciesAdapter(list, flagsList, this@CurrenciesFragment)
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


    override fun openBottomSheetDialog(currencies: Currencies, flag: Int) {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val bindingDialog = BottomSheetItemDialogBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bindingDialog.root)

        bindingDialog.flagImage.setImageResource(flag)
        bindingDialog.nameOfCourse.text = "${currencies.Nominal} ${currencies.CcyNm_UZ}"
        bindingDialog.nameOfCourse2.text = currencies.Ccy
        bindingDialog.courseUzb.text = "${currencies.Rate} so`m"
        bindingDialog.diff.text = currencies.Diff
        if (!(bindingDialog.diff.text.startsWith("-"))) {
            bindingDialog.diff.setTextColor(resources.getColor(R.color.green))
            bindingDialog.arrows.setImageResource(R.drawable.ic_up)
            bindingDialog.diff.text = "+${bindingDialog.diff.text}"
        } else {
            bindingDialog.diff.setTextColor(resources.getColor(R.color.red))
            bindingDialog.arrows.setImageResource(R.drawable.ic_down)
        }

        bottomSheetDialog.show()
    }

    private fun loadFlags() {
        flagsList = ArrayList()
        flagsList.add(R.drawable.usa)
        flagsList.add(R.drawable.european_union)
        flagsList.add(R.drawable.ru)
        flagsList.add(R.drawable.gb)
        flagsList.add(R.drawable.jp)
        flagsList.add(R.drawable.az)
        flagsList.add(R.drawable.bd)
        flagsList.add(R.drawable.bg)
        flagsList.add(R.drawable.bh)
        flagsList.add(R.drawable.bn)
        flagsList.add(R.drawable.br)
        flagsList.add(R.drawable.by)
        flagsList.add(R.drawable.ca)
        flagsList.add(R.drawable.ch)
        flagsList.add(R.drawable.cn)
        flagsList.add(R.drawable.cu)
        flagsList.add(R.drawable.cz)
        flagsList.add(R.drawable.dk)
        flagsList.add(R.drawable.dz)
        flagsList.add(R.drawable.eg)
        flagsList.add(R.drawable.af)
        flagsList.add(R.drawable.ar)
        flagsList.add(R.drawable.ge)
        flagsList.add(R.drawable.hk)
        flagsList.add(R.drawable.hu)
        flagsList.add(R.drawable.id)
        flagsList.add(R.drawable.il)
        flagsList.add(R.drawable.inr)
        flagsList.add(R.drawable.iq)
        flagsList.add(R.drawable.ir)
        flagsList.add(R.drawable.isk)
        flagsList.add(R.drawable.jo)
        flagsList.add(R.drawable.au)
        flagsList.add(R.drawable.kg)
        flagsList.add(R.drawable.kh)
        flagsList.add(R.drawable.kr)
        flagsList.add(R.drawable.kw)
        flagsList.add(R.drawable.kz)
        flagsList.add(R.drawable.la)
        flagsList.add(R.drawable.lb)
        flagsList.add(R.drawable.ly)
        flagsList.add(R.drawable.ma)
        flagsList.add(R.drawable.md)
        flagsList.add(R.drawable.mm)
        flagsList.add(R.drawable.mn)
        flagsList.add(R.drawable.mx)
        flagsList.add(R.drawable.my)
        flagsList.add(R.drawable.no)
        flagsList.add(R.drawable.nz)
        flagsList.add(R.drawable.om)
        flagsList.add(R.drawable.ph)
        flagsList.add(R.drawable.pk)
        flagsList.add(R.drawable.pl)
        flagsList.add(R.drawable.qa)
        flagsList.add(R.drawable.ro)
        flagsList.add(R.drawable.rs)
        flagsList.add(R.drawable.am)
        flagsList.add(R.drawable.sa)
        flagsList.add(R.drawable.sd)
        flagsList.add(R.drawable.se)
        flagsList.add(R.drawable.sg)
        flagsList.add(R.drawable.sy)
        flagsList.add(R.drawable.th)
        flagsList.add(R.drawable.tj)
        flagsList.add(R.drawable.tm)
        flagsList.add(R.drawable.tn)
        flagsList.add(R.drawable.tr)
        flagsList.add(R.drawable.ua)
        flagsList.add(R.drawable.ae)
        flagsList.add(R.drawable.uy)
        flagsList.add(R.drawable.ve)
        flagsList.add(R.drawable.vn)
        flagsList.add(R.drawable.xdr)
        flagsList.add(R.drawable.ye)
        flagsList.add(R.drawable.za)
    }
}