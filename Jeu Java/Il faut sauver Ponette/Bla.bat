
SET PATH=%PATH%;"C:\Program Files\Java\jdk1.7.0_11\bin"

javac Game.java
jar cfm Game.jar manifest.mf Game.class
java -jar Game.jar