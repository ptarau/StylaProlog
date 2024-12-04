mkdir bin
pushd .
cd src
scalac  -d ../bin prolog/*.scala prolog/interp/*.scala prolog/terms/*.scala prolog/fluents/*.scala  prolog/io/*.scala prolog/builtins/*.scala
popd
