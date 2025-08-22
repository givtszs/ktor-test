package com.example.routes.reports

import com.example.app.models.ApplicationDataDto

data class Metadata(
    val metadata: ApplicationDataDto,
    val settlementDate: String,
)

data class PlacementOrderPayload(
    val data: Metadata,
)
