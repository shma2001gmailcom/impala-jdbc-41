#!/usr/bin/env bash

#install ImpalaJDBC41.jarr to local maven repo

file=./src/main/resources/ImpalaJDBC41.jar
group=Impala
artifact=ImpalaJDBC41
version=2.6.18.1021

mvn install:install-file -Dfile=${file} -DgroupId=${group} \
-DartifactId=${artifact} -Dversion=${version} -Dpackaging=jar
