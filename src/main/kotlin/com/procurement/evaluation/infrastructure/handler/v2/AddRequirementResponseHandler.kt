package com.procurement.evaluation.infrastructure.handler.v2

import com.procurement.evaluation.application.service.Logger
import com.procurement.evaluation.application.service.award.AwardService
import com.procurement.evaluation.infrastructure.api.Action
import com.procurement.evaluation.infrastructure.api.v2.CommandTypeV2
import com.procurement.evaluation.infrastructure.fail.Failure
import com.procurement.evaluation.infrastructure.handler.v2.base.AbstractValidationHandlerV2
import com.procurement.evaluation.infrastructure.handler.v2.converter.convert
import com.procurement.evaluation.infrastructure.handler.v2.model.CommandDescriptor
import com.procurement.evaluation.infrastructure.handler.v2.model.request.AddRequirementResponseRequest
import com.procurement.evaluation.lib.functional.Validated
import com.procurement.evaluation.lib.functional.asValidationError
import com.procurement.evaluation.lib.functional.flatMap
import org.springframework.stereotype.Component

@Component
class AddRequirementResponseHandler(
    private val awardService: AwardService,
    logger: Logger
) : AbstractValidationHandlerV2(logger) {

    override val action: Action = CommandTypeV2.ADD_REQUIREMENT_RESPONSE

    override fun execute(descriptor: CommandDescriptor): Validated<Failure> =
        descriptor.body.asJsonNode
            .params<AddRequirementResponseRequest>()
            .flatMap { it.convert() }
            .onFailure { return it.reason.asValidationError() }
            .let { params ->
                awardService.addRequirementResponse(params)
            }
}
