package dhs.buystore.service;

import dhs.buystore.model.ProductDetail;
import dhs.buystore.model.ProductInfo;
import dhs.buystore.model.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
/**
 * Service for retrieving and storing Product information
 */
public class ProductService {

    private ProductDetailService productDetailService;
    private PriceService priceService;

    /**
     * Get the product info for the given product.
     * @param productId The id of the product
     * @return an Optional<ProductInfo> object
     */
    public Optional<ProductInfo> getProductInfo(Long productId) {
        Optional<ProductDetail> productDetail = productDetailService.getProductDetail(productId);
        Optional<ProductPrice> productPrice = priceService.getProductPrice(productId);
        Optional<ProductInfo> productInfo;

        if (productDetail.isPresent() && productPrice.isPresent()) {
            productInfo = Optional.of(new ProductInfo(productId, productDetail.get().getName(), productPrice.get().getPrice()));
        } else {
            productInfo = Optional.ofNullable(null);
        }
        return productInfo;
    }

    /**
     * Update the product info
     * @param productId The id of the product to update
     * @param update A ProductInfo containing the info to update
     * @return a Optional<ProductInfo> object with updates
     */
    public Optional<ProductInfo> updateProductInfo(Long productId, ProductInfo update) {
        Optional<ProductInfo> productInfo;
        Optional<ProductPrice> productPrice = priceService.updateProductPrice(productId, update.getCurrentPrice());

        if (productPrice.isPresent()) {
            Optional<ProductDetail> productDetail = productDetailService.getProductDetail(productId);
            if (productDetail.isPresent()) {
                productInfo = Optional.of(new ProductInfo(productId, productDetail.get().getName(), productPrice.get().getPrice()));
            } else {
                productInfo = Optional.ofNullable(null);
            }
        } else {
            productInfo = Optional.ofNullable(null);
        }
        return productInfo;
    }

    @Autowired
    public void setProductDetailService(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @Autowired
    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }
}
