package util;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * Created by rio on 2018/10/26.
 */
public class DataTransportUtil {

    private String webServicesNameSpace;
    private String wsMethodName;
    private String targetEndpointAddress;
    private String username;
    private String passwd;

    public DataTransportUtil() {
        this(null);
    }

    public DataTransportUtil(String type) {
        Properties wsp = PropertiesUtil.getPropertiesByType(type);
        this.webServicesNameSpace = wsp.getProperty("WebServicesNameSpace");
        this.wsMethodName = wsp.getProperty("wsMethodName");
        this.targetEndpointAddress = wsp.getProperty("TargetEndpointAddress");
        this.username = wsp.getProperty("username");
        this.passwd = wsp.getProperty("passwd");
    }

    public void axisDynamicInvoke(Map map) {
        String[] paramNames = (String[]) map.get("paramNames");
        Object[] params = (Object[]) map.get("params");

        Service service = new Service();
        Call call = null;

        try {
            call = (Call) service.createCall();

            if(StringUtil.notEmpty(username) && StringUtil.notEmpty(passwd)){
                call.addHeader(new SOAPHeaderElement("Authorization","username",username));
                call.addHeader(new SOAPHeaderElement("Authorization","password",passwd));
            }

            call.setTargetEndpointAddress(new URL(targetEndpointAddress));
            call.setOperationName(new QName(webServicesNameSpace, wsMethodName));

            // 添加传入参数
            for (String param : paramNames) {
                call.addParameter(param,
                        XMLType.SOAP_STRING, ParameterMode.IN);

            }
            call.setReturnType(XMLType.SOAP_INT);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(webServicesNameSpace + wsMethodName);
            Object result = call.invoke(params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        }
    }
}
