package com.philiprehberger.jsondiff

/** Result of a JSON diff operation. */
public data class DiffResult(public val changes: List<Change>) {
    public fun hasChanges(): Boolean = changes.isNotEmpty()
    public fun added(): List<Change> = changes.filter { it.type == ChangeType.ADDED }
    public fun removed(): List<Change> = changes.filter { it.type == ChangeType.REMOVED }
    public fun modified(): List<Change> = changes.filter { it.type == ChangeType.MODIFIED }
}
