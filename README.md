# Kotorito Lib (Fabric 1.20.1)

Kotorito Lib is a **library mod** for the Kotoritos ecosystem. It centralizes reusable APIs, shared conventions, and lifecycle hooks so future mods can depend on one stable foundation.

## Why this project exists
- Provide a single shared dependency for all future Kotoritos mods.
- Reduce duplicate utility code across projects.
- Standardize identifiers, registry helpers, lifecycle events, and API versioning.
- Keep the project fully documented for long-term GitHub maintenance.

## Technical baseline
- Minecraft: `1.20.1`
- Loader: Fabric
- Java: `17`
- Build tool: Gradle + Fabric Loom

## Repository policy
- Source-only repository: no precompiled binaries are committed.
- Build artifacts are generated locally with the provided tutorial script.

## Current functional features
- **Lifecycle bridge events** exposed to other mods:
  - `ServerStartedCallback`
  - `ServerStoppingCallback`
- **ID utilities** (`ModId`) for safe and consistent `Identifier` creation.
- **Registry utilities** (`RegistryTools`) for cleaner registration patterns.
- **API version utility** (`ApiVersion`) to support compatibility checks in dependent mods.

## Project structure
- `src/main/java/com/kotoritos/kotoritolib/KotoritoLib.java` – mod initializer and lifecycle bridge.
- `src/main/java/com/kotoritos/kotoritolib/api/` – public reusable API package.
- `docs/research.md` – compatibility research and implementation decisions.
- `docs/dependency-guide.md` – how future mods should depend on this lib.
- `tutorial.bat` – Windows helper to bootstrap wrapper/build/run.
- `docs/windows-build-tutorial.md` – step-by-step Windows usage guide.

## Build
```bash
./gradlew clean build
```

## Run in development
```bash
./gradlew runClient
```

## Windows tutorial script (.bat)
Run `tutorial.bat` from Command Prompt. It will:
1. Generate/update the Gradle wrapper locally
2. Compile the mod with `clean build`
3. Output the JAR to `build\libs\`

Required Gradle for bootstrap: **8.14.3**

## License
**ARR (All Rights Reserved)** — author: **kotoritos**.
