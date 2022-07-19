package com.tan.first_app.Interfaces

import com.tan.first_app.Models.Member

interface IClickBtnMem {
    fun upDateMem(member: Member)

    fun deleteMem(member: Member)
}