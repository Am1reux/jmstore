package cn.tedu.jmstore.dbtest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.District;
import cn.tedu.jmstore.service.IDistrictService;
import cn.tedu.jmstore.service.ex.ServiceException;

@SpringBootTest
public class TestDistrictService {

	@Autowired
    IDistrictService service;

    @Test
    public void listByParent() {
        try {
            List<District> list=service.listByParent("86");
            for(District dist:list) {
                System.err.println(dist);
            }
        }catch(ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
}
