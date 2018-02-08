package com.wisemk.web.spg.share;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.oss.share.OssLog;

public class WHttpReq
{
    private static final OssLog log = new OssLog();

    private static class MyX509TrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
        {
            // TODO Auto-generated method stub

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers()
        {
            // TODO Auto-generated method stub
            return null;
        }

    }

    public static String httpGet(String url, Map<String, String> mapParam)
    {
        String strQuery = "";
        if (null != mapParam)
        {
            for (String paramKey : mapParam.keySet())
            {
                if (strQuery.length() > 0)
                {
                    strQuery += "&";
                }

                strQuery += (paramKey + "=" + mapParam.get(paramKey));
            }
        }

        String result = "";
        BufferedReader in = null;
        try
        {
            /* 打开和URL之间的连接 */
            String urlNameString = url;
            if (strQuery.length() > 0)
            {
                urlNameString = url + "?" + strQuery;
            }

            URL urlPath = new URL(urlNameString);
            HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null)
            {
                result += temp;
            }
        }
        catch (Exception e)
        {
            log.error("http request exception: \n" + OssFunc.getExceptionInfo(e));
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
                log.error("close buff-reader exception: \n" + OssFunc.getExceptionInfo(e2));
            }
        }

        return result;
    }

    public static String httpsGet(String url, Map<String, String> mapParam)
    {
        String strQuery = "";
        if (null != mapParam)
        {
            for (String paramKey : mapParam.keySet())
            {
                if (strQuery.length() > 0)
                {
                    strQuery += "&";
                }

                strQuery += (paramKey + "=" + mapParam.get(paramKey));
            }
        }

        String result = "";
        BufferedReader in = null;
        try
        {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            /* 打开和URL之间的连接 */
            String urlNameString = url;
            if (strQuery.length() > 0)
            {
                urlNameString = url + "?" + strQuery;
            }

            URL urlPath = new URL(urlNameString);
            HttpsURLConnection httpsConn = (HttpsURLConnection) urlPath.openConnection();
            httpsConn.setSSLSocketFactory(ssf);
            httpsConn.setDoOutput(true);
            httpsConn.setDoInput(true);
            httpsConn.setUseCaches(false);

            httpsConn.setRequestMethod("GET");
            httpsConn.connect();

            InputStreamReader isr = new InputStreamReader(httpsConn.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null)
            {
                result += temp;
            }
        }
        catch (Exception e)
        {
            log.error("http request exception: \n" + OssFunc.getExceptionInfo(e));
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
                log.error("close buff-reader exception: \n" + OssFunc.getExceptionInfo(e2));
            }
        }

        return result;
    }

    public static String sendPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = null;
        try
        {
            URL realUrl = new URL(url);

            /* 打开和URL之间的连接 */
            URLConnection conn = realUrl.openConnection();

            /* 设置通用的请求属性 */
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            /* 发送POST请求必须设置如下两行 */
            conn.setDoOutput(true);
            conn.setDoInput(true);

            /* 获取URLConnection对象对应的输出流 */
            out = new PrintWriter(conn.getOutputStream());

            /* 发送请求参数 */
            out.print(param);

            /* flush输出流的缓冲 */
            out.flush();

            /* 定义BufferedReader输入流来读取URL的响应 */
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            result = null;

            log.error("http request exception: \n" + OssFunc.getExceptionInfo(e));
        }

        /* 使用finally块来关闭输出流、输入流 */
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                log.error("close buff-reader exception: \n" + OssFunc.getExceptionInfo(ex));
            }
        }

        return result;
    }
}
