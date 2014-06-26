 SET PATH=%PATH%;"C:\Program Files\Java\jdk1.7.0\bin"

javadoc -d progdoc -author -version -private -linksource *.java
javadoc -d userdoc -author -version *.java