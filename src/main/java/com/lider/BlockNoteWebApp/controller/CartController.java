package com.lider.BlockNoteWebApp.controller;

import com.lider.BlockNoteWebApp.domain.Order;
import com.lider.BlockNoteWebApp.domain.OrderDetail;
import com.lider.BlockNoteWebApp.domain.Product;
import com.lider.BlockNoteWebApp.domain.User;
import com.lider.BlockNoteWebApp.repos.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lider.BlockNoteWebApp.repos.*;

@Controller
@RequestMapping("/cart") //чтобы в каждом методе отдельно не прописывать
public class CartController {

    @Autowired // This means to get the bean called ProductRepo
    // Which is auto-generated by Spring, we will use it to handle the data
    private OrderRepo OrderRepo;
    @Autowired
    private OrderDetailRepo OrderDetailRepo;

    @GetMapping
    public String cartView(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        Order order = OrderRepo.findByCustomer(user);
        List<OrderDetail> orderDetails = OrderDetailRepo.findAllByOrder(order);

        model.put("order", order);
        model.put("orderdetails", orderDetails);

        return "cart";
    }
}
