package shop.mtcoding.buyer10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer10.model.Product;
import shop.mtcoding.buyer10.model.ProductRepository;
import shop.mtcoding.buyer10.model.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Transactional
    public int 구매하기(int productId, int principalId, int count) {
        // 상품목록 존재 확인
        Product product = productRepository.findById(productId);
        if (product == null) {
            return -1;
        }

        // 재고 확인
        if (product.getQty() < count) {
            return -1;
        }

        // product_tb 반영 (update)
        int res1 = productRepository.updateById(productId, product.getName(), product.getPrice(),
                product.getQty() - count);
        if (res1 != 1) {
            return -1;
        }

        // purchase_tb 반영 (insert)
        int res2 = purchaseRepository.insert(principalId, productId, count);
        if (res2 != 1) {
            return -1;
        }

        return 1;

    }
}
