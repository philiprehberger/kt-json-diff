package com.philiprehberger.jsondiff

/** Configuration for JSON diff. */
public class DiffConfig {
    /** Treat arrays as unordered sets. */
    public var ignoreArrayOrder: Boolean = false
    /** Paths to ignore (e.g., "$.timestamp"). */
    public val ignorePaths: MutableSet<String> = mutableSetOf()
}
