chmod 777 gradlew
./gradlew clean build
mkdir log
nohup java -jar logListener/build/libs/logListener-0.0.1-SNAPSHOT.jar > ./log/web.log &
nohup java -jar orderListener/build/libs/orderListener-0.0.1-SNAPSHOT.jar > ./log/order.log &