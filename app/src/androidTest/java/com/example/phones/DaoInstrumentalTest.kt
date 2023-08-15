package com.example.phones

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.phones.Modelo.local.PhonesDao
import com.example.phones.Modelo.local.database.PhonesDataBase
import com.example.phones.Modelo.local.entities.PhonesDetailEntity
import com.example.phones.Modelo.local.entities.PhonesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoInstrumentalTest {

private lateinit var phonesDao: PhonesDao
private lateinit var db: PhonesDataBase

@Before
fun setUp(){
    val context= ApplicationProvider.getApplicationContext<android.content.Context>()
    db= Room.inMemoryDatabaseBuilder(context,PhonesDataBase::class.java).build()
    phonesDao= db.getPhonesDao()
}

@After
fun shutDown(){
    db.close()

}

@Test
fun insertCoursesList() = runBlocking {

    val phonesEntities= listOf(
        PhonesEntity (2,"prueba",2000,"imagen1"),
        PhonesEntity (4,"prueba2",3000,"imagen2")

    )

    phonesDao.insertAllPhones(phonesEntities)

    val phonesLiveData = phonesDao.getAllPhones()
    val phonesList : List<PhonesEntity> = phonesLiveData.value?: emptyList()

    // verificamos el listado
    // verificamos el listado si no es vacio
    assertThat(phonesList, not(emptyList()))
    assertThat(phonesList.size,equalTo(2))
}

@Test
fun insertDetailPhone()= runBlocking {

    val coursesDetail = PhonesDetailEntity(
        2,
        "Phone 2",
        4500,
        "imagen 2",
        "Phone 2",
        3500,
        true
    )

    phonesDao.insertPhoneDetail(coursesDetail)
     val courseLiveData= phonesDao.getPhoneDetailById(2)
     val courseValue= courseLiveData.value

    assertThat(courseValue?.id, equalTo(2))
    assertThat(courseValue?.credit, equalTo(false))
}
}