@echo off
color 0a
for /f "tokens=1,2 eol=/ delims=/ " %%a in ('date/t') do set yyyy=%%a
for /f "tokens=1,2 eol=/ delims=/" %%a in ('date/t') do set mm=%%b
for /f "tokens=2,3 delims=/ " %%a in ('date/t') do set dd=%%b
set ymd=%yyyy%_%mm%_%dd%____
set tmp_hms=%time:~-11,2%-%time:~-8,2%-%time:~-5,2%
set hms=%tmp_hms: =%6
set dt=%ymd%%hms%
echo %dt%
mkdir "%dt%"
