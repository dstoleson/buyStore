package dhs.buystore.service;

import dhs.buystore.model.ProductDetail;
import dhs.buystore.repo.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * A mock REST client for calling the Product Detail Service.
 *
 * Actual implementation wraps REDIS to simulate the REST calls.
 */
@Service
public class ProductDetailService {

    private ProductDetailRepository productDetailRepository;

    /**
     * init data
     */
    @PostConstruct
    public void init() {
        productDetailRepository.deleteAll();
        productDetailRepository.save(new ProductDetail(1L, "Product 1"));
        productDetailRepository.save(new ProductDetail(2L, "Product 2"));
        productDetailRepository.save(new ProductDetail(3L, "Product 3"));
        productDetailRepository.save(new ProductDetail(4L, "Product 4"));
        productDetailRepository.save(new ProductDetail(5L, "Product 5"));
    }

    /**
     * Fetch the product detail from the ProductDetail REST service, simulated using REDIS
     * @param productId id of the product
     * @return An Optional<ProductDetail>, present if found, empty if not found
     */
    public Optional<ProductDetail> getProductDetail(Long productId) {
        return productDetailRepository.findById(productId);
    }

    @Autowired
    public void setProductDetailRepository(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

}
