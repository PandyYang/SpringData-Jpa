package com.tinyfry;

import com.tinyfry.dao.CustomerDao;
import com.tinyfry.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 7:58
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 查询单个对象
     */
    @Test
    public void testSpec(){
        /**
         * ---------匿名内部类----------
         *          1.实现Specification接口 提供泛型 查询的对象类型
         *          2.实现toPredicate方法(构造查询条件)
         *          3.需要借助方法参数中的两个参数
         *              root:获取需要查询的对象属性
         *              CriteriaBuilder:构造查询条件的内封装了很多查询条件(模糊匹配 精准匹配)
         *          案例:根据客户名称查询
         *              查询条件
         *                  1.查询方式
         *                      cb对象
         *                  2.比较的属性名称
         *                      root对象
         */
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取比较的属性
                Path<Object> custName = root.get("custName");
                //构造查询条件
                /**
                 * 第一个参数:需要比较的属性(path对象)
                 * 第二个参数:当前需要比较的取值
                 */
                Predicate predicate = criteriaBuilder.equal(custName,"fry");//进行精准匹配
                return predicate;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println("one = " + one);
    }

    /**
     * 多条件查询
     *      案例:根据客户名 和 客户所属行业查询
     */
    @Test
    public void testSpec1(){
        /**
         * root用来获取属性
         *      客户名
         *      所属行业
         * cb:构造查询
         *      1.构造客户名的精准匹配查询
         *      2.构造所属行业的精准匹配查询
         *      3.将以上两个查询联系起来
         */
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");

                Predicate p1 = criteriaBuilder.equal(custName, "fry");
                Predicate p2 = criteriaBuilder.equal(custIndustry, "2");

                //将多个条件组合在一起
                    //与关系
                //Predicate and = criteriaBuilder.and(p1, p2);
                //System.out.println("and = " + and);
                Predicate or = criteriaBuilder.or(p1, p2);
                //System.out.println("or = " + or);
                return or;
            }
        };
        List<Customer> one = customerDao.findAll(spec);
        for (Customer customer : one) {
            System.out.println("customer = " + customer);
        }
    }

    /**
     * 完成根据客户名称的模糊匹配
     * 客户名称以fry 开头
     */
    @Test
    public void testSpec3(){
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //查询属性 客户名
                Path<Object> custName = root.get("custName");
                //查询方式 模糊匹配
                Predicate like = criteriaBuilder.like(custName.as(String.class), "fry%");
                return like;
            }
        };
        List<Customer> all = customerDao.findAll(specification);
        for (Customer customer : all) {
            System.out.println("customer = " + customer);
        }
    }

    /**
     * 排序
     */
    @Test
    public void testSpec4(){
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //查询属性 客户名
                Path<Object> custName = root.get("custName");
                //查询方式 模糊匹配
                Predicate like = criteriaBuilder.like(custName.as(String.class), "fry%");
                return like;
            }
        };
       //排序
            //创建排序对象
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        //第一个参数:排序的顺序 第二个参数:排序的属性名
        List<Customer> all = customerDao.findAll(specification, sort);
        for (Customer customer : all) {
            System.out.println("customer = " + customer);
        }
    }
    /**
     * 分页
     */
    @Test
    public void testSpec5(){
        //PageRequest是Pageable的实现类
        /**
         * 创建PageRequest的过程中 需要调用其构造方法传入两个参数
         *      第一个参数
         */
        Specification specification = null;
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> all = customerDao.findAll(null,pageable);
        System.out.println("all = " + all.getContent());
        System.out.println("all = " + all.getTotalElements());
    }
}
