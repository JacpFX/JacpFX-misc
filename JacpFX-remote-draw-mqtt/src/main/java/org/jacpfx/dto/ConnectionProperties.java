package org.jacpfx.dto;

/**
 * Created by Andy Moncsek on 27.12.13.
 */
public class ConnectionProperties {
    final String ip;
    final String port;

    public ConnectionProperties(final String ip, final String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }
}
