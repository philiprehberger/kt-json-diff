# json-diff

[![Tests](https://github.com/philiprehberger/kt-json-diff/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-json-diff/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/json-diff.svg)](https://central.sonatype.com/artifact/com.philiprehberger/json-diff)
[![Last updated](https://img.shields.io/github/last-commit/philiprehberger/kt-json-diff)](https://github.com/philiprehberger/kt-json-diff/commits/main)

Structural diffs between JSON documents with path-based change tracking.

## Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("com.philiprehberger:json-diff:0.1.4")
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>json-diff</artifactId>
    <version>0.1.4</version>
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

## Support

If you find this project useful:

⭐ [Star the repo](https://github.com/philiprehberger/kt-json-diff)

🐛 [Report issues](https://github.com/philiprehberger/kt-json-diff/issues?q=is%3Aissue+is%3Aopen+label%3Abug)

💡 [Suggest features](https://github.com/philiprehberger/kt-json-diff/issues?q=is%3Aissue+is%3Aopen+label%3Aenhancement)

❤️ [Sponsor development](https://github.com/sponsors/philiprehberger)

🌐 [All Open Source Projects](https://philiprehberger.com/open-source-packages)

💻 [GitHub Profile](https://github.com/philiprehberger)

🔗 [LinkedIn Profile](https://www.linkedin.com/in/philiprehberger)

## License

[MIT](LICENSE)
