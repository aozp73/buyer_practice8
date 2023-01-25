package shop.mtcoding.buyer10.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer10.model.ProductRepository;
import shop.mtcoding.buyer10.model.User;
import shop.mtcoding.buyer10.service.PurchaseService;

@Controller
public class PurchaseController {

    @Autowired
    HttpSession session;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/purchase/insert")
    public String insert(int productId, int count) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        // 트랜잭션
        int res = purchaseService.구매하기(productId, principal.getId(), count);
        if (res == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/";
    }
}
