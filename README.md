## Falling Sand

## Gradle

- <code>gradle wrapper</code> to generate gradlew and gradlew.bat.
- <code>./gradlew createMissingSourceDirs</code> to establish directory structure.
- Set <code>defaultMainClassName</code> to java class containing main.
- Use <code>gradle run</code> to run.

## JAR Creation

- First compile files and specify bin directory so class files are put in there <code>javac -d bin src/test/resources/*.java</code>.
- Make MANIFEST.MF file specifying the main class name as <code>Main-Class: package.className</code> or just <code>Main-Class: className</code> if in default package.
- Make jar file specifying  <code>jar cfm FallingSand.jar MANIFEST.MF -C bin .</code> to specify that jar should be created using bin directory (which contains the .class files).
- jar cfm FallingSand.jar src/test/resources/bin/MANIFEST.MF -C src/test/resources/bin .
- javac -d src/test/resources/bin src/test/resources/*.java