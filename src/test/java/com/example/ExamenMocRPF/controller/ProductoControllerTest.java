package com.example.ExamenMocRPF.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ProductoController;
import entry.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import service.ProductoService;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void listaProductos() throws Exception {
        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Test product");

        when(productoService.findAllProductos()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/api/products")) // ajusta la ruta a la tuya
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("Test product"));

        verify(productoService, times(1)).findAllProductos();
    }

    /*private ObjectMapper mapper = new ObjectMapper();

    private Producto p1;
    private Producto p2;

    @BeforeEach
    void setUp() {
        p1 = new Producto(1L, "Pan", 1.0);
        p2 = new Producto(2L, "Leche", 0.8);
    }

    @Test
    void testListarProductos() throws Exception {
        when(productoService.listarProductos()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(productoService, times(1)).listarProductos();
    }

    @Test
    void testBuscarProducto() throws Exception {
        when(productoService.buscarPorId(1L)).thenReturn(p1);

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pan"));

        verify(productoService, times(1)).buscarPorId(1L);
    }

    @Test
    void testGuardarProducto() throws Exception {
        when(productoService.guardarProducto(any())).thenReturn(p1);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pan"));

        verify(productoService, times(1)).guardarProducto(any());
    }

    @Test
    void testEliminarProducto() throws Exception {
        doNothing().when(productoService).eliminarProducto(1L);

        mockMvc.perform(delete("/productos/1"))
                .andExpect(status().isOk());

        verify(productoService, times(1)).eliminarProducto(1L);
    }*/
}
