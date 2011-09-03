#!/bin/sh

usage() {
    cat 1>&2 <<EOF
WindGate Cleaner

Usage:
    $0 profile [execuion-id]

Parameters:
    profile
        name of WindGate profile name
    execution-id
        execution ID of current execution
        if not specified, this cleans all sessions
EOF
}

if [ $# -ne 1 -a $# -ne 2 ]
then
  usage
  exit 1
fi

_OPT_PROFILE="$1"
_OPT_EXECUTION_ID="$2"

_WG_ROOT="$(dirname $0)/.."
_WG_PROFILE="$_WG_ROOT/profile/${_OPT_PROFILE}.properties"
_WG_SESSION="$_OPT_EXECUTION_ID"

_WG_PLUGIN=""
if [ -e "$_WG_ROOT/plugin" ]
then
    for f in $(ls "$_WG_ROOT/plugin/")
    do
        if [ "$_WG_PLUGIN" = "" ]
        then
            _WG_PLUGIN="$_WG_ROOT/plugin/$f"
        else
            _WG_PLUGIN="$_WG_PLUGIN:$_WG_ROOT/plugin/$f"
        fi
    done
fi

_WG_CLASSPATH="$_WG_ROOT/conf"
if [ -d "$_WG_ROOT/lib" ]
then
    for f in $(ls "$_WG_ROOT/lib/")
    do
        _WG_CLASSPATH="$_WG_CLASSPATH:$_WG_ROOT/lib/$f"
    done
fi
if [ "$ASAKUSA_HOME" != "" -a -d "$ASAKUSA_HOME/core/lib" ]
then
    for f in $(ls "$ASAKUSA_HOME/core/lib/")
    do
        _WG_CLASSPATH="$_WG_CLASSPATH:$ASAKUSA_HOME/core/lib/$f"
    done
fi

export WINDGATE_PROFILE="$_OPT_PROFILE"

_WG_CLASS="com.asakusafw.windgate.bootstrap.WindGateAbort"

echo "Cleaning WindGate Session(s)"
echo "  -classpath $_WG_CLASSPATH"
echo "  -profile $_WG_PROFILE"
echo "  -session $_WG_SESSION"
echo "  -plugin $_WG_PLUGIN"

if [ -d "$HADOOP_HOME" ]
then
    export HADOOP_CLASSPATH="$_WG_CLASSPATH"
    "$HADOOP_HOME/bin/hadoop" \
        "$_WG_CLASS" \
        -profile "$_WG_PROFILE" \
        -session "$_WG_SESSION" \
        -plugin "$_WG_PLUGIN"
else
    java \
        -classpath "$_WG_CLASSPATH" \
        "$_WG_CLASS" \
        -profile "$_WG_PROFILE" \
        -session "$_WG_SESSION" \
        -plugin "$_WG_PLUGIN"
fi

_WG_RET=$?
if [ $_WG_RET -ne 0 ]
then
    echo "WindGateAbort failed with exit code: $_WG_RET" 1>&2
    echo "  -classpath $_WG_CLASSPATH" 1>&2
    echo "  -profile $_WG_PROFILE" 1>&2
    echo "  -session $_WG_SESSION" 1>&2
    echo "  -plugin $_WG_PLUGIN" 1>&2
    exit $_WG_RET
fi
