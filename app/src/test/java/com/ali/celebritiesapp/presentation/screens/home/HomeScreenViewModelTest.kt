package com.ali.celebritiesapp.presentation.screens.home

import com.ali.celebritiesapp.MainDispatcherRule
import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.domain.model.toArtist
import com.ali.celebritiesapp.domain.model.toVenue
import com.ali.celebritiesapp.domain.repository.CelebritiesRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {
    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var repository: CelebritiesRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = mockk()
        viewModel = HomeScreenViewModel(repository)
    }

    @Test
    fun `test getArtists return success`() = runTest {
        val artistsList = listOf(
            Artist("Genre 1", 1, "Artist 1"),
            Artist("Genre 2", 2, "Artist 2")
        )
        coEvery { repository.getArtists() } returns Resource.Success(artistsList)

        // When
        viewModel.getArtists {}

        // Then
        assertEquals(artistsList.sortedBy { it.name }, viewModel.artists.map { artistItem -> artistItem.toArtist() })
    }

    @Test
    fun `test getVenues return success`() = runTest {
        val venuesList = listOf(
            Venue(3, "Venue 1", 1),
            Venue(5, "Venue 2", 2)
        )
        coEvery { repository.getVenues() } returns Resource.Success(venuesList)

        // When
        viewModel.getVenues {}

        // Then
        assertEquals(venuesList.sortedBy { it.sortId }, viewModel.venues.map { venueItem -> venueItem.toVenue() })
    }

}