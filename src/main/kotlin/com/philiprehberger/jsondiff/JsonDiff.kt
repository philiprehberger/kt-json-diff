package com.philiprehberger.jsondiff

import kotlinx.serialization.json.*

/** Compute a structural diff between two JSON strings. */
public fun jsonDiff(old: String, new: String, config: DiffConfig.() -> Unit = {}): DiffResult {
    val cfg = DiffConfig().apply(config)
    val oldEl = Json.parseToJsonElement(old)
    val newEl = Json.parseToJsonElement(new)
    val changes = mutableListOf<Change>()
    diffElement(oldEl, newEl, "$", cfg, changes)
    return DiffResult(changes)
}

private fun diffElement(old: JsonElement, new: JsonElement, path: String, cfg: DiffConfig, changes: MutableList<Change>) {
    if (path in cfg.ignorePaths) return
    when {
        old is JsonObject && new is JsonObject -> diffObject(old, new, path, cfg, changes)
        old is JsonArray && new is JsonArray -> diffArray(old, new, path, cfg, changes)
        old != new -> changes.add(Change(ChangeType.MODIFIED, path, old, new))
    }
}

private fun diffObject(old: JsonObject, new: JsonObject, path: String, cfg: DiffConfig, changes: MutableList<Change>) {
    for (key in old.keys) {
        val childPath = "$path.$key"
        if (childPath in cfg.ignorePaths) continue
        if (key !in new) changes.add(Change(ChangeType.REMOVED, childPath, old[key], null))
        else diffElement(old[key]!!, new[key]!!, childPath, cfg, changes)
    }
    for (key in new.keys) {
        if (key !in old) changes.add(Change(ChangeType.ADDED, "$path.$key", null, new[key]))
    }
}

private fun diffArray(old: JsonArray, new: JsonArray, path: String, cfg: DiffConfig, changes: MutableList<Change>) {
    if (cfg.ignoreArrayOrder) {
        val oldSet = old.toSet()
        val newSet = new.toSet()
        for (el in oldSet - newSet) changes.add(Change(ChangeType.REMOVED, path, el, null))
        for (el in newSet - oldSet) changes.add(Change(ChangeType.ADDED, path, null, el))
    } else {
        val maxLen = maxOf(old.size, new.size)
        for (i in 0 until maxLen) {
            val childPath = "$path[$i]"
            when {
                i >= old.size -> changes.add(Change(ChangeType.ADDED, childPath, null, new[i]))
                i >= new.size -> changes.add(Change(ChangeType.REMOVED, childPath, old[i], null))
                else -> diffElement(old[i], new[i], childPath, cfg, changes)
            }
        }
    }
}
