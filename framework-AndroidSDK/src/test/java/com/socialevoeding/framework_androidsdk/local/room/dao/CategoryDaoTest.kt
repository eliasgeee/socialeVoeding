/*
package com.socialevoeding.framework_androidsdk.local.room.dao

import com.socialevoeding.framework_androidsdk.local.room.entities.RoomCategoryEntity
import tests.categoryUseCases.DataFactory

class CategoryDaoTest {
    private val categories = listOf<RoomCategoryEntity>(
        RoomCategoryEntity(
            id = 1,
            name = tests.categoryUseCases.DataFactory.randomString(),
            type = tests.categoryUseCases.DataFactory.randomString()
        ),
        RoomCategoryEntity(
            id = 2,
            name = tests.categoryUseCases.DataFactory.randomString(),
            type = tests.categoryUseCases.DataFactory.randomString()
        ),
        RoomCategoryEntity(
            id = 3,
            name = tests.categoryUseCases.DataFactory.randomString(),
            type = tests.categoryUseCases.DataFactory.randomString()
        ),
        RoomCategoryEntity(
            id = 4,
            name = tests.categoryUseCases.DataFactory.randomString(),
            type = tests.categoryUseCases.DataFactory.randomString()
        ),
        RoomCategoryEntity(
            id = 5,
            name = tests.categoryUseCases.DataFactory.randomString(),
            type = tests.categoryUseCases.DataFactory.randomString()
        ),
        RoomCategoryEntity(
            id = 6,
            name = tests.categoryUseCases.DataFactory.randomString(),
            type = tests.categoryUseCases.DataFactory.randomString()
        )
    )
}

*/
/*
@RunWith(MockitoJUnitRunner::class)
class GetLocationsTests {
    private lateinit var getLocations: GetLocations
    private val locations = listOf(UserLocation(1, 1.0, 1.0, 1L, 1))

    @Mock
    private lateinit var locationsRepository: ILocationsRepository

    @Before
    fun setUp() {
        getLocations = GetLocations(locationsRepository)
    }

    @Test
    fun `should call getLocations locations`() {
        runBlocking { getLocations.run(UserIdParams(1)) }
        verify(locationsRepository, times(1)).locations(1)
    }

    @Test
    fun `should return locations obtained from locationsRepository`() {
        given { locationsRepository.locations(1) }.willReturn(OneOf.Success(locations))
        val returnedLocations = runBlocking { getLocations.run(UserIdParams(1)) }
        returnedLocations shouldEqual OneOf.Success(locations)
    }
}*/
