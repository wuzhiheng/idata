#!/bin/bash

package=$PWD/target/package

#判断是否打包
if [ -e $package ]
then
  scp target/package/idata-0.0.1-SNAPSHOT.jar aliyun2:/home/docker/app/idata/
  #不覆盖原文件
  rsync -avzu --progress target/package/lib aliyun2:/home/docker/app/idata/
fi

#不覆盖原文件
rsync -avzu src/main/resources aliyun2:/home/docker/app/idata/
#覆盖原文件
scp src/main/resources/static/css/my.css aliyun2:/home/docker/app/idata/resources/static/css/
scp -r src/main/resources/templates aliyun2:/home/docker/app/idata/resources/
scp -r src/main/resources/mapper aliyun2:/home/docker/app/idata/resources/
scp -r src/main/resources/static/js aliyun2:/home/docker/app/idata/resources/static/
