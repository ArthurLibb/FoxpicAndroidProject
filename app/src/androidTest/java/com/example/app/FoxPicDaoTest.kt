package com.example.app

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app.database.RoomDB
import com.example.app.database.asDomainFoxPic
import com.example.app.database.asEntity
import com.example.app.database.daos.FoxPicDao
import com.example.app.model.FoxPic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Date

@RunWith(AndroidJUnit4::class)
class FoxPicDaoTest {
    private lateinit var foxpicDb: RoomDB
    private lateinit var foxpicDao: FoxPicDao

    private var foxpic1 = FoxPic(
        "i like this fox",
        "https://randomfox.ca/images/15.jpg",
        Date(2023, 6, 25, 5, 15)
    )
    private var foxpic2 = FoxPic(
        "yup",
        "https://randomfox.ca/images/15.jpg",
        Date()
    )

    private suspend fun addOneFoxPic() {
        foxpicDao.addFoxPic(foxpic1.asEntity())
    }

    private suspend fun addTwoFoxPic() {
        foxpicDao.addFoxPic(foxpic1.asEntity())
        foxpicDao.addFoxPic(foxpic2.asEntity())
    }

    private suspend fun deleteOneFoxPic() {
        foxpicDao.deleteFoxPic(foxpic1.asEntity())
    }

    private suspend fun deleteTwoFoxPic() {
        foxpicDao.deleteFoxPic(foxpic1.asEntity())
        foxpicDao.deleteFoxPic(foxpic2.asEntity())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        foxpicDb = Room.inMemoryDatabaseBuilder(context, RoomDB::class.java)
            .allowMainThreadQueries()
            .build()
        foxpicDao = foxpicDb.foxPicDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        foxpicDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun dao_insertTwoFoxPicIntoDb() = runBlocking {
        addTwoFoxPic()
        val foxpics = foxpicDao.getAll().first()
        assertEquals(foxpics[0].asDomainFoxPic(), foxpic1)
        assertEquals(foxpics[1].asDomainFoxPic(), foxpic2)
    }

    @Test
    @Throws(Exception::class)
    fun dao_insertOneFoxPicIntoDb() = runBlocking {
        addOneFoxPic()
        val foxpics = foxpicDao.getAll().first()
        assertEquals(foxpics[0].asDomainFoxPic(), foxpic1)
    }

    @Test
    @Throws(Exception::class)
    fun dao_deleteOneFoxPicAfterAddingTwo() = runBlocking {
        addTwoFoxPic()
        val foxpics = foxpicDao.getAll().first()
        assertEquals(foxpics.size, 2)
        deleteOneFoxPic()
        val refreshFoxPics = foxpicDao.getAll().first()
        assertEquals(refreshFoxPics.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun dao_deleteTwoFoxPicAfterAddingTwo() = runBlocking {
        addTwoFoxPic()
        val foxpics = foxpicDao.getAll().first()
        assertEquals(foxpics.size, 2)
        deleteTwoFoxPic()
        val refreshFoxPics = foxpicDao.getAll().first()
        assertEquals(refreshFoxPics.size, 0)
    }
}
