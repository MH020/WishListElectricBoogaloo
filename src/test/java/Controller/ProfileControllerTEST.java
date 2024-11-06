package Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.example.wishlistelectricboogaloo.Service.UserService;
import org.example.wishlistelectricboogaloo.Controller.ProfileController;
import org.example.wishlistelectricboogaloo.Model.Product;

import java.util.Collections;
import java.util.List;

public class ProfileControllerTEST {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMarket_returnsMarketView_withProducts() {
        int marketId = 1;
        List<Product> products = List.of(new Product());
        when(userService.getAllProducts(marketId)).thenReturn(products);

        String viewName = profileController.getMarket(marketId, model);

        assertEquals("market", viewName);
        verify(model).addAttribute("products");
    }

    @Test
    void getMarket_returnsMarketView_withNoProducts() {
        int marketId = 1;
        when(userService.getAllProducts(marketId)).thenReturn(Collections.emptyList());

        String viewName = profileController.getMarket(marketId, model);

        assertEquals("market", viewName);
        verify(model).addAttribute("products");
    }

    @Test
    void getMarket_handlesInvalidMarketId() {
        int marketId = -1;
        when(userService.getAllProducts(marketId)).thenReturn(Collections.emptyList());

        String viewName = profileController.getMarket(marketId, model);

        assertEquals("market", viewName);
        verify(model).addAttribute("products");
    }
}