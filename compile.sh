rm -r -f bin
mkdir bin
scalac -d bin src/prolog/*.scala src/prolog/*/*.scala
