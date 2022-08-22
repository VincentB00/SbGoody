package com.ecommerce.used_good.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.used_good.bean.Item;
import com.ecommerce.used_good.bean.ReportedItem;
import com.ecommerce.used_good.bean.User;
import com.ecommerce.used_good.dao.ReportedItemDao;
import com.ecommerce.used_good.http.HttpResponseThrowers;
import com.ecommerce.used_good.http.Response;
import com.ecommerce.used_good.service.ItemService;
import com.ecommerce.used_good.util.ReplacementUtils;

@Service
public class ReportedItemService 
{
    @Autowired
    private ItemService itemService;

    @Autowired
    private ReportedItemDao reportedItemDao;

    public ReportedItem get(int reportedItemID)
    {
        Optional<ReportedItem> optional = this.reportedItemDao.findById(reportedItemID);

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("Invalid or missing Report id");

        return optional.get();
    }

    public List<ReportedItem> getAll()
    {
        return this.reportedItemDao.findAll();
    }

    public Response create(ReportedItem reportedItem, int itemID, User user)
    {
        Item item = this.itemService.getItem(itemID);

        reportedItem.setItem(item);
        reportedItem.setUser(user);

        this.reportedItemDao.save(reportedItem);

        return new Response(true, "A report have been file");
    }

    public Response modifyReport(ReportedItem origin, ReportedItem target)
    {
        target.setUser(null);
        target.setItem(null);

        ReplacementUtils.replaceValue(origin, target);

        this.reportedItemDao.save(origin);

        return new Response(true, "Report have been modified");
    }

    public Response modifyReport(int originID, ReportedItem target)
    {
        ReportedItem reportedItem = this.get(originID);
        return this.modifyReport(reportedItem, target);
    }

    public Response deleteReport(int reportID)
    {
        this.reportedItemDao.deleteById(reportID);
        return new Response(true, "Report have been deleted");
    }
}
