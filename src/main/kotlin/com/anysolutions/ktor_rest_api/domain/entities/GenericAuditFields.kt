package com.anysolutions.ktor_rest_api.domain.entities

import kotlinx.serialization.Serializable

@Serializable
open class GenericAuditFields(var createdAt: Long, var modifiedAt: Long?)