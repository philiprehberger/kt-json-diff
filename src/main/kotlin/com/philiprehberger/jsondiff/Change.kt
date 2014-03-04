package com.philiprehberger.jsondiff

import kotlinx.serialization.json.JsonElement

/** A single change between two JSON documents. */
public data class Change(
    public val type: ChangeType,
    public val path: String,
    public val oldValue: JsonElement?,
    public val newValue: JsonElement?,
)
