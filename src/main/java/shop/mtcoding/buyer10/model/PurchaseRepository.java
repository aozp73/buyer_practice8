package shop.mtcoding.buyer10.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseRepository {

    public int insert(@Param("userId") int userId, @Param("productId") int productId, @Param("count") int count);
}
