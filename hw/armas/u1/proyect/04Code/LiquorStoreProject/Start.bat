@echo off
cd /d "%~dp0"
chcp 65001 >nul
powershell.exe -NoProfile -ExecutionPolicy Bypass -File "%~dp0Build.ps1"
if errorlevel 1 (
    pause
    exit /b 1
)
set "STORE_JAVA=java.exe"
if exist "%JAVA_HOME%\bin\java.exe" set "STORE_JAVA=%JAVA_HOME%\bin\java.exe"
for /d %%J in ("%ProgramFiles%\Java\jdk-*") do if exist "%%~J\bin\java.exe" set "STORE_JAVA=%%~J\bin\java.exe"
"%STORE_JAVA%" -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -jar "dist\SystemLiquorStore.jar"
pause
