package com.example.githubusersandconverter.interfaces

import com.example.githubusersandconverter.models.Currencies

interface CurrenciesItemClick {
    fun openBottomSheetDialog(currencies: Currencies, flag: Int)
}