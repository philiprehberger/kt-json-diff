package com.philiprehberger.jsondiff

import kotlin.test.*

class JsonDiffTest {
    @Test fun `no changes`() {
        val r = jsonDiff("""{"a":1}""", """{"a":1}""")
        assertFalse(r.hasChanges())
    }
    @Test fun `added field`() {
        val r = jsonDiff("""{"a":1}""", """{"a":1,"b":2}""")
        assertEquals(1, r.added().size)
        assertEquals("$.b", r.added().first().path)
    }
    @Test fun `removed field`() {
        val r = jsonDiff("""{"a":1,"b":2}""", """{"a":1}""")
        assertEquals(1, r.removed().size)
    }
    @Test fun `modified field`() {
        val r = jsonDiff("""{"a":1}""", """{"a":2}""")
        assertEquals(1, r.modified().size)
    }
    @Test fun `nested`() {
        val r = jsonDiff("""{"a":{"b":1}}""", """{"a":{"b":2}}""")
        assertEquals("$.a.b", r.modified().first().path)
    }
    @Test fun `array changes`() {
        val r = jsonDiff("""{"a":[1,2]}""", """{"a":[1,3]}""")
        assertEquals(1, r.modified().size)
    }
    @Test fun `ignorePaths`() {
        val r = jsonDiff("""{"a":1,"b":2}""", """{"a":1,"b":99}""") { ignorePaths.add("$.b") }
        assertFalse(r.hasChanges())
    }
}
