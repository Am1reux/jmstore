package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.Address;
import cn.tedu.jmstore.entity.District;
import cn.tedu.jmstore.mapper.AddressMapper;
import cn.tedu.jmstore.mapper.DistrictMapper;

@SpringBootTest
public class TestDistrictMapper {

    @Autowired
    DistrictMapper mapper;

    //@Test
    public void findByParent(){
        String parent="86";
        List<District> list=mapper.findByParent(parent);
        for(District dist:list){
            System.err.println(dist);
        }
    }
    
    @Test
    public void findByCode(){
        String code="110000";
        District dist=mapper.findByCode(code);
        System.err.println(dist);
    }
}

