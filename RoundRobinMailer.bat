SET M2_HOME=C:\Program Files\apache-maven-3.0.4
SET JAVA_HOME=C:\Program Files\Java\jdk1.6.0_20
SET ANT_HOME=C:\Program Files\apache-ant-1.7.0
SET PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;%ANT_HOME%\bin;
mvn -s settings.xml archetype:generate -DgroupId=com.wildcraft.poc -DartifactId=RoundRobinMailer -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cmd /k command