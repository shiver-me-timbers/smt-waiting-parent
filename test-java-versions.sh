#!/bin/bash

jenv local 1.8
export JAVA_HOME="$HOME"/.jenv/versions/1.8
export JDK_HOME="$JAVA_HOME"
mvn clean verify -Pspring-boot1 -Pjava8
mvn clean verify -Pspring-boot2 -Pjava8
jenv local 17
export JAVA_HOME="$HOME"/.jenv/versions/17
export JDK_HOME="$JAVA_HOME"
mvn clean verify -Pspring-boot2 -Pjava17
mvn clean verify -Pspring-boot3 -Pjava17