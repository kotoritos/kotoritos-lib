# Dependency guide for future mods

This guide shows how a new Fabric mod can depend on **Kotorito Lib**.

## 1) Configure repositories and dependency

In the consumer mod `build.gradle`:

```gradle
repositories {
    mavenCentral()
    maven { url = 'https://maven.fabricmc.net/' }
    // Add the Maven repository where kotorito-lib is published.
}

dependencies {
    modImplementation "com.kotoritos:kotorito-lib:1.0.0+1.20.1"
}
```

## 2) Require Kotorito Lib in `fabric.mod.json`

```json
{
  "depends": {
    "fabricloader": ">=0.16.10",
    "minecraft": "~1.20.1",
    "fabric-api": ">=0.92.2+1.20.1",
    "kotorito_lib": ">=1.0.0"
  }
}
```

## 3) Use the public API

```java
import com.kotoritos.kotoritolib.api.ModId;
import com.kotoritos.kotoritolib.api.lifecycle.ServerStartedCallback;

public final class MyModEntrypoint {
    public static void register() {
        var id = ModId.id("my_resource");

        ServerStartedCallback.EVENT.register(server -> {
            int onlinePlayers = server.getPlayerManager().getPlayerList().size();
            System.out.println("[MyMod] Server started with " + onlinePlayers + " players online.");
        });
    }
}
```

## 4) Compatibility checks (recommended)

```java
import com.kotoritos.kotoritolib.api.version.ApiVersion;

boolean supported = ApiVersion.isMajorCompatible(1);
```

## 5) Best practices
- Put shared cross-mod logic in Kotorito Lib, not in each individual mod.
- Keep your dependent mods focused on their own gameplay content.
- Upgrade dependency versions intentionally and test migration paths.
