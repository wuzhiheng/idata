app_path=$PWD/target/package
cd ${app_path}
java -Dloader.path=resources,lib -Dspring.profiles.active=dev -jar idata-0.0.1-SNAPSHOT.jar
