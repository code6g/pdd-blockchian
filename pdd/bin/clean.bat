@echo off
echo.
echo [��Ϣ] ��������·����
echo.

%~d0
cd %~dp0

cd ..
call "D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.1\plugins\maven\lib\maven3\bin\mvn" clean

pause