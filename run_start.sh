nohup java -Dspring.profiles.active=test -Dserver.port=8088 -Xms50m -Xmx150m -XX:-UseGCOverheadLimit -jar elk_log_sdk-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &