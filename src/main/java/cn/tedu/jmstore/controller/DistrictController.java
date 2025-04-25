package cn.tedu.jmstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.jmstore.entity.District;
import cn.tedu.jmstore.service.IDistrictService;
import cn.tedu.jmstore.util.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

    @Autowired
    IDistrictService service;

    @GetMapping("/")
    public JsonResult<List<District>> listByParent(String parent){
        List<District> list = service.listByParent(parent);
        return new JsonResult<List<District>>(SUCCESS,list);
    }
}
