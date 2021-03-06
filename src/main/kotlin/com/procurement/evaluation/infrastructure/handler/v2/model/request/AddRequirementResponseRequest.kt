package com.procurement.evaluation.infrastructure.handler.v2.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.procurement.evaluation.domain.model.data.RequirementRsValue

data class AddRequirementResponseRequest(
    @param:JsonProperty("cpid") @field:JsonProperty("cpid") val cpid: String,
    @param:JsonProperty("ocid") @field:JsonProperty("ocid") val ocid: String,
    @param:JsonProperty("award") @field:JsonProperty("award") val award: Award
) {
    data class Award(
        @param:JsonProperty("id") @field:JsonProperty("id") val id: String,
        @param:JsonProperty("requirementResponse") @field:JsonProperty("requirementResponse") val requirementResponse: RequirementResponse
    ) {
        data class RequirementResponse(
            @param:JsonProperty("id") @field:JsonProperty("id") val id: String,
            @param:JsonProperty("value") @field:JsonProperty("value") val value: RequirementRsValue,
            @param:JsonProperty("relatedTenderer") @field:JsonProperty("relatedTenderer") val relatedTenderer: RelatedTenderer,
            @param:JsonProperty("requirement") @field:JsonProperty("requirement") val requirement: Requirement,
            @param:JsonProperty("responder") @field:JsonProperty("responder") val responder: Responder
        ) {
            data class RelatedTenderer(
                @param:JsonProperty("id") @field:JsonProperty("id") val id: String
            )

            data class Requirement(
                @param:JsonProperty("id") @field:JsonProperty("id") val id: String
            )

            data class Responder(
                @param:JsonProperty("id") @field:JsonProperty("id") val id: String,
                @param:JsonProperty("name") @field:JsonProperty("name") val name: String
            )
        }
    }
}
