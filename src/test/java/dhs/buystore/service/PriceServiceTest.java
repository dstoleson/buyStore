package dhs.buystore.service;

import dhs.buystore.model.Price;
import dhs.buystore.model.ProductPrice;
import dhs.buystore.repo.ProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    ProductPriceRepository productPriceRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateProductPrice() {
        when(productPriceRepository.findById(1L)).thenReturn(
                Optional.of(new ProductPrice(1L, new Price(2.5F, "USD"))));
        Optional<ProductPrice> productPrice = priceService.updateProductPrice(1L, new Price(3.0F, "EUR"));
        assertTrue(productPrice.isPresent());
        assertEquals(1L, productPrice.get().getProductId());
        assertEquals(3.0F, productPrice.get().getPrice().getValue());
        assertEquals("EUR", productPrice.get().getPrice().getCurrencyCode());
    }

    @Test
    public void testUpdateProductPriceNotFound() {
        when(productPriceRepository.findById(1L)).thenReturn(
                Optional.ofNullable(null));
        Optional<ProductPrice> productPrice = priceService.updateProductPrice(1L, new Price(3.0F, "EUR"));
        assertTrue(productPrice.isEmpty());
    }
}