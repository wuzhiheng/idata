#!/bin/bash

package=$PWD/target/package
remote=aliyun2:/home/docker/app/idata/
#判断是否打包
if [ -e $package ]
then
  scp target/package/idata-0.0.1-SNAPSHOT.jar   ${remote}
  #不覆盖原文件
  rsync -avzu --progress target/package/lib   ${remote}
fi

#不覆盖原文件
rsync -avzu src/main/resources  ${remote}
#覆盖原文件
scp src/main/resources/static/css/my.css  ${remote}/resources/static/css/
scp -r src/main/resources/templates   ${remote}/resources/
scp -r src/main/resources/mapper  ${remote}/resources/
scp -r src/main/resources/static/js   ${remote}/resources/static/
