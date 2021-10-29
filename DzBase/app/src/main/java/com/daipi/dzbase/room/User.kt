package com.daipi.dzbase.room

import androidx.room.*

data class Address(
    val street: String?,
    val state: String?,
    val city: String?,
    @ColumnInfo(name = "post_code") val postCode: Int
)

data class TestData(var id:Int, var name:String)

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
    //@Embedded val address: Address?
)
