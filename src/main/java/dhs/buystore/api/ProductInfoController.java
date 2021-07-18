package dhs.buystore.api;

import dhs.buystore.model.ProductInfo;
import dhs.buystore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/")
/**
 * REST Controller for retrieving and update Product information
 */
public class ProductInfoController {

    private ProductService productService;

    @Operation(summary = "Get product info")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found Project info for id", content = @Content(schema = @Schema(implementation = ProductInfo.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "Project info not found for id")})
    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ProductInfo getProduct(@PathVariable Long id) {

        Optional<ProductInfo> productInfo = productService.getProductInfo(id);
        if (productInfo.isPresent()) {
            return productInfo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update product info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Project info for id", content = @Content(schema = @Schema(implementation = ProductInfo.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Project info not found for id")})
    @PutMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
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
