package com.nep.entity;

import java.io.Serializable;

/**
 * @author Bobby
 * 管理员信息
 */

public class Admin extends Operator implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Admin{}";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Admin(String loginCode, String password, String realName) {
        super(loginCode, password, realName);
    }

    public Admin() {
    }
}
