JacpFX Quickstart-Archetype template
======

This is a template for maven archetypes

- mvn archetype:create-from-project 
- copy pom.xml from archetype to target/generated-sources/archetype
- delete intellij file and archetype folder in resources/archetype-resources
- mvn clean deploy

use: mvn archetype:generate  -DarchetypeGroupId=org.jacpfx  -DarchetypeArtifactId=JacpFX-simple-quickstart  -DarchetypeVersion=VERSION


mvnDebug clean package exec:java -Dexec.mainClass="quickstart.main.ApplicationLauncher"
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar JacpFX-simple-quickstart-app.jar 