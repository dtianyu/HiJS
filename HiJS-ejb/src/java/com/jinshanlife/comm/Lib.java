/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.comm;

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
        outputStream.write(stringWriter.toString().getBytes());
        outputStream.flush();
        outputStream.close();

    }

}
