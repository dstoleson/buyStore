package dhs.buystore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ProductPrice")
public class ProductPrice {

    @Id
    private Long productId;
    private Price price;

    public ProductPrice(Long productId, Price price) {
        this.productId = productId;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductPrice productPrice = (ProductPrice) o;
        return productId != null &&
                productId.equals(productPrice.getProductId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
