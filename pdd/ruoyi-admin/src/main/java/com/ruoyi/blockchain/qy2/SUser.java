package com.ruoyi.blockchain.qy2;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import java.util.HashSet;
import java.util.Set;

/**
 * SUser的构造用crypton工具生成的证书本地读取来生成
 */
public class SUser implements User {

    private String userName;

    private String mspid;

    private Enrollment enrollment;

    public SUser(String userName, String mspid) {
        this.userName = userName;
        this.mspid = mspid;
    }

    public String getName() {
        return userName;
    }

    public Set<String> getRoles() {
        return new HashSet<String>();
    }

    public String getAccount() {
        return "";
    }

    public String getAffiliation() {
        return "";
    }

    public Enrollment getEnrollment() {
        return this.enrollment;
    }

    public String getMspId() {
        return this.mspid;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }
}
