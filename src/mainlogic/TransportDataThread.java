package mainlogic;

import util.DataTransportUtil;
import util.PropertiesUtil;
import util.TextAreaScrollUtil;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by rio on 2018/10/26.
 */
public class TransportDataThread extends Thread {

    private JTextArea textArea;

    // 使用哪个配置文件
    private static final String WHICH_PROP = null;

    public TransportDataThread() {
    }

    public TransportDataThread(JTextArea textArea) {
        super();
        this.textArea = textArea;
    }

    public void run() {
        TextAreaScrollUtil.TextAreaScroll(this.textArea, "推送车辆当前位置服务启动...");

        DataTransportUtil dtu = null;
        try {
            dtu = new DataTransportUtil(WHICH_PROP);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 参数
        Map map = new HashMap();
        map.put("paramNames", new String[]{"xmlLocationInfoOfSupplier", "operationType", "credentials"});
        Properties wsp = PropertiesUtil.getPropertiesByType(WHICH_PROP);
        String[] values = new String[3];
        values[2] = wsp.getProperty("credentials");
        map.put("params",values);

        while (true) {

            // 组装数据
            values[0] = creaetXMLLocationInfoOfSupplier();
            values[1] = "1";

            // 调用WebService定时传递数据
            dtu.axisDynamicInvoke(map);

        }
    }

    private String creaetXMLLocationInfoOfSupplier() {
        StringBuffer sb = new StringBuffer();

        sb.append("<DataSet xmlns=\"\">");
        sb.append("<ds>");
        sb.append("<Gps_No>").append("13588888000").append("</Gps_No>");
        sb.append("<Gps_ID>").append("13588888000").append("</Gps_ID>");
        sb.append("<Car_No>").append("蚂蚁测001").append("</Car_No>");
        sb.append("<CompanyOrg_ID>").append("201310210849000001").append("</CompanyOrg_ID>");
        sb.append("<Driver_Code>").append("4001").append("</Driver_Code>");
        sb.append("<HD_Direction>").append("355").append("</HD_Direction>");
        sb.append("<HD_Altitude>").append("0").append("</HD_Altitude>");
        sb.append("<HD_Latitude>").append("28.617277").append("</HD_Latitude>");
        sb.append("<HD_Longitude>").append("115.91056").append("</HD_Longitude>");
        sb.append("<HD_RunAndStopFlag>").append("0").append("</HD_RunAndStopFlag>");
        sb.append("<HD_Speed>").append("80").append("</HD_Speed>");
        sb.append("<HD_Miles>").append("146199").append("</HD_Miles>");
        sb.append("<HD_GpsTime>").append("2014-12-05 15:24:38").append("</HD_GpsTime>");
        sb.append("<HD_ReceiveTime>").append("2014-12-05 15:24:54").append("</HD_ReceiveTime>");
        sb.append("<HD_DriverTime>").append("0").append("</HD_DriverTime>");
        sb.append("<HD_GprsSignalStrength>").append("0").append("</HD_GprsSignalStrength>");
        sb.append("<HD_GpsSatelliteNumber>").append("0").append("</HD_GpsSatelliteNumber>");
        sb.append("<HD_GpsMiles>").append("0").append("</HD_GpsMiles>");
        sb.append("<HD_K1Flag>").append("0").append("</HD_K1Flag>");
        sb.append("<HD_K2Flag>").append("0").append("</HD_K2Flag>");
        sb.append("<HD_K3Flag>").append("0").append("</HD_K3Flag>");
        sb.append("<HD_FuelConsumption1>").append("0").append("</HD_FuelConsumption1>");
        sb.append("<HD_FuelConsumptionChange1>").append("0").append("</HD_FuelConsumptionChange1>");
        sb.append("<HD_Temperature1>").append("0").append("</HD_Temperature1>");
        sb.append("<HD_Humidity1>").append("0").append("</HD_Humidity1>");
        sb.append("<HD_Temperature3>").append("0").append("</HD_Temperature3>");
        sb.append("<HD_FuelConsumption2>").append("0").append("</HD_FuelConsumption2>");
        sb.append("<HD_FuelConsumptionChange2>").append("0").append("</HD_FuelConsumptionChange2>");
        sb.append("<HD_Temperature2>").append("0").append("</HD_Temperature2>");
        sb.append("<HD_Humidity2>").append("0").append("</HD_Humidity2>");
        sb.append("<HD_SleepFlag>").append("0").append("</HD_SleepFlag>");
        sb.append("<HD_Temperature4>").append("0").append("</HD_Temperature4>");
        sb.append("<HD_BatteryPowerFlag>").append("0").append("</HD_BatteryPowerFlag>");
        sb.append("<HD_OffOilControlFlag>").append("0").append("</HD_OffOilControlFlag>");
        sb.append("<HD_Location>").append("").append("</HD_Location>");
        sb.append("<HD_Point>").append("江西省 南昌市 青云谱区 青云谱南路 南昌市青云谱瑞通汽车修理厂西250米").append("</HD_Point>");
        sb.append("<HD_Road>").append("").append("</HD_Road>");
        sb.append("<OffLineFlag>").append("0").append("</OffLineFlag>");
        sb.append("</ds>");
        sb.append("</DataSet>");

        return sb.toString();
    }
}
