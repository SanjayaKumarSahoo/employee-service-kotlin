#!/bin/sh

### Set default environment variables
#default_env_vars() {
#  [ -z "$MAX_HEAP" ] && MAX_HEAP="512m"
#}

### Construct ARGS and OPTIONS
default_cmd_args() {
  ARGS=""
  [ ! -z "$CONFIG_LOC" ] && ARGS="$ARGS --spring.config.location='$CONFIG_LOC'"

  OPTIONS=""
  [ ! -z "$JAVA_OPTS" ] && OPTIONS="$OPTIONS ${JAVA_OPTS}"
}

### Parse arguments. Set $EXTRA_ARGS and EXTRA_OPTIONS 
parse_args() {
  EXTRA_ARGS=""
  EXTRA_OPTIONS=""
  while [ "$#" -gt 0 ] ; do
    case "$1" in
      --show-command)
        SHOW_COMMAND="true"
        shift
        ;;
      -o|--java_options)
        shift
        EXTRA_OPTIONS="$EXTRA_OPTIONS $1"
        shift
        ;;
      --*=*)
        # extra key-value args (add quotation)
        arg="${1%%=*}=\"${1#*=}\""
        echo $arg
        EXTRA_ARGS="$EXTRA_ARGS $arg"
        shift
        ;;
      *)
        # default as extra args
        EXTRA_ARGS="$EXTRA_ARGS $1"
        shift
        ;;
    esac
  done
}

### Main

#default_env_vars
default_cmd_args
parse_args "$@"
SHOW_COMMAND="true"

cmd="exec java \
  -XX:MaxTenuringThreshold=15 \
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/var/log/dump.hprof \
  "
cmd=$cmd" ${OPTIONS}"
cmd=$cmd" ${EXTRA_OPTIONS}"
cmd=$cmd" -jar /service/app.jar"
cmd=$cmd" ${ARGS}"

[ ! -z "$SHOW_COMMAND" ] && echo $cmd

echo $cmd > run.sh
chmod 755 run.sh
echo "Starting"
cat run.sh
sh run.sh
#eval "$cmd"