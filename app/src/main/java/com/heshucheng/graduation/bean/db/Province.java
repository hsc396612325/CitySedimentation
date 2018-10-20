package com.heshucheng.graduation.bean.db;

import org.litepal.crud.DataSupport;

/**
 * Created by heshu on 2017/11/14.
 */

public class Province extends DataSupport {

    private String provinceName;
    private int provinceCode;

    public Province(String provinceName){

        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}

