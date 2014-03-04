# json-diff

[![CI](https://github.com/philiprehberger/kt-json-diff/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-json-diff/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/json-diff)](https://central.sonatype.com/artifact/com.philiprehberger/json-diff)

Structural diffs between JSON documents with path-based change tracking.

## Requirements

- Kotlin 1.9+ / Java 17+

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.philiprehberger:json-diff:0.1.0")
}
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>json-diff</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

```kotlin
import com.philiprehberger.jsondiff.*

val diff = jsonDiff(
    """{"name":"Alice","age":30}""",
    """{"name":"Alice","age":31,"email":"a@b.com"}"""
)
diff.modified() // [Change(MODIFIED, "$.age", 30, 31)]
diff.added()    // [Change(ADDED, "$.email", null, "a@b.com")]
```

## API

| Function / Class | Description |
|------------------|-------------|
| `jsonDiff(old, new, config)` | Compute structural diff between two JSON strings |
| `DiffResult.hasChanges()` | Check if any differences exist |
| `DiffResult.added()` / `removed()` / `modified()` | Filter changes by type |
| `Change` | Single change with type, path, oldValue, newValue |
| `DiffConfig.ignoreArrayOrder` | Treat arrays as unordered sets |
| `DiffConfig.ignorePaths` | Paths to exclude from comparison |

## Development

```bash
./gradlew test
./gradlew build
```

## License

MIT
