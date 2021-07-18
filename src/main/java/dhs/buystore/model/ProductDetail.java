package dhs.buystore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ProductDetail")
public class ProductDetail {

    @Id
    private Long productId;
    private String name;

    public ProductDetail(Long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDetail productDetail = (ProductDetail) o;
        return productId != null &&
                productId.equals(productDetail.getProductId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
