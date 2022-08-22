package com.ecommerce.used_good.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.used_good.bean.ReportedItem;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.AuthService;
import com.ecommerce.used_good.util.ConstantType;

@RestController
@RequestMapping("/reports")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class ReportedItemController 
{
    @Autowired
    private AuthService authService;

    @Autowired
    private ReportedItemService reportedItemService;

    @GetMapping
    public List<ReportedItem> getAll()
    {
        List<ReportedItem> list = this.reportedItemService.getAll();
        return list;
    }

    @PostMapping("{itemID}")
    public Response createReport(@PathVariable int itemID ,@RequestBody ReportedItem reportedItem, Authentication authentication)
    {
        User user = this.authService.getCurrentLoginUser(authentication);
        return this.reportedItemService.create(reportedItem, itemID, user);
    }

    @PutMapping
    public Response modifyReport(@RequestBody ReportedItem reportedItem)
    {
        return this.reportedItemService.modifyReport(reportedItem.getId(), reportedItem);
    }

    @DeleteMapping("{reportID}")
    public Response removeReport(@PathVariable int reportID)
    {
        return this.reportedItemService.deleteReport(reportID);
    }
}
