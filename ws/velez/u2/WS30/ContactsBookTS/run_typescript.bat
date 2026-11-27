@echo off
cd /d "%~dp0"
call tsc
node server.js
pause
