package me.ly.demo;

import me.ly.pojo.Customer;
import me.ly.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class Demo2 {

    /**
     * 测试新增数据
     */
    @Test
    public void testAdd() {
        //定义对象
        Customer customer = new Customer();
        customer.setCustName("鲁迅");
        customer.setCustLevel("VIP客户");
        customer.setCustSource("读者");
        customer.setCustIndustry("作家");
        customer.setCustAddress("浙江绍兴");
        customer.setCustPhone("94529452");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //保存数据
            entityManager.persist(customer);
            //事务提交
            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 更新
     */
    @Test
    public void testMerge(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            Customer customer = entityManager.find(Customer.class,1L);
            customer.setCustName("周树人");
            //清除缓存
            entityManager.clear();
            //更新
            entityManager.merge(customer);
            //事务提交
            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }

        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 删除
     */
    @Test
    public void testRemove(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            Customer customer = entityManager.find(Customer.class,1L);
            //移除对象
            entityManager.remove(customer);
            //事务提交
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 根据ID查询
     */
    @Test
    public void testGetOne(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            Customer customer1 = entityManager.find(Customer.class,2L);
            Customer customer2 = entityManager.find(Customer.class,2L);
            //事务提交
            transaction.commit();
            System.out.println(customer1 == customer2);

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 根据ID查询(延迟查询)
     */
    @Test
    public void testLoadOne(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            Customer customer1 = entityManager.getReference(Customer.class,2L);
            //事务提交
            transaction.commit();
            System.out.println(customer1);

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 查询全部
     */
    @Test
    public void testFindAll(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //创建Query对象
            Query query = entityManager.createQuery("from Customer");
            //获取list集合
            List list = query.getResultList();
            for (Object customer : list) {
                System.out.println(customer);
            }

            //事务提交
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testPaged(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //创建Query对象
            Query query = entityManager.createQuery("from Customer");
            //起始索引
            query.setFirstResult(0);
            //每页显示条数
            query.setMaxResults(2);
            //获取list集合
            List list = query.getResultList();
            for (Object customer : list) {
                System.out.println(customer);
            }

            //事务提交
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testCondition(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //创建Query对象
            Query query = entityManager.createQuery("from Customer where custName like ?1");
            //设置参数
            query.setParameter(1,"%舍%");
           //获取查询结果
            List list = query.getResultList();
            for (Object o : list) {
                System.out.println(o);
            }

            //事务提交
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 排序
     */
    @Test
    public void testOrder(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //创建Query对象
            Query query = entityManager.createQuery("from Customer order by custId desc");
            List list = query.getResultList();
            System.out.println(list);
            //事务提交
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //创建Query对象
            Query query = entityManager.createQuery("select count(custId) from Customer");
            Object count = query.getSingleResult();
            System.out.println(count);
            //事务提交
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test
    public void testCount2(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getFactory();
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //创建Query对象
            Query query = entityManager.createQuery("select c from Customer c");
            List list = query.getResultList();
            System.out.println(list);
            //事务提交
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                //事务回滚
                transaction.rollback();
            }
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }
}
