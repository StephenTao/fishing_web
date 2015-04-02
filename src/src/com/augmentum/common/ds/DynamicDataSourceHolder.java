package com.augmentum.common.ds;

public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }
    public static void setMaster() {
        contextHolder.remove();
        contextHolder.set("master");
    }
    public static void setSlave() {
        contextHolder.remove();
        contextHolder.set("slave");
    }
    public static String getDataSouce(){
        String dataSource = contextHolder.get();
        if (dataSource == null) {
            setMaster();
        }
        return contextHolder.get().toString();
    }
}