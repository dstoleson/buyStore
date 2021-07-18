package dhs.buystore.service;

import dhs.buystore.model.Price;
import dhs.buystore.model.ProductPrice;
import dhs.buystore.repo.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * A mock REST client for calling the Price Service.
 *
 * Actual implementation wraps REDIS to simulate the REST calls.
 */
@Service
public class PriceService {

    private ProductPriceRepository productPriceRepository;

    /**
     * init data
     */
    @PostConstruct
    public void init() {
        productPriceRepository.deleteAll();
        productPriceRepository.save(new ProductPrice(1L, new Price(2.30F, "USD")));
        productPriceRepository.save(new ProductPrice(2L, new Price(2.40F, "USD")));
        productPriceRepository.save(new ProductPrice(3L, new Price(2.50F, "USD")));
        productPriceRepository.save(new ProductPrice(4L, new Price(2.60F, "USD")));
        productPriceRepository.save(new ProductPrice(5L, new Price(2.75F, "USD")));
    }

    /**
     * Fetch the product price from the ProductPrice REST service, simulated using REDIS
     * @param productId id of the product
     * @return An Optional<ProductPrice>, present if found, empty if not found
     */
    public Optional<ProductPrice> getProductPrice(Long productId) {
        return productPriceRepository.findById(productId);
    }

    public Optional<ProductPrice> updateProductPrice(Long productId, Price update) {
        Optional<ProductPrice> productPrice = productPriceRepository.findById(productId);
        if (productPrice.isPresent()) {
            ProductPrice price = productPrice.get();
            price.getPrice().setValue(update.getValue());
            price.getPrice().setCurrencyCode(update.getCurrencyCode());
            productPriceRepository.save(price);
        }
        return productPrice;
    }

    @Autowired
    public void setProductPriceRepository(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

}
