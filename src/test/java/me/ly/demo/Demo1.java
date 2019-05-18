package me.ly.demo;

import me.ly.pojo.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Demo1 {

    @Test
    public void test(){
        /**
         * 创建实体管理类工厂，借助Persistence的静态方法获取
         * 其中传递的参数为持久化单元名称，需要jpa配置文件制定
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //创建实体管理类
        EntityManager manager = factory.createEntityManager();
        //获取事务对象
        EntityTransaction transaction = manager.getTransaction();
        //开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("铁甲小宝");
        //保存操作
        manager.persist(customer);
        //提交事务
        transaction.commit();
        //释放资源
        manager.close();
        factory.close();
    }
}
