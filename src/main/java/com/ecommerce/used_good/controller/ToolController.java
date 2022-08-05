package com.ecommerce.used_good.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.FTPData;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.ToolService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/tools")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class ToolController 
{
    @Autowired
    ToolService toolService;

    @PutMapping("/ftp")
    public Response setFtpData(@RequestBody FTPData ftpData)
    {
        toolService.setFtpData(ftpData);
        return new Response(true);
    }

    @GetMapping("/ftp")
    public FTPData getFtpData()
    {
        return this.toolService.getFtpData();
    }
}
