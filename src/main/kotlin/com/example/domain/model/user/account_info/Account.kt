package com.example.domain.model.user.account_info

@kotlinx.serialization.Serializable
data class Account(
   val balance:Int = 0,
   val winnings:Int = 0,
   val transactions:Transactions? = null
)
