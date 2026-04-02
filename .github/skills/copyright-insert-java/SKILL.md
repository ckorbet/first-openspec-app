````skill
---
name: copyright-insert-java
description: Automatically insert copyright header into Java and Groovy source files
license: MIT
compatibility: Applies to Java and Groovy file creation
metadata:
  author: Carlos Torres (ckorbet)
  version: "1.0"
  generatedBy: "1.0.0"
---

Insert copyright headers into Java and Groovy source files.

This skill defines a standard copyright notice that must be inserted at the beginning of every new `.java` and `.groovy` file created during development.

## Copyright Header Template

Use this exact format for Java and Groovy files:

```java
/*
 * Copyright © Carlos Torres (ckorbet).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (ckorbet) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */
```

## When to Apply

Apply the copyright header to:
- ✅ All new `.java` files created in the project
- ✅ All new `.groovy` files created in the project
- ✅ All new test files (`.java`, `.groovy` in src/test)
- ✅ All new main source files (`.java`, `.groovy` in src/main)

## How to Apply

### For New Java Files

When creating a new Java file, prepend the copyright header **before** the package declaration:

```java
/*
 * Copyright © Carlos Torres (ckorbet).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (ckorbet) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### For New Groovy Files

Same format applies for Groovy source files:

```groovy
/*
 * Copyright © Carlos Torres (ckorbet).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (ckorbet) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example

class MyService {
    void performAction() {
        // implementation
    }
}
```

## Integration with OpenSpec Workflow

When using the OpenSpec workflow to create new files:

1. **During `/opsx:propose`** - If capability specs define new Java/Groovy artifacts, add headers
2. **During `/opsx:apply`** - When implementing tasks that create `.java` or `.groovy` files, include the header
3. **Code Examples in Specs** - Document this requirement in capability specs to ensure implementers know to apply it

### Reminder in Tasks File

When writing tasks for changes that will create source files, include a reminder task:

```markdown
## X. Copyright Header

- [ ] X.1 Ensure all new .java files include the copyright header at the top
- [ ] X.2 Ensure all new .groovy files include the copyright header at the top
```

## Verification Checklist

Before completing a task that creates source files:

- [ ] File starts with the copyright notice block
- [ ] Notice is properly formatted as a multi-line Java comment
- [ ] Package declaration comes after the copyright block
- [ ] No content appears before the copyright block
- [ ] All imports follow normal Java/Groovy conventions

## Format Variations

The copyright notice format is consistent across all files:
- **Not** shortened versions - Always use the full text
- **Not** single-line comments - Always use `/* ... */` multi-line format
- **Not** different author names - Always include "Carlos Torres (ckorbet)"
- **Not** year dates - No date declarations needed

## Edge Cases

### Inner Classes and Nested Classes

Inner classes defined within a file do NOT need separate copyright headers - only the main file needs it.

```java
/*
 * Copyright © Carlos Torres (ckorbet).
 * [full header]
 */

package com.example;

public class OuterClass {
    
    public class InnerClass {
        // No additional header needed
    }
}
```

### Generated Code

If files are auto-generated (by Maven plugins, code generators, etc.), apply the header to the generation template, not the generated output. Document this in the build configuration.

### Test Files

Test files follow the same rule:

```java
/*
 * Copyright © Carlos Torres (ckorbet).
 * [full header]
 */

package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {
    @Test
    void applicationStartsSuccessfully() {
        // test implementation
    }
}
```

## Automation Considerations

In future development:
- Consider IDE templates to auto-insert headers on file creation
- Consider Maven plugins to validate header presence in build
- Consider Git hooks to prevent commits missing headers
- Store the copyright text in a central configuration file (e.g., `pom.xml` property)

## References

This copyright notice applies to:
- [Copyright © Carlos Torres (ckorbet). All rights reserved.]

For questions about usage or exceptions, contact the author.

````