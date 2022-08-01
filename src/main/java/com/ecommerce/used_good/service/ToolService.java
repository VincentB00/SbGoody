package com.ecommerce.used_good.service;

import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.FTPData;

@Service
public class ToolService 
{
    private FTPData ftpData;

    public ToolService()
    {
        
    }

    public void setFtpData(FTPData ftpData)
    {
        this.ftpData = ftpData;
    }

    public FTPData getFtpData()
    {
        return this.ftpData;
    }
}
