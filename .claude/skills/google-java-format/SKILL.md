---
name: google-java-format
description: Automatically format Java and Groovy source files according to Google Java Style Guide
license: MIT
compatibility: Applies to Java and Groovy file creation
metadata:
  author: Carlos Torres (ckorbet)
  version: "1.0"
  generatedBy: "1.0.0"
---

Automatically format Java and Groovy source files according to the Google Java Style Guide.

This skill applies Google Java Format to `.java` and `.groovy` files to ensure consistent code formatting and adherence to Google's Java style standards across the project. The google-java-format tool (v1.20.0) is included in the skill assets directory.

## Google Java Style Standards

This skill enforces the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) which includes:

- ✅ 2-space indentation (no tabs)
- ✅ Line length of 100 characters maximum
- ✅ Consistent brace placement (opening braces on same line, closing on new line)
- ✅ Proper spacing around operators and keywords
- ✅ Consistent import organization
- ✅ One statement per line

## When to Apply

Apply Google Java Format to:
- ✅ All new `.java` files created in the project
- ✅ All new `.groovy` files created in the project
- ✅ All new test files (`.java`, `.groovy` in src/test)
- ✅ All new main source files (`.java`, `.groovy` in src/main)

## How to Apply

### For New Java Files

After creating a new Java file, run the formatter:

```bash
java -jar .claude/skills/google-java-format/assets/google-java-format.jar --replace src/main/java/com/example/MyClass.java
```

**Before formatting:**
```java
package com.example;

import java.util.*;

public class MyClass{
    private String name;
    public MyClass(String name){
        this.name=name;
    }
    public String getName(){return name;}
}
```

**After formatting:**
```java
package com.example;

import java.util.*;

public class MyClass {
  private String name;

  public MyClass(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
```

### For New Groovy Files

Same command applies for Groovy source files:

```bash
java -jar .claude/skills/google-java-format/assets/google-java-format.jar --replace src/main/groovy/com/example/MyService.groovy
```

## Integration with OpenSpec Workflow

When using the OpenSpec workflow to create new Java/Groovy files:

1. **During `/opsx:propose`** - If capability specs define new Java/Groovy source files, note that formatting will be applied
2. **During `/opsx:apply`** - When implementing tasks that create `.java` or `.groovy` files, apply this skill to format the code
3. **Code Examples in Specs** - Document this formatting requirement in capability specs so implementers understand the expected style

### Reminder in Tasks File

When writing tasks for changes that will create Java/Groovy source files, include a reminder task like:

```markdown
- [ ] Format new Java/Groovy files with Google Java Format using the google-java-format skill
```

## Tool Information

- **Tool**: google-java-format v1.20.0
- **Location**: `.claude/skills/google-java-format/assets/google-java-format.jar`
- **Usage**: `java -jar <jar-path> [--replace] <files>`
- **Documentation**: [Google Java Format on GitHub](https://github.com/google/google-java-format)

## Formatting Configuration

Default behavior (no additional flags needed):
- Uses Google's standard formatting rules
- Respects existing formatting intent where possible
- The `--replace` flag modifies files in-place
- Use without `--replace` to see formatting changes without modifying files
