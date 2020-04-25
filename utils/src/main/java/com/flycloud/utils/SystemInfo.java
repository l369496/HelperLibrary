package com.flycloud.utils;

import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class SystemInfo {

    public static void selectInetAddress(Delegate.Function1<InetAddress, Boolean> function){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                for (Enumeration<InetAddress> enumIpAddress = en.nextElement()
                        .getInetAddresses(); enumIpAddress.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddress.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if(!function.invoke(inetAddress))
                            return;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("Preference IpAddress", ex.toString());
        }
    }

    public static String getAllLocalIpAddress() {
        final StringBuilder address = new StringBuilder();
        selectInetAddress(new Delegate.Function1<InetAddress, Boolean>() {
            @Override
            public Boolean invoke(InetAddress inetAddress) {
                address.append(inetAddress.getHostAddress()).append('\n');
                return true;
            }
        });
        address.replace(address.length() - 1, address.length() - 1, "");
        return address.toString();
    }
    public static InetAddress getFirstLocalIp4Address(){
        final InetAddress[] addresses = {null};
        selectInetAddress(new Delegate.Function1<InetAddress, Boolean>() {
            @Override
            public Boolean invoke(InetAddress inetAddress) {
                if(inetAddress instanceof Inet4Address){
                    addresses[0] = inetAddress;
                    return false;
                }
                return true;
            }
        });
        return addresses[0];
    }
}
