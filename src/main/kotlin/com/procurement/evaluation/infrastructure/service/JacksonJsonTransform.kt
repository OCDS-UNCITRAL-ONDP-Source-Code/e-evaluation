package com.procurement.evaluation.infrastructure.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.NullNode
import com.procurement.evaluation.application.service.Transform
import com.procurement.evaluation.infrastructure.fail.Failure
import com.procurement.evaluation.lib.functional.Result
import com.procurement.evaluation.lib.functional.Result.Companion.failure
import com.procurement.evaluation.lib.functional.Result.Companion.success
import java.io.IOException

class JacksonJsonTransform(private val mapper: ObjectMapper) : Transform {

    /**
     * Parsing
     */
    override fun tryParse(value: String): Result<JsonNode, Failure.Incident.Transform.Parsing> = try {
        success(mapper.readTree(value))
    } catch (expected: IOException) {
        failure(Failure.Incident.Transform.Parsing(className = JsonNode::class.java.canonicalName, exception = expected))
    }

    /**
     * Mapping
     */
    override fun <R> tryMapping(value: JsonNode, target: Class<R>): Result<R, Failure.Incident.Transform.Mapping> =
        try {
            if (value is NullNode)
                failure(Failure.Incident.Transform.Mapping(description = "Object to map must not be null."))
            else success(mapper.treeToValue(value, target))
        } catch (expected: Exception) {
            failure(Failure.Incident.Transform.Mapping(description = "Error of mapping.", exception = expected))
        }

    override fun <R> tryMapping(
        value: JsonNode,
        typeRef: TypeReference<R>
    ): Result<R, Failure.Incident.Transform.Mapping> = try {
        val parser = mapper.treeAsTokens(value)
        success(mapper.readValue(parser, typeRef))
    } catch (expected: Exception) {
        failure(Failure.Incident.Transform.Mapping(description = "Error of mapping.", exception = expected))
    }

    /**
     * Deserialization
     */
    override fun <R> tryDeserialization(
        value: String,
        target: Class<R>
    ): Result<R, Failure.Incident.Transform.Deserialization> = try {
        success(mapper.readValue(value, target))
    } catch (expected: Exception) {
        failure(
            Failure.Incident.Transform.Deserialization(description = "Error of deserialization.", exception = expected)
        )
    }

    override fun <R> tryDeserialization(
        value: String,
        typeRef: TypeReference<R>
    ): Result<R, Failure.Incident.Transform.Deserialization> = try {
        success(mapper.readValue(value, typeRef))
    } catch (expected: Exception) {
        failure(
            Failure.Incident.Transform.Deserialization(description = "Error of deserialization.", exception = expected)
        )
    }

    /**
     * Serialization
     */
    override fun <R> trySerialization(value: R): Result<String, Failure.Incident.Transform.Serialization> = try {
        success(mapper.writeValueAsString(value))
    } catch (expected: Exception) {
        failure(Failure.Incident.Transform.Serialization(description = "Error of serialization.", exception = expected))
    }

    /**
     * ???
     */
    override fun tryToJson(value: JsonNode): Result<String, Failure.Incident.Transform.Serialization> = try {
        success(mapper.writeValueAsString(value))
    } catch (expected: Exception) {
        failure(Failure.Incident.Transform.Serialization(description = "Error of serialization.", exception = expected))
    }
}
