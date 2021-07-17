package dhs.buystore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductInfoRepository productInfoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductInfo() {
        when(productInfoRepository.findById(11111L)).thenReturn(
                Optional.of(new ProductInfo(11111L, "Product X", new Price(2.30F, "USD"))));
        Optional<ProductInfo> productInfo = productService.getProductInfo(11111L);
        assertTrue(productInfo.isPresent());
        productInfo.ifPresent(p -> {
            assertEquals(p.getId(), 11111L);
            assertEquals(p.getName(), "Product X");
            assertEquals(p.getCurrentPrice().getValue(), 2.30F);
            assertEquals(p.getCurrentPrice().getCurrencyCode(), "USD");
        });
    }

    @Test
    public void testGetProductInfoNotFound() {
        when(productInfoRepository.findById(11111L)).thenReturn(
                Optional.ofNullable(null));
        Optional<ProductInfo> productInfo = productService.getProductInfo(11111L);
        assertTrue(productInfo.isEmpty());
    }

    @Test
    public void testUpdateProductInfo() {
        when(productInfoRepository.findById(11111L)).thenReturn(
                Optional.of(new ProductInfo(11111L, "Product X", new Price(2.30F, "USD"))));
        Optional<ProductInfo> productInfo = productService.updateProductInfo(11111L, new ProductInfo(11111L, "Product X", new Price(3.53F, "EUR")));
        assertTrue(productInfo.isPresent());
        productInfo.ifPresent(p -> {
            assertEquals(p.getId(), 11111L);
            assertEquals(p.getName(), "Product X");
            assertEquals(p.getCurrentPrice().getValue(), 3.53F);
            assertEquals(p.getCurrentPrice().getCurrencyCode(), "EUR");
        });
    }

    @Test
    public void testUpdateProductInfoNotFound() {
        when(productInfoRepository.findById(11111L)).thenReturn(
                Optional.ofNullable(null));
        Optional<ProductInfo> productInfo = productService.updateProductInfo(11111L, new ProductInfo(11111L, "Product X", new Price(3.53F, "EUR")));
        assertTrue(productInfo.isEmpty());
    }
}
