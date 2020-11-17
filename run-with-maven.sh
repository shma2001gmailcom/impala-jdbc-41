#!/usr/bin/env bash

mvn exec:java -Dexec.mainClass=org.misha.Launcher -Dexec.arguments="SELECT description FROM sample_07 limit 10"
