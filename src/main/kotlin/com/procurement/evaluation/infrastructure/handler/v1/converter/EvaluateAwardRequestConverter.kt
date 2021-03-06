package com.procurement.evaluation.infrastructure.handler.v1.converter

import com.procurement.evaluation.application.service.award.EvaluateAwardData
import com.procurement.evaluation.exception.ErrorException
import com.procurement.evaluation.exception.ErrorType
import com.procurement.evaluation.infrastructure.handler.v1.model.request.EvaluateAwardRequest
import com.procurement.evaluation.lib.errorIfEmpty

fun EvaluateAwardRequest.convert() = EvaluateAwardData(
    award = this.award
        .let { award ->
            EvaluateAwardData.Award(
                statusDetails = award.statusDetails,
                description = award.description,
                documents = award.documents
                    .errorIfEmpty {
                        ErrorException(
                            error = ErrorType.IS_EMPTY,
                            message = "The award contains empty list of documents."
                        )
                    }
                    ?.map { document ->
                        EvaluateAwardData.Award.Document(
                            id = document.id,
                            title = document.title,
                            description = document.description,
                            relatedLots = document.relatedLots
                                .errorIfEmpty {
                                    ErrorException(
                                        error = ErrorType.IS_EMPTY,
                                        message = "The document '${document.id}' in award contains empty list of related lots."
                                    )
                                }
                                ?.toList()
                                .orEmpty(),
                            documentType = document.documentType
                        )
                    }
                    .orEmpty()
            )
        }
)
