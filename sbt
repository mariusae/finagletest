#!/bin/bash

# To use YourKit:
#   $ env PROFILE=1 ./sbt
# OR
#   $ java -agentlib:yjpagent -Xss200M -Xmx2G -jar $ROCKDOVE_ROOT/libs/sbt-launch-0.7.4.jar "$@"

if [ "$PROFILE" = "1" ]; then
  JAVA_OPTS="-agentlib:yjpagent $JAVA_OPTS"
fi

test -f ~/.sbtconfig && . ~/.sbtconfig

# Options-magic courtesy of wilhelm@
java -ea                                            \
  $SBT_OPTS                                         \
  $JAVA_OPTS                                        \
  -Djava.net.preferIPv4Stack=true                   \
  -XX:+AggressiveOpts                               \
  -XX:+UseParNewGC                                  \
  -XX:+UseConcMarkSweepGC                           \
  -XX:+CMSParallelRemarkEnabled                     \
  -XX:+CMSClassUnloadingEnabled                     \
  -XX:MaxPermSize=512m                              \
  -XX:SurvivorRatio=128                             \
  -XX:MaxTenuringThreshold=0                        \
  -Xss200M                                          \
  -Xms512M                                          \
  -Xmx2G                                            \
  -server                                           \
  -jar $HOME/lib/java/sbt-launch-0.12.1.jar "$@"
