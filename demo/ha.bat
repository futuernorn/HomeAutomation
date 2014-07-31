@ECHO OFF
SET JRE_HOME=<path to your jre>
SET MY_CLASSPATH=ha_lib\*;ha.jar
%JRE_HOME%\bin\java.exe -cp %MY_CLASSPATH% <your main class>
