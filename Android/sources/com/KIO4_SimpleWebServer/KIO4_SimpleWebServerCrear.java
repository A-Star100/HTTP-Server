package com.KIO4_SimpleWebServer;

import com.google.appinventor.components.runtime.util.NanoHTTPD;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

public class KIO4_SimpleWebServerCrear {
    public String contenido = "";
    public HttpServiceThread httpServiceThread;

    public class HttpServiceThread extends Thread {
        static final int HttpServerPORT = 8080;
        boolean RUNNING;
        BasicHttpContext basicHttpContext;
        HttpService httpService;
        ServerSocket serverSocket;
        Socket socket;

        class HomeCommandHandler implements HttpRequestHandler {
            HomeCommandHandler() {
            }

            public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                EntityTemplate entityTemplate = new EntityTemplate(new ContentProducer() {
                    public void writeTo(OutputStream outputStream) throws IOException {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                        outputStreamWriter.write(KIO4_SimpleWebServerCrear.this.contenido);
                        outputStreamWriter.flush();
                    }
                });
                httpResponse.setHeader("Content-Type", NanoHTTPD.MIME_HTML);
                httpResponse.setEntity(entityTemplate);
            }
        }

        HttpServiceThread() {
            this.RUNNING = false;
            this.RUNNING = true;
            startHttpService();
        }

        private synchronized void startHttpService() {
            BasicHttpProcessor basicHttpProcessor = new BasicHttpProcessor();
            this.basicHttpContext = new BasicHttpContext();
            basicHttpProcessor.addInterceptor(new ResponseDate());
            basicHttpProcessor.addInterceptor(new ResponseServer());
            basicHttpProcessor.addInterceptor(new ResponseContent());
            basicHttpProcessor.addInterceptor(new ResponseConnControl());
            this.httpService = new HttpService(basicHttpProcessor, new DefaultConnectionReuseStrategy(), new DefaultHttpResponseFactory());
            HttpRequestHandlerRegistry httpRequestHandlerRegistry = new HttpRequestHandlerRegistry();
            httpRequestHandlerRegistry.register("/", new HomeCommandHandler());
            this.httpService.setHandlerResolver(httpRequestHandlerRegistry);
        }

        public void run() {
            try {
                ServerSocket serverSocket2 = new ServerSocket(HttpServerPORT);
                this.serverSocket = serverSocket2;
                serverSocket2.setReuseAddress(true);
                while (this.RUNNING) {
                    this.socket = this.serverSocket.accept();
                    DefaultHttpServerConnection defaultHttpServerConnection = new DefaultHttpServerConnection();
                    defaultHttpServerConnection.bind(this.socket, new BasicHttpParams());
                    this.httpService.handleRequest(defaultHttpServerConnection, this.basicHttpContext);
                    defaultHttpServerConnection.shutdown();
                }
                this.serverSocket.close();
            } catch (IOException | HttpException e) {
            }
        }

        public synchronized void stopServer() {
            this.RUNNING = false;
            ServerSocket serverSocket2 = this.serverSocket;
            if (serverSocket2 != null) {
                try {
                    serverSocket2.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public String getIpAddress() {
        String str = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (nextElement.isSiteLocalAddress()) {
                        str = str + "SiteLocalAddress: " + nextElement.getHostAddress() + "\n";
                    }
                }
            }
            return str;
        } catch (SocketException e) {
            return str + "Something Wrong! " + e.toString() + "\n";
        }
    }

    public void onCreate(String str) {
        this.contenido = str;
        HttpServiceThread httpServiceThread2 = new HttpServiceThread();
        this.httpServiceThread = httpServiceThread2;
        httpServiceThread2.start();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.httpServiceThread.stopServer();
    }
}
