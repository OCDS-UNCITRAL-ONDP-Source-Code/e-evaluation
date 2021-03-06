package com.procurement.evaluation.infrastructure.dto.award.create.response

import com.procurement.evaluation.infrastructure.AbstractDTOTestBase
import com.procurement.evaluation.infrastructure.handler.v1.model.response.CreateAwardsResponse
import org.junit.jupiter.api.Test

class CreateAwardsResponseTest : AbstractDTOTestBase<CreateAwardsResponse>(CreateAwardsResponse::class.java) {

    @Test
    fun fully() {
        testBindingAndMapping("json/infrastructure/dto/award/create/response/response_create_awards_full.json")
    }
}
