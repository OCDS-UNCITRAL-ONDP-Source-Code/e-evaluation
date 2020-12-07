package com.procurement.evaluation.infrastructure.handler.v2.converter

import com.procurement.evaluation.application.model.award.check.state.CheckAwardStateParams
import com.procurement.evaluation.infrastructure.fail.error.DataErrors
import com.procurement.evaluation.infrastructure.handler.v2.model.request.CheckAwardsStateRequest
import com.procurement.evaluation.lib.functional.Result

fun CheckAwardsStateRequest.convert(): Result<CheckAwardStateParams, DataErrors> =
    CheckAwardStateParams.tryCreate(
        cpid = cpid,
        ocid = ocid,
        country = country,
        operationType = operationType,
        pmd = pmd,
        awards = awards.map { it.convert() }
    )

fun CheckAwardsStateRequest.Award.convert(): CheckAwardStateParams.Award =
    CheckAwardStateParams.Award(id = id)
