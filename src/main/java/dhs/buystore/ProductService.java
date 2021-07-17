package dhs.buystore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
/**
 * Service for retrieving and storing Product information
 */
public class ProductService {

    private ProductInfoRepository productInfoRepository;

    @PostConstruct
    /**
     * init data
     */
    public void init() {
        productInfoRepository.deleteAll();
        productInfoRepository.save(new ProductInfo(1L, "Product 1", new Price(2.30F, "USD")));
        productInfoRepository.save(new ProductInfo(2L, "Product 2", new Price(2.40F, "USD")));
        productInfoRepository.save(new ProductInfo(3L, "Product 3", new Price(2.50F, "USD")));
        productInfoRepository.save(new ProductInfo(4L, "Product 4", new Price(2.60F, "USD")));
        productInfoRepository.save(new ProductInfo(5L, "Product 5", new Price(2.75F, "USD")));
    }

    /**
     * Get the product info for the given product.
     * @param productId The id of the product
     * @return an Optional<ProductInfo> object
     */
    public Optional<ProductInfo> getProductInfo(Long productId) {
        return productInfoRepository.findById(productId);
    }

    /**
     * Update the product info
     * @param productId The id of the produt to update
     * @param update A ProductInfo containing the info to update
     * @return a Optional<ProductInfo> object with updates
     */
    public Optional<ProductInfo> updateProductInfo(Long productId, ProductInfo update) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        if (productInfo.isPresent()) {
            ProductInfo x = productInfo.get();
            x.setCurrentPrice(update.getCurrentPrice());
            productInfoRepository.save(x);
        }
        return productInfo;
    }

    @Autowired
    public void setProductInfoRepository(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }
}
