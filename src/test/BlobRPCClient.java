package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import javax.xml.namespace.QName;

public class BlobRPCClient
{
    public static void main(String[] args) throws Exception{
//       
//        //=================测试文件上传==================================
//        
//        String filePath = "f:\\head.jpg";
//        FileInputStream fis = new FileInputStream(filePath);
//       
//        // 创建保存要上传的图像文件内容的字节数组
//        byte[] buffer = new byte[fis.available()];
//        
//        //将输入流fis中的数据读入字节数组buffer中
//        fis.read(buffer);  
//      
//        //设置入参（1、文件名，2、文件字节流数组）
//        Object[] opAddEntryArgs = new Object[]{"我是上传的文件.jpg", buffer};
//        
//        //返回值类型
//        Class<?>[] classes = new Class<?>[]{ Boolean.class };
//        
//        //指定要调用的方法名及WSDL文件的命名空间
//        QName opAddEntry = new QName("http://ws.apache.org/axis2","uploadFile");
//        
//        //关闭流
//        fis.close();
//     
//        //执行文件上传
//        System.out.println(new Date()+" 文件上传开始");
//        Object returnValue = serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes)[0];
//        System.out.println(new Date()+" 文件上传结束，返回值="+returnValue);
//      
//        //=================测试文件下载==================================
//
//        opAddEntry = new QName("http://ws.apache.org/axis2", "downloadFile");
//        
//        System.out.println(new Date()+" 文件下载开始");
//        byte bytes[] = (byte[]) serviceClient.invokeBlocking(opAddEntry, new Object[]{}, new Class[]{byte[].class})[0];
//        FileOutputStream fileOutPutStream = new FileOutputStream("F:\\我是下载的文件.jpg");
//       
//        //将字节数组bytes中的数据，全部写入输出流fileOutPutStream中
//        fileOutPutStream.write(bytes);
//        fileOutPutStream.flush();
//        fileOutPutStream.close();
//        System.out.println(new Date()+" 文件下载完成");
    }
}
