#!/bin/bash

ROOT=$(cd $(dirname $(dirname $(dirname $0))) && pwd)
SCRIPT_PATH="$ROOT/script"

echo "ROOT: $ROOT"

# DB
if [[ $(brew services list | grep postgresql@16 | awk '{print $2}') != "started" ]]; then
  $SCRIPT_PATH/db/start.local.sh
fi

./mvnw spring-boot:run