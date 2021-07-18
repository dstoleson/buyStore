package dhs.buystore.service;

import dhs.buystore.model.ProductDetail;
import dhs.buystore.repo.ProductDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ProductDetailServiceTest {

    @InjectMocks
    private ProductDetailService productDetailService;

    @Mock
    ProductDetailRepository productDetailRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductDetail() {
        when(productDetailRepository.findById(1L)).thenReturn(
                Optional.of(new ProductDetail(1L, "Product 1")));
        Optional<ProductDetail> productDetail = productDetailService.getProductDetail(1L);
        assertTrue(productDetail.isPresent());
        assertEquals(1L, productDetail.get().getProductId());
        assertEquals("Product 1", productDetail.get().getName());
    }

    @Test
    public void testGetProductDetailNotFound() {
        when(productDetailRepository.findById(1L)).thenReturn(
                Optional.ofNullable(null));
        Optional<ProductDetail> productDetail = productDetailService.getProductDetail(1L);
        assertTrue(productDetail.isEmpty());
    }
}