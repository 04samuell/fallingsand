## Falling Sand

## Gradle

- <code>gradle wrapper</code> to generate gradlew and gradlew.bat.
- <code>./gradlew createMissingSourceDirs</code> to establish directory structure.
- Set <code>defaultMainClassName</code> to java class containing main.
- Use <code>gradle run</code> to run.

## JAR Creation

- First compile files and specify bin directory so class files are put in there <code>javac -d src/test/resources/bin src/test/resources/*.java</code>.
- Make MANIFEST.MF file inside same directory as <code>.class</code> files specifying the main class name as <code>Main-Class: package.className</code> or just <code>Main-Class: className</code> if in default package.
- Resource files such as images/bundles shoud be put in same directory as <code>.class</code> files and loaded in in source files using (for example) <code>FallingSand.class.getClassLoader().getResourceAsStream("<path to file from bin directory>")</code>. 
- Make jar file from directory containing <code>.class</code> files with command:  <code>jar cfm FallingSand.jar src/test/resources/bin/MANIFEST.MF -C src/test/resources/bin .</code> to specify that jar should be created using bin directory. Note that the filepath starts from root of project directory.

