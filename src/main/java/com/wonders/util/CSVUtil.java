package com.wonders.util;

import cn.hutool.core.util.ReflectUtil;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 09:13 2020-03-10
 */
public class CSVUtil {


    public <T extends Object> void inputData(OutputStream out, List<T> data,Map<String,String> map){

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"gbk"));

            List<String> headers = new ArrayList<>();
            List<String> fields = new ArrayList<>();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                headers.add(entry.getValue());
                fields.add(entry.getKey());
            }

            for (String header : headers) {
                writer.write("\""+header+"\",");
            }
            for (T t : data) {
                for (String field : fields) {
                    Object value = ReflectUtil.invoke(t,getMethodName(field), (Object) null);
                    writer.write("\""+value+"\",");
                }
                writer.write("\r\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getMethodName(String field){
        field = "get"+StringUtils.capitalize(field);
        System.out.println(field);
        return field;
    }


}
