package com.rdt.coin.coin.models;

public class Point {
    private float price;
    private long time;
    private int latency;

    // Ideally, instead of adding constructor, we should using GSON to parse the data automatically
    public Point(String[] strings) {
        price = Float.valueOf(strings[0]);
        // TODO: This is not the proper way of doing it; data cleaning should be done on server side
        time = (long) ((float) Float.valueOf(strings[1]));
        latency = Integer.valueOf(strings[2]);
    }

    public float getPrice() {
        return price;
    }

    public long getTime() {
        return time;
    }

    public int getLatency() {
        return latency;
    }
}
