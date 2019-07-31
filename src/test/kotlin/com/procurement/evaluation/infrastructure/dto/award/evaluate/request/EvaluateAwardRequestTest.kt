package com.procurement.evaluation.infrastructure.dto.award.evaluate.request

import com.procurement.evaluation.infrastructure.AbstractDTOTestBase
import org.junit.jupiter.api.Test

class EvaluateAwardRequestTest : AbstractDTOTestBase<EvaluateAwardRequest>(EvaluateAwardRequest::class.java) {

    @Test
    fun fully() {
        testBindingAndMapping("json/infrastructure/dto/award/evaluate/request/request_evaluate_award_full.json")
    }

    @Test
    fun required1() {
        testBindingAndMapping("json/infrastructure/dto/award/evaluate/request/request_evaluate_award_required_1.json")
    }

    @Test
    fun required2() {
        testBindingAndMapping("json/infrastructure/dto/award/evaluate/request/request_evaluate_award_required_2.json")
    }
}
