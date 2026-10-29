#!/bin/bash
# System report script - Turatbekuly Amir
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
LOG_FILE="$SCRIPT_DIR/../logs/Turatbekuly_Amir_system_report.log"

{
  echo "=================================================="
  echo "Student:      Turatbekuly Amir"
  echo "Date/time:    $(date '+%Y-%m-%d %H:%M:%S')"
  echo "User:         $(whoami)"
  echo "OS:           $(. /etc/os-release && echo "$PRETTY_NAME")"
  echo "Kernel:       $(uname -r)"
  echo "--- CPU ---"
  lscpu | grep -E 'Model name|^CPU\(s\)'
  echo "--- Memory ---"
  free -h
  echo "--- Disk usage ---"
  df -h /
  echo "--- Available disk space ---"
  df -h --output=target,avail /
} | tee -a "$LOG_FILE"
