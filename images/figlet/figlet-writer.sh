#!/bin/bash
name=${1%%.*}
cat "$1" | figlet > "$name.out.txt"
rm -f "$1"
