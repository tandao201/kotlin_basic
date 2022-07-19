package com.tan.first_app.Repositories

import com.tan.first_app.Interfaces.dao.IMemberDao
import com.tan.first_app.Models.Member

class MemberRepo(private val memberDao: IMemberDao) {

    suspend fun addNewMem(member:Member){
        return memberDao.insertMember(member)
    }

    suspend fun updateMember(meber: Member){
        return memberDao.updateMember(meber)
    }

    suspend fun delete(member: Member){
        return memberDao.delete(member)
    }

    suspend fun getAllMember(): List<Member>{
        return memberDao.getAllMember()
    }

    suspend fun getMemberById(id: Long): Member {
        return memberDao.getMemberById(id)
    }

    suspend fun getMemberContainName(name: String): List<Member> {
        return memberDao.getMemberContainName(name)
    }
}