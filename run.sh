#!/usr/bin/env bash

java -jar target/impala-jdbc-41.jar "SELECT description FROM sample_07 limit 10"