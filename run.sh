#!/usr/bin/env bash

java -jar target/cloudera-impala-jdbc-example-uber.jar "SELECT description FROM sample_07 limit 10"