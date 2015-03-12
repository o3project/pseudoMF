#!/bin/sh -

DIR=$(dirname $0)
JAVA=java

MC_JAR=$DIR/pseudoMf-1.0.0.jar
MC_BIN_COMP_MNG_JAVA_ARGS=$DIR/PseudoMf.properties

TMP=/var/tmp
MC_COMPJ_PID_FILE=$TMP/pseudoMf.pid

start_mf_java_comp_mgr()
{
    if check_pid_file $MC_COMPJ_PID_FILE; then
    echo "pseudoMf is already running"
    return 1
    fi
    sudo $JAVA -jar $MC_JAR $MC_BIN_COMP_MNG_JAVA_ARGS &
    echo $! > $MC_COMPJ_PID_FILE
}

start_mf()
{
    start_mf_java_comp_mgr
}

stop_mf()
{
    if check_pid_file $MC_COMPJ_PID_FILE; then
    sudo kill `cat $MC_COMPJ_PID_FILE` > /dev/null 2>&1
    unlink $MC_COMPJ_PID_FILE     > /dev/null 2>&1
    else
    echo "pseudoMf is not running"
    fi
}

check_pid_file()
{
    pid_file=$1
    if [ -f $pid_file ];then
    pid=`cat $pid_file`
    sudo kill -0 $pid > /dev/null 2>&1
    else
    return 1
    fi
}

mf_status()
{
    if check_pid_file $MC_COMPJ_PID_FILE; then
    echo "pseudoMf is running"
    else
    echo "pseudoMf is not running"
    fi
}

show_help()
{
    echo >&2 "usage : $0 [-qrsS]"
    echo >&2 "  -q stop pseudoMf"
    echo >&2 "  -r restart pseudoMf"
    echo >&2 "  -s start pseudoMf"
    echo >&2 "  -S pseudoMf status"
}

# check command args.
if [ $# -lt 1 ]; then
    show_help
    exit 1
fi

sudo sh <<SCRIPT
SCRIPT

while getopts 'qrsS' OPTION
do
    case $OPTION in
    "q")
        stop_mf ;;
    "r")
        stop_mf
        start_mf ;;
    "s")
        start_mf ;;
    "S")
        mf_status ;;
    esac
done

shift $((OPTIND - 1))
