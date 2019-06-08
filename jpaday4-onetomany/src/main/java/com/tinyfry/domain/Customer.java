package com.tinyfry.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Pandy
 * @Date: 2019/6/7 17:11
 * @Version 1.0
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "cust_industry")
    private String custIndustry;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_phone")
    private String custPhone;
    @Column(name = "cust_source")
    private String custSource;

    /**
     * 注解的形式配置多表关系
     *      1.声明关系
     *one-to-many:配置一对多关系
     *targetEntity:对方对象的字节码对象
     *      2.配置外键关系
     * joinColumn:配置的是外键
     * name:外键字段名称
     * referenceColumnName:参照的主表的主键字段名称
     */

    //客户和联系人之间的关系
    /*@OneToMany(targetEntity = LinkMan.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();*/

    //@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    /**
     * CascadeType:
     *              All:    所有
     *              Persist:保存
     *              Merge:  更新
     *              Remove: 删除
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custAddress='" + custAddress + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custSource='" + custSource + '\'' +
                '}';
    }
}
