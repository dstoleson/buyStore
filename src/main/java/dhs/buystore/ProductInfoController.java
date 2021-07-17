package dhs.buystore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
/**
 * REST Controller for retrieving and update Product information
 */
public class ProductInfoController {

    private ProductService productService;

    @GetMapping("/products/{id}")
    public ProductInfo getProduct(@PathVariable Long id) {
        Optional<ProductInfo> productInfo = productService.getProductInfo(id);
        if (productInfo.isPresent()) {
            return productInfo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/{id}")
    public ProductInfo updateProduct(@PathVariable Long id, @RequestBody ProductInfo update) {
        Optional<ProductInfo> productInfo =  productService.updateProductInfo(id, update);
        if (productInfo.isPresent()) {
            return productInfo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
