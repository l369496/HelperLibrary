package com.flycloud.andsetvier;

import android.content.Context;
import android.util.Log;

import com.flycloud.utils.SystemInfo;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;
import com.yanzhenjie.andserver.framework.website.Website;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class ServerManager {
    private Server mServer;

    /**
     * Create server.
     */
    public ServerManager(Context context, int port) {
        final InetAddress inetAddress = SystemInfo.getFirstLocalIp4Address();

        PathManager.setRootDir(context);
        HtmlManager htmlManager = new HtmlManager();
        try {
            htmlManager.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mServer = AndServer.webServer(context)
                .inetAddress(inetAddress)
                .port(port)
                .timeout(10, TimeUnit.SECONDS)
                .listener(new Server.ServerListener() {
                    @Override
                    public void onStarted() {
                        // TODO The server started successfully.
                        Log.d("start server",inetAddress.getHostAddress()+":8080");
                    }

                    @Override
                    public void onStopped() {
                        // TODO The server has stopped.
                        Log.d("stop server",inetAddress.getHostAddress()+":8080");
                    }

                    @Override
                    public void onException(Exception e) {
                        // TODO An exception occurred while the server was starting.
                        Log.d("server on exception",inetAddress.getHostAddress()+":8080");
                        e.printStackTrace();
                    }
                })
                .build();
    }

    /**
     * Start server.
     */
    public void startServer() {
        if (mServer.isRunning()) {
            // TODO The server is already up.
        } else {
            mServer.startup();
        }
    }

    /**
     * Stop server.
     */
    public void stopServer() {
        if (mServer.isRunning()) {
            mServer.shutdown();
        } else {
            Log.w("AndServer", "The server has not started yet.");
        }
    }
}
