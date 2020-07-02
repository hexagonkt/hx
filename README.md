
# Application Templates

Each subdirectory starting with `hexagon_` inside this module is a different template.

You can package the templates locally with the command:

    # TODO Addapt to Maven Archetypes
    ./gradlew clean && ./gradlew processTemplate

## Starters Code

For convenience, the code used to generate the templates is in `src/`. It is tested before being
copied to the template.

## TODO
- [ ] Fix first logging message (stdout instead Logback)
- [ ] Add a dark theme to JavaFX UI
- [ ] Right click at tray icon stops the application
- [ ] Modules and JLink are not working

## Distribution

```java
module com.hexagonkt.sidekick {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.web;
    requires kotlin.stdlib;
    requires kotlinx.html.jvm;
    requires tornadofx;
    requires hexagon.core;
}
```

```kotlin
plugins {
    id("org.beryx.jlink") version "2.20.0"
}

jlink {
    addExtraDependencies("javafx")
    launcher {
        noConsole = true
    }
}
```
