scp src/main/resources/static/css/my.css aliyun2:/home/docker/app/idata/resources/static/css/
scp -r src/main/resources/templates aliyun2:/home/docker/app/idata/resources/
scp -r src/main/resources/static/js aliyun2:/home/docker/app/idata/resources/static/
scp -r src/main/resources/static/images aliyun2:/home/docker/app/idata/resources/static/

scp target/package/idata-0.0.1-SNAPSHOT.jar aliyun2:/home/docker/app/idata/
scp src/main/resources/application*.yml scp aliyun2:/home/docker/app/idata/resources/
scp -r src/main/resources/mapper scp aliyun2:/home/docker/app/idata/resources/

> -u不覆盖原文件
rsync -avzu --progress target/package/lib aliyun2:/home/docker/app/idata/
