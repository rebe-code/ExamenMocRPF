package com.example.ExamenMocRPF.controller;

import com.example.ExamenMocRPF.entry.Producto;
import com.example.ExamenMocRPF.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Configuration
    static class MockConfig {
        @Bean
        public ProductoService productoService() {
            return Mockito.mock(ProductoService.class);
        }
    }

    @Autowired
    private ProductoService productoService;

    @Test
    void listaProductos() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test product");

        when(productoService.findAllProductos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Test product"));

        verify(productoService).findAllProductos();
    }
}

