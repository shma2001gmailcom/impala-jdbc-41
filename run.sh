#!/usr/bin/env bash
cd ./target/
java -jar impala-jdbc-41.jar "SELECT description FROM sample_07 limit 10"