@echo off
setlocal

REM Kotorito Lib build-only script for Windows

where gradle >nul 2>nul
if errorlevel 1 (
  echo ERROR: Gradle was not found in PATH.
  echo Install Gradle 8.14.3 and try again.
  goto end
)

echo Running: gradle wrapper --gradle-version 8.14.3 --no-validate-url
call gradle wrapper --gradle-version 8.14.3 --no-validate-url
if errorlevel 1 goto fail

echo Running: gradlew.bat clean build
call gradlew.bat clean build
if errorlevel 1 goto fail

echo.
echo Build finished successfully.
echo Your mod JAR is in: build\libs\
goto end

:fail
echo.
echo Build failed. Verify:
echo  - Java 17 is installed and configured
echo  - Gradle 8.14.3 is installed and in PATH
echo  - Internet access is available for first dependency download
echo  - Fabric repositories are reachable

:end
endlocal
