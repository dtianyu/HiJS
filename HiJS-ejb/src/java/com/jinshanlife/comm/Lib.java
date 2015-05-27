/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.comm;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author C0160
 */
public class Lib {

    public static void buildJson(JsonStructure value, String fileFullName) throws IOException {

        /* Write formatted JSON Output */
        Map<String, String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory factory = Json.createWriterFactory(config);

        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = factory.createWriter(stringWriter)) {
            jsonWriter.write(value);
            jsonWriter.close();
        }
        //建立文件
        File jsonFile = new File(fileFullName);
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }
        //保存内容
        OutputStream outputStream;
        outputStream = new FileOutputStream(jsonFile);
        outputStream.write(stringWriter.toString().getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

    }

    public static String formatDate(String format, Date day) {
        if (format != null && day != null) {
            String str = "";
            for (int i = 0; i < format.toString().length(); i++) {
                str += String.format("%t" + format.substring(i, i + 1) + "", day);
            }
            return str;
        } else {
            return "";
        }
    }

    public static String getLocalOperateMessage(String value) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "i18n");
        return bundle.getString(value);
    }

    public static String securityMD5(String str) throws UnsupportedEncodingException {
        MessageDigest messageDigest = null;
        byte[] byteArray = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            byteArray = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(Lib.class.getName()).log(Level.SEVERE, null, e);
        }
        StringBuilder md5Buff = new StringBuilder();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5Buff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5Buff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5Buff.toString();

    }

    public static void sendVerifyCode(String value) {

        HashMap<String, Object> result = null;

        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init("sandboxapp.cloopen.com", "8883");

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount("aaf98f894d22eb10014d2758e69a0413", "45c42156fa5848baa695270f27adfe6c");

        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId("8a48b5514d232afc014d275d42d302db");

        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。																														       *
        //**************************************************************************************************
        result = restAPI.sendTemplateSMS("13816375299", "1", new String[]{value, "3"});

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if (!"000000".equals(result.get("statusCode"))) {
            //异常返回输出错误码和错误信息
            Logger.getLogger("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }
    }

}
