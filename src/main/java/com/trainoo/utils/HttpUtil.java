/*
 * ������http���󹤾���
 * �����ˣ�pan.tang
 * ����ʱ�䣺2012-8-13
 */
package com.trainoo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * http���󹤾��࣬��get��post��ʽ����http���󣬻�ȡ���ؽ�����ַ�����
 */
public final class HttpUtil {
    /**
     * ��ֹʵ����
     */
    private HttpUtil() { }
    
    /** ��Ӧ��ʱʱ�䣨���룩**/
    private static final int    DEFAULT_CONNECTION_TIME_OUT = 100000;

    /** ��ȡ��ʱʱ�䣨���룩**/
    private static final int    DEFAULT_READ_TIME_OUT       = 100000;

    /** Ĭ�ϱ���  **/
    private static final String DEFAULT_ENCODING            = "gb2312";

    /** ����ʽ GET  **/
    private static final String REQUEST_METHOD_GET          = "GET";

    /** ����ʽ POST **/
    private static final String REQUEST_METHOD_POST         = "POST";

    /**
     * ���� Get ��ʽ����Http������ GB2312 �����ʽ��������
     * @param urlAddress �����ַ������������
     * @return
     */
    public static String get(String urlAddress) {
        return request(urlAddress, REQUEST_METHOD_GET, "", DEFAULT_ENCODING);
    }

    /**
     * ���� Get ��ʽ����Http����
     * @param urlAddress �����ַ������������
     * @param encoding �����ʽ
     * @return
     */
    public static String get(String urlAddress, String encoding) {
        return request(urlAddress, REQUEST_METHOD_GET, "", encoding);
    }

    /**
     * ���� POST ��ʽ����Http����
     * @param urlAddress �����ַ
     * @param params POST����
     * @return
     */
    public static String post(String urlAddress, String params) {
        return request(urlAddress, REQUEST_METHOD_POST, params,
                DEFAULT_ENCODING);
    }

    /**
     * ���� POST ��ʽ����Http����
     * @param urlAddress �����ַ
     * @param params POST����
     * @param encoding �����ʽ
     * @return
     */
    public static String post(String urlAddress, String params, String encoding) {
        return request(urlAddress, REQUEST_METHOD_POST, params, encoding);
    }

    /**
     * ����HTTP����
     * @param urlAddress url��ַ
     * @param method ����ʽ
     * @param params POST�������
     * @param encoding �����ʽ
     * @return
     */
    private static String request(String urlAddress, String method,
            String params, String encoding) {
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();

            //��������� Accept-Encoding��������վ��ʶ��Ϊ������򣬲�����������
            con.setRequestProperty("Accept-Encoding", "gzip,deflate");
            con.setConnectTimeout(DEFAULT_CONNECTION_TIME_OUT);
            con.setReadTimeout(DEFAULT_READ_TIME_OUT);
            con.setUseCaches(false);

            //������÷�ʽ���ԣ�������վ������������
            con.setRequestMethod(method);

            if (REQUEST_METHOD_POST.equals(method)) {
                con.setDoOutput(true);
                byte[] b = params.getBytes();
                con.getOutputStream().write(b);
                con.getOutputStream().flush();
                con.getOutputStream().close();
            } else {
                con.setDoOutput(false);
            }

            //������ؽ���� gzip ѹ�����ݣ�����������
            if (null != con.getContentEncoding()
                    && con.getContentEncoding().indexOf("gzip") > -1) {
                GZIPInputStream gzin = new GZIPInputStream(con.getInputStream());
                in = new BufferedReader(new InputStreamReader(gzin, encoding));
            } else {
                in = new BufferedReader(new InputStreamReader(
                        con.getInputStream(), encoding));
            }

            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
    /**
     * 
     * getFilebyUrl(ͨ��url ����ļ�������file) 
     * @param response
     * @param fileUrl  
     * void 
     * @exception
     */
    public static void getFilebyUrl(HttpServletResponse response,String fileUrl){
    	URL url = null;  
        InputStream is =null;  
        try {  
            url = new URL(fileUrl);  
         } catch (MalformedURLException e) {  
            e.printStackTrace();  
         }  
        try {  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//����HttpURLConnection����,���ǿ��Դ������л�ȡ��ҳ����.   
            conn.setDoInput(true);  
            conn.connect();  
            is = conn.getInputStream(); //�õ����緵�ص�������   
            ServletOutputStream out = response.getOutputStream();
            //ѭ��ȡ�����е�����
            byte[] b = new byte[1024];
            int len;
            while((len=is.read(b)) >0){
          	  out.write(b,0,len);
            }
           response.setStatus( response.SC_OK );
            response.flushBuffer();
           out.close();
           is.close();
        } catch (IOException e) {  
            e.printStackTrace();  
       }
    }
}
