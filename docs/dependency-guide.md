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

## 3) Use identifier and registry helpers

```java
import com.kotoritos.kotoritolib.api.ModId;
import com.kotoritos.kotoritolib.api.RegistryTools;

var id = ModId.id("my_resource");
var block = RegistryTools.register(myRegistry, "my_mod", "example_block", () -> createBlock());
```

## 4) Use lifecycle callbacks

```java
import com.kotoritos.kotoritolib.api.lifecycle.ServerStartedCallback;

ServerStartedCallback.EVENT.register(server -> {
    int onlinePlayers = server.getPlayerManager().getPlayerList().size();
    System.out.println("[MyMod] Server started with " + onlinePlayers + " players online.");
});
```

## 5) Use delayed/repeating tasks

```java
import com.kotoritos.kotoritolib.api.scheduler.ServerTaskScheduler;

ServerStartedCallback.EVENT.register(server -> {
    ServerTaskScheduler.schedule(server, 100, () -> System.out.println("Runs after 5 seconds"));
    ServerTaskScheduler.scheduleRepeating(server, 20, 20, () -> System.out.println("Runs every second"));
});
```

## 6) Use JSON config helper

```java
import com.google.gson.GsonBuilder;
import com.kotoritos.kotoritolib.api.config.ConfigIO;

var gson = new GsonBuilder().setPrettyPrinting().create();
var config = ConfigIO.readOrCreate(configPath, gson, MyConfig.class, MyConfig::new);
```

## 7) Use weighted random helper

```java
import com.kotoritos.kotoritolib.api.collections.WeightedSelector;

var selector = new WeightedSelector<String>()
        .add("common", 70)
        .add("rare", 25)
        .add("legendary", 5);
```

## 8) Compatibility checks (recommended)

```java
import com.kotoritos.kotoritolib.api.version.ApiVersion;

boolean supported = ApiVersion.isMajorCompatible(1);
```

## 9) Best practices
- Put shared cross-mod logic in Kotorito Lib, not in each individual mod.
- Keep dependent mods focused on gameplay content.
- Upgrade dependency versions intentionally and test migration paths.
