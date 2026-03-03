@echo off
setlocal

REM Kotorito Lib text-only bootstrap/build script for Windows
REM This repository intentionally does not commit binary artifacts.

echo ======================================
echo   Kotorito Lib - Build/Run Tutorial
echo ======================================

echo.
echo [1] Generate Gradle Wrapper (text files only)
echo [2] Build mod JAR
echo [3] Run development client
echo [4] Generate wrapper + build + run
echo [5] Exit
echo.
set /p option=Choose an option (1-5): 

if "%option%"=="1" goto wrapper
if "%option%"=="2" goto build
if "%option%"=="3" goto run
if "%option%"=="4" goto all
if "%option%"=="5" goto end

echo Invalid option.
goto end

:wrapper
echo Running: gradle wrapper --gradle-version 8.14.3 --no-validate-url
call gradle wrapper --gradle-version 8.14.3 --no-validate-url
if errorlevel 1 goto fail
echo Wrapper text files generated. Note: gradle-wrapper.jar is generated locally and should not be committed.
goto end

:build
if not exist gradlew.bat (
  echo Wrapper not found. Generating wrapper first...
  call gradle wrapper --gradle-version 8.14.3 --no-validate-url
  if errorlevel 1 goto fail
)
echo Running: gradlew.bat clean build
call gradlew.bat clean build
if errorlevel 1 goto fail
echo Build finished. JAR files are in build\libs
goto end

:run
if not exist gradlew.bat (
  echo Wrapper not found. Generating wrapper first...
  call gradle wrapper --gradle-version 8.14.3 --no-validate-url
  if errorlevel 1 goto fail
)
echo Running: gradlew.bat runClient
call gradlew.bat runClient
if errorlevel 1 goto fail
goto end

:all
call gradle wrapper --gradle-version 8.14.3 --no-validate-url
if errorlevel 1 goto fail
call gradlew.bat clean build runClient
if errorlevel 1 goto fail
goto end

:fail
echo.
echo Build/run failed. Verify:
echo  - Java 17 is installed and configured
echo  - Gradle is installed to bootstrap the wrapper
echo  - Internet access is available for first dependency download
echo  - Fabric repositories are reachable

:end
echo.
echo Done.
endlocal
