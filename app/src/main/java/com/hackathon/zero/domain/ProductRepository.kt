package com.hackathon.zero.domain

import com.hackathon.zero.core.dto.ResponseBody
import com.hackathon.zero.data.Product

interface ProductRepository {

    suspend fun getProductList(keyword: String, lastProductId: Int? = null): ResponseBody<Product?>
}