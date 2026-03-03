# Windows build and run tutorial (.bat)

This project includes `tutorial.bat` so you can bootstrap, compile, and run the mod on Windows.

> Repository policy: this project is kept source-only. Binary outputs must be generated locally and are not committed.

## Prerequisites
1. Install **Java 17**.
2. Install **Gradle** (used once to generate wrapper files).
3. Ensure internet access to:
   - `services.gradle.org`
   - `maven.fabricmc.net`

## First-time setup and build
1. Open `cmd.exe`.
2. Go to the project folder:
   ```bat
   cd C:\path\to\kotorito-lib
   ```
3. Run:
   ```bat
   tutorial.bat
   ```
4. Choose option `1` to generate the wrapper locally.
5. Choose option `2` to build the mod JAR.

## Run client in development
- Open `tutorial.bat` and choose option `3`, or run directly:
  ```bat
  gradlew.bat runClient
  ```

## Output location
After build, the mod JAR is generated in:
- `build\libs\`

## About `.exe`
The official workflow provided by this repository is `tutorial.bat`.
If needed, you may convert it to `.exe` with a third-party tool on your machine, but that executable is out of scope for repository source control.
