package com.wonders;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.*;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 6:29 下午 2020/3/25
 */
@Data
@Accessors(chain = true)
public class Test implements Serializable{

    private static final long serialVersionUID = -1576559015308404264L;
    private String name;

    private String nn;

    public static void main(String[] args) throws Exception{

        Test test = new Test().setName("wzh");
//        test.write();
        test.read();
    }

    public void write() throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("test")));
        oos.writeObject(this);
        oos.close();;
    }

    public void read() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("test")));
        System.out.println(ois.readObject());
        ois.close();
    }

}
