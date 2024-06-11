#!/bin/bash

ROOT=$(cd $(dirname $(dirname $(dirname $0))) && pwd)
SCRIPT_PATH="$ROOT/script"

echo "Permission setting for script files recurrsively : $SCRIPT_PATH"
# sudo chmod +x $SCRIPT_PATH
sudo chmod -R +x $SCRIPT_PATH