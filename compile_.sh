rm -r -f bin
mkdir bin
scalac  -explaintypes -language:reflectiveCalls -language:postfixOps -deprecation -unchecked -feature  -d bin  src/prolog/*.scala src/prolog/*/*.scala
