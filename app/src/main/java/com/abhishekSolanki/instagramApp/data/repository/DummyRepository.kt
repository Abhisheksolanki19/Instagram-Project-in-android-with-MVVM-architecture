package com.abhishekSolanki.instagramApp.data.repository


import com.abhishekSolanki.instagramApp.data.local.db.DatabaseService
import com.abhishekSolanki.instagramApp.data.model.Dummy
import com.abhishekSolanki.instagramApp.data.remote.NetworkService
import com.abhishekSolanki.instagramApp.data.remote.request.DummyRequest
import io.reactivex.Single
import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchDummy(id: String): Single<List<Dummy>> =
        networkService.doDummyCall(DummyRequest(id)).map { it.data }

}