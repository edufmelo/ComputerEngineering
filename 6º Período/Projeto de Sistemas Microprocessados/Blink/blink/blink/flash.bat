@echo off
echo ======================================================
echo PUCPR - Pontificia Universidade Catolica do Parana
echo Autor: Prof. Afonso Miguel
echo Versao: 2.2 - 05/09/2024
echo ======================================================

setlocal enabledelayedexpansion

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
rem Obtendo o diretório corrente
set "currentDir=%CD%"

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
rem Verificar se existe um arquivo .hex no diretorio Debug
set "hexFile="
set "hexPath=%currentDir%\Debug\*.hex"
for %%f in ("%hexPath%") do (
    set "hexFile=%%f"
    goto :FoundHexFile
)

:FoundHexFile
if defined hexFile (
    rem echo Arquivo .hex encontrado: %hexFile%
) else (
    echo Nenhum arquivo .hex encontrado no diretório Debug.
    pause
    exit
)

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
set "command=%currentDir%\avrdude.exe"
set "localConfPath=%currentDir%\avrdude.conf"
set "fileBaseURL=https://arquivos.afonso.prof/"

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
rem Verifica se o arquivo avrdude.conf existe localmente
if not exist "%localConfPath%" (
    echo O arquivo avrdude.conf nao foi encontrado localmente.
    echo Baixando o arquivo de %fileBaseURL%...

    curl --version >nul 2>&1
    if errorlevel 1 (
        echo curl nao esta instalado. Por favor, instale o curl e tente novamente.
        pause
        exit /b 1
    )

    curl -o "%localConfPath%" -w "%%{http_code}" "%fileBaseURL%avrdude.conf"
    echo Download concluido: "%localConfPath%"
)

if not exist "%localConfPath%" (
    echo O arquivo avrdude.conf foi encontrado localmente.
    pause
    exit /b 1
)

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
rem Verifica se o arquivo avrdude.exe existe localmente
if not exist "%command%" (
    echo O arquivo avrdude.exe nao foi encontrado localmente.
    echo Baixando o arquivo de %fileBaseURL%...

    curl --version >nul 2>&1
    if errorlevel 1 (
        echo curl nao esta instalado. Por favor, instale o curl e tente novamente.
        pause
        exit /b 1
    )

    curl -o "%command%" -w "%%{http_code}" "%fileBaseURL%avrdude.exe"
    echo Download concluido: "%command%"
)

if not exist "%command%" (
    echo O arquivo avrdude.exe nao foi encontrado mesmo apos o download.
    pause
    exit /b 1
)

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
REM Verificar se uma porta foi fornecida como argumento
if "%~1" neq "" (
    set "selectedPort=%~1"
    echo Usando a porta fornecida: "%selectedPort%"
    goto :ProgramArduino
)

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
REM Obter as portas seriais disponíveis usando reg query
set count=0
for /f "tokens=3" %%A in ('reg query HKEY_LOCAL_MACHINE\HARDWARE\DEVICEMAP\SERIALCOMM') do (
    set /a count+=1
    set "ports[!count!]=%%A"
)

REM Se não houver portas seriais disponíveis
if %count%==0 (
    echo Nenhuma porta serial disponivel.
    pause
    exit /b
)

REM Listar as portas seriais disponíveis
echo Portas seriais disponiveis:
for /l %%I in (1,1,%count%) do (
    echo %%I: !ports[%%I]!
)

REM Solicitar ao usuário que selecione uma porta
:AskSelection
set /p selection="Selecione o numero da porta desejada: "

REM Verificar se a selecao é válida
if "%selection%"=="" (
    echo Selecao invalida. Por favor, tente novamente.
    goto :AskSelection
)
if %selection% leq 0 (
    echo Selecao invalida. Por favor, tente novamente.
    goto :AskSelection
)
if %selection% gtr %count% (
    echo Selecao invalida. Por favor, tente novamente.
    goto :AskSelection
)

set "selectedPort=!ports[%selection%]!"

:ProgramArduino
REM Salvar a porta selecionada em serial.txt
REM echo %selectedPort% > serial.txt
rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
"%command%" -C"%localConfPath%" -v -patmega328p -carduino -P"%selectedPort%" -b115200 -D -Uflash:w:"%hexFile%":i

rem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
REM pause