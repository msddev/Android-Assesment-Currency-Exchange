package com.mkdev.data

import com.mkdev.data.mapper.RateMapper
import com.mkdev.data.source.RateDataSourceFactory
import com.mkdev.domain.model.Rate
import com.mkdev.domain.repository.RateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    private val dataSourceFactory: RateDataSourceFactory,
    private val rateMapper: RateMapper
) : RateRepository {
    override fun getRates(): Flow<List<Rate>> = flow {
        val isCached = dataSourceFactory.getCacheDataSource().isCached()
        val rateList = dataSourceFactory.getDataStore(isCached).getRates()
            .map { rateEntity ->
                rateMapper.mapFromEntity(rateEntity)
            }
        saveRates(rateList)
        emit(rateList)
    }

    override fun getRateByCurrencyName(name: String): Flow<Rate> = flow {
        val venuesDetail = rateMapper.mapFromEntity(
            dataSourceFactory.getDataStore(true).getRateByCurrencyName(name)
        )
        emit(venuesDetail)
    }

    override suspend fun saveRates(rateList: List<Rate>) {
        val rateEntities = rateList.map { rate ->
            rateMapper.mapToEntity(rate)
        }
        dataSourceFactory.getCacheDataSource().saveRates(rateEntities)
    }
}