# Research and compatibility notes

This document captures the technical research used to define the Kotorito Lib baseline.

## Research goals
- Ensure the project targets a stable, widely-used Fabric setup.
- Minimize version mismatch risk for future dependent mods.
- Provide practical APIs that reduce repetitive code in downstream mods.

## Compatibility conclusions
1. **Minecraft 1.20.1 + Java 17** is the correct runtime baseline for this branch.
2. **Fabric Loader + Fabric API + Yarn mappings** must be declared explicitly to avoid ambiguous runtime behavior.
3. A library-first mod should keep gameplay logic out of core initialization and focus on reusable APIs.

## Implementation decisions from research
- Use explicit dependency coordinates in `gradle.properties` and `build.gradle`.
- Keep `fabric.mod.json` strict with dependency constraints.
- Expose lifecycle callbacks, task scheduling, config I/O, and registry utilities as reusable APIs.
- Start with semantic API versioning (`1.0.0`) and major-compatibility checks.

## Future hardening roadmap
- Publish the artifact to a Maven repository and pin dependency examples to release tags.
- Add automated tests for pure utility APIs and API compatibility checks.
- Maintain a changelog per API version increment.
