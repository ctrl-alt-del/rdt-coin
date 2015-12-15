package com.rdt.coin.coin;

public class Point {
    private String price;
    private String time;
    private String latency;

    public Point(String[] strings) {
        price = strings[0];
        time = strings[1];
        latency = strings[2];
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public String getLatency() {
        return latency;
    }
}
