# Windows build tutorial (.bat)

This project includes `tutorial.bat` so you can bootstrap and compile the mod on Windows.

> Repository policy: this project is kept source-only. Binary outputs must be generated locally and are not committed.

## Prerequisites
1. Install **Java 17**.
2. Install **Gradle 8.14.3** (used to generate wrapper files and bootstrap the first build).
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
4. The script will generate the wrapper and compile the mod automatically.

## Output location
After build, the mod JAR is generated in:
- `build\libs\`

## About `.exe`
The official workflow provided by this repository is `tutorial.bat` (build-only).
If needed, you may convert it to `.exe` with a third-party tool on your machine, but that executable is out of scope for repository source control.
