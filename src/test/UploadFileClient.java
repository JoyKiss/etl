package test;
import java.rmi.RemoteException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.JAFDataHandlerDeserializerFactory;
import org.apache.axis.encoding.ser.JAFDataHandlerSerializerFactory;

/**
 * <b>function:</b>上传文件WebService客户端
 */
public class UploadFileClient {

    public static void main(String[] args) throws ServiceException, RemoteException {
        String url = "http://localhost:8080/WebServiceTest1/services/UploadFile";
//        String fileName = "readMe.txt";
//        String fileName = "算法导论.pdf";
//        String path = System.getProperty("user.dir") + "\\files\\" + fileName;
        String path = "E:\\asd.cron";
        System.out.println(path);

        //这样就相当于构造了一个带文件路径的File了
        DataHandler handler = new DataHandler(new FileDataSource(path));

        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url);

        /**
         * 注册异常类信息和序列化类 ns:FileUploadHandler 和 wsdd 配置文件中的typeMapping中的xmlns:hns="ns:FileUploadHandler" 的对应 DataHandler
         * 和 wsdd 配置文件中的typeMapping中的qname="hns:DataHandler"的DataHandler对应
         */
        QName qn = new QName("apachesoap:DataHandler", "DataHandler");
        call.registerTypeMapping(DataHandler.class, qn, 
                JAFDataHandlerSerializerFactory.class,
                JAFDataHandlerDeserializerFactory.class);
        call.setOperationName(new QName(url, "upload"));

        //设置方法形参，注意的是参数1的type的DataHandler类型的，和上面的qn的类型是一样的
        call.addParameter("handler", qn, ParameterMode.IN);
        call.addParameter("fileName", XMLType.XSD_STRING, ParameterMode.IN);

        //设置返回值类型，下面2种方法都可以
        call.setReturnClass(String.class);
        //call.setReturnType(XMLType.XSD_STRING);

        String result = (String) call.invoke(new Object[] { handler, "D:/qwe.cron" });
        System.out.println(result);
    }
}