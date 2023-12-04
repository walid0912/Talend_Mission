#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/log4j-slf4j-impl-2.13.2.jar:$ROOT_PATH/../lib/log4j-api-2.13.2.jar:$ROOT_PATH/../lib/log4j-core-2.13.2.jar:$ROOT_PATH/../lib/jboss-marshalling-2.0.12.Final.jar:$ROOT_PATH/../lib/mysql-connector-java-8.0.18.jar:$ROOT_PATH/../lib/dom4j-2.1.3.jar:$ROOT_PATH/../lib/slf4j-api-1.7.29.jar:$ROOT_PATH/../lib/talendcsv-1.0.0.jar:$ROOT_PATH/../lib/crypto-utils-0.31.12.jar:$ROOT_PATH/exercice09_01_0_2.jar: mission_talend.exercice09_01_0_2.exercice09_01 "$@"
