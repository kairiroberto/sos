<%-- 
    Document   : newjsp
    Created on : 22/03/2019, 14:38:02
    Author     : Roberto
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URLConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String sb = "...";
            try {
                String param = request.getParameter("serie");
                URL url = new URL("<host>/index.php/arduino-rest/sos.html?serie=00001");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("User-Agent", "ANDROID");
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.flush();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sb += line;
                }
                bufferedReader.close();
                dataOutputStream.close();
                connection.disconnect();
                connection.disconnect();
            } catch (Exception e) {
                sb = e.toString();
            }
            
        %>
        <%= sb %>
    </body>
</html>
