#!/bin/bash

URL="http://localhost:8081/events"

EVENT_TYPES=("CLICK" "VIEW" "PURCHASE" "RETURN" "LOGIN" "FAIL")
USERS=("user-134" "user-286" "user-312" "user-194" "user-58")
SOURCES=("app" "web" "mobile" "api")

i=1

while true; do
  EVENT_ID="e-$(printf %06d $i)-$(openssl rand -hex 4)"
  USER_ID=${USERS[$RANDOM % ${#USERS[@]}]}
  EVENT_TYPE=${EVENT_TYPES[$RANDOM % ${#EVENT_TYPES[@]}]}
  SOURCE=${SOURCES[$RANDOM % ${#SOURCES[@]}]}
  TS=$(date +%s%3N)

  curl -s -X POST "$URL" \
    -H "Content-Type: application/json" \
    -d "{
      \"eventId\": \"$EVENT_ID\",
      \"userId\": \"$USER_ID\",
      \"source\": \"$SOURCE\",
      \"eventType\": \"$EVENT_TYPE\",
      \"timestamp\": $TS
    }" > /dev/null

  echo "$(date): Sent event $EVENT_ID ($EVENT_TYPE by $USER_ID from $SOURCE)"

  i=$((i+1))
  sleep 10   # 1 event every 30 seconds
done
