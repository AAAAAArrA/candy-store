package com.example.chocolateshop.controllers;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.services.OrderDetailsService;
import com.example.chocolateshop.services.OrderService;
import com.example.chocolateshop.services.implementation.ProductServiceImpl;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final ProductServiceImpl productService;
    public OrderController(OrderService orderService, OrderDetailsService orderDetailsService, ProductServiceImpl productService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
    }



    @GetMapping
    public String orders(Model model){

        return getPaginated(1, model);
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginatedOrders(@PathVariable (value = "pageNo") int pageNo,
                                      Model model, Principal principal){
        int pageSize = 30;
        Page<Order> page = orderService.getPaginatedOrdersBuUser(principal.getName(),
                pageNo, pageSize);
        List<Order> orderList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("order", orderList);
        return "orderList";
    }
    @GetMapping("/report-1/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 20;
        Page<Order> page = orderService.getAllPaginatedOrders(pageNo, pageSize);
        List<Order> orders = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("orderList", orders);
        return "report-1";
    }

    @GetMapping("/firstReport")
    public String invokeFilter(){
        return "dateFilter";
    }

    @PostMapping("/datesF")
    public String getDatesF(@RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate){
        return "redirect:/orders/filter/page/1/"+startDate+"/"+endDate;
    }


    @GetMapping("/filter/page/{pageNo}/{start}/{end}")
    public String filter(@PathVariable(value = "pageNo") int pageNo,@PathVariable(value = "start")String  start,
                         @PathVariable(value = "end") String  end, Model model){
        int pageSize = 15;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<Order> orders = orderService.findFilteredOrders(startDate, endDate);
        PagedListHolder page = new PagedListHolder(orders);
        page.setPageSize(15);
        model.addAttribute("filtered", page.getPageList());
        model.addAttribute("currentPage", pageNo);
        return "firstReport";
    }

}
