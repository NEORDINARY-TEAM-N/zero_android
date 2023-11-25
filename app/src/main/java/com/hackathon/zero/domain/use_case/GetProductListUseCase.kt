package com.hackathon.zero.domain.use_case

import com.hackathon.zero.core.Resource
import com.hackathon.zero.data.Product
import com.hackathon.zero.domain.ProductRepository
import com.hackathon.zero.util.Constants
import com.hackathon.zero.util.isSuccessful
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(keyword: String, lastProductId : Int): Flow<Resource<Product>> = flow {
        runCatching {
            emit(Resource.loading())
            val response = productRepository.getProductList(keyword, lastProductId)
            if(isSuccessful(response.status)) {
                emit(Resource.success(response.result))
            } else {
                emit(Resource.error(response.message))
            }
        }.onFailure {
            emit(Resource.error(it.localizedMessage ?: Constants.ERROR_UNKNOWN))
        }
    }

}