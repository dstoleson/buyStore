package dhs.buystore.service;

import dhs.buystore.model.Price;
import dhs.buystore.model.ProductDetail;
import dhs.buystore.model.ProductInfo;
import dhs.buystore.model.ProductPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDetailService productDetailService;

    @Mock
    private PriceService priceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductInfo() {
        when(productDetailService.getProductDetail(1L)).thenReturn(
                Optional.of(new ProductDetail(1L, "Product X")));
        when(priceService.getProductPrice(1L)).thenReturn(
                Optional.of(new ProductPrice(1L, new Price(2.30F, "USD"))));

        Optional<ProductInfo> productInfo = productService.getProductInfo(1L);
        assertTrue(productInfo.isPresent());
        productInfo.ifPresent(p -> {
            assertEquals(p.getId(), 1L);
            assertEquals(p.getName(), "Product X");
            assertEquals(p.getCurrentPrice().getValue(), 2.30F);
            assertEquals(p.getCurrentPrice().getCurrencyCode(), "USD");
        });
    }

    @Test
    public void testGetProductDetailNotFound() {
        when(productDetailService.getProductDetail(1L)).thenReturn(
                Optional.ofNullable(null));
        when(priceService.getProductPrice(1L)).thenReturn(
                Optional.of(new ProductPrice(1L, new Price(2.30F, "USD"))));
        Optional<ProductInfo> productInfo = productService.getProductInfo(1L);
        assertTrue(productInfo.isEmpty());
    }

    @Test
    public void testGetProductPriceNotFound() {
        when(productDetailService.getProductDetail(1L)).thenReturn(
                Optional.of(new ProductDetail(1L, "Product X")));
        when(priceService.getProductPrice(1L)).thenReturn(Optional.ofNullable(null));
        Optional<ProductInfo> productInfo = productService.getProductInfo(1L);
        assertTrue(productInfo.isEmpty());
    }

    @Test
    public void testUpdateProductInfo() {
        when(productDetailService.getProductDetail(1L)).thenReturn(
                Optional.of(new ProductDetail(1L, "Product X")));
        when(priceService.updateProductPrice(1L, new Price(3.50F, "EUR"))).thenReturn(
                Optional.of(new ProductPrice(1L, new Price(3.50F, "EUR"))));
        Optional<ProductInfo> productInfo = productService.updateProductInfo(1L, new ProductInfo(1L, "Product X", new Price(3.50F, "EUR")));
        assertTrue(productInfo.isPresent());
        productInfo.ifPresent(p -> {
            assertEquals(p.getId(), 1L);
            assertEquals(p.getName(), "Product X");
            assertEquals(p.getCurrentPrice().getValue(), 3.50F);
            assertEquals(p.getCurrentPrice().getCurrencyCode(), "EUR");
        });
    }

    @Test
    public void testUpdateProductPriceNotFound() {
        when(productDetailService.getProductDetail(1L)).thenReturn(
                Optional.of(new ProductDetail(1L, "Product X")));
        when(priceService.updateProductPrice(1L, new Price(3.50F, "EUR"))).thenReturn(
                Optional.ofNullable(null));
        Optional<ProductInfo> productInfo = productService.updateProductInfo(1L, new ProductInfo(1L, "Product X", new Price(3.53F, "EUR")));
        assertTrue(productInfo.isEmpty());
    }

    @Test
    public void testUpdateProductDetailNotFound() {
        when(productDetailService.getProductDetail(1L)).thenReturn(
                Optional.ofNullable(null));
        when(priceService.updateProductPrice(1L, new Price(3.50F, "EUR"))).thenReturn(
                Optional.of(new ProductPrice(1L, new Price(3.50F, "EUR"))));
        Optional<ProductInfo> productInfo = productService.updateProductInfo(1L, new ProductInfo(1L, "Product X", new Price(3.53F, "EUR")));
        assertTrue(productInfo.isEmpty());
    }
}
