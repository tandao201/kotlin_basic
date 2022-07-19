package com.tan.first_app.Interfaces.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.tan.first_app.Models.Member

@Dao
interface IMemberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMember(member: Member)

    @Update(onConflict = REPLACE)
    suspend fun updateMember(meber: Member)

    @Delete
    suspend fun delete(member: Member)

    @Query("select * from member")
    suspend fun getAllMember(): List<Member>

    @Query("select * from member where id=:id")
    suspend fun getMemberById(id: Long): Member

    @Query("select * from member where name like '%' || :name || '%'")
    suspend fun getMemberContainName(name: String?): List<Member>
}