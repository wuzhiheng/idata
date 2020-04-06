package com.wonders.controller;


import com.wonders.entity.PackagePriceEntity;
import com.wonders.service.PackagePriceService;
import com.wonders.service.PackageService;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 套餐信息 前端控制器
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageService packageService;
    @Autowired
    private PackagePriceService packagePriceService;

    @RequestMapping("/price/{id}")
    public ReturnMsg packageInfo(@PathVariable Integer id){
        PackagePriceEntity price = packagePriceService.getById(id);
        return ReturnMsg.successTip(price);
    }

}
