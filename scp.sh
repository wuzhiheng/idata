#!/bin/bash

current=$(dirname $0)
remote=aliyun3:/home/docker/app/idata/



# 更新全部
all(){
    #判断是否打包
    if [ -e ${current}/target/package ]
    then
      scp ${current}/target/package/idata-0.0.1-SNAPSHOT.jar   ${remote}
      #不覆盖原文件
      rsync -avzu --progress ${current}/target/package/lib   ${remote}
    fi

    #不覆盖原文件
    rsync -avzu ${current}/src/main/resources  ${remote}
    rsync -avzu ${current}/src/main/resources/static/js   ${remote}/resources/static/
    #覆盖原文件
    css
    scp -r ${current}/src/main/resources/templates   ${remote}/resources/
    scp -r ${current}/src/main/resources/mapper  ${remote}/resources/
    scp -r ${current}/src/main/resources/static/js/my   ${remote}/resources/static/js
}

# 只更新css
css(){
   scp ${current}/src/main/resources/static/css/style.css  ${remote}/resources/static/css/
}

case "$1" in
    "css")
    css
;;
    *)
    all
;;
esac
