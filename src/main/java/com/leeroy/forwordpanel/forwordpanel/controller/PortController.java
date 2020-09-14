package com.leeroy.forwordpanel.forwordpanel.controller;

import com.leeroy.forwordpanel.forwordpanel.common.WebCurrentData;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dto.PageRequest;
import com.leeroy.forwordpanel.forwordpanel.model.Port;
import com.leeroy.forwordpanel.forwordpanel.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("port")
@Controller
public class PortController {

    @Autowired
    private PortService portService;


    @ResponseBody
    @PostMapping("getPage")
    public ApiResponse getUserPortList(@RequestBody PageRequest pageRequest) {
        return ApiResponse.ok(portService.findList(pageRequest));
    }

    @ResponseBody
    @GetMapping("getFreePortList")
    public ApiResponse getFreePortList() {
        return ApiResponse.ok(portService.findFreePortList());
    }


    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResponse saveUserPort(@RequestBody Port port) {
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        return portService.save(port);
    }

    @ResponseBody
    @GetMapping("delete")
    public ApiResponse delete(Integer id) {
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        return portService.delete(id);
    }


}
