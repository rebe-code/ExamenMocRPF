package com.example.ExamenMocRPF.controller;

import com.example.ExamenMocRPF.entry.Producto;
import com.example.ExamenMocRPF.repository.ProductoRepository;
import com.example.ExamenMocRPF.service.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl service;

    @Test
    void listaProductos() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test producto");

        // Simulamos que el repositorio devuelve el producto
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Llamamos al servicio
        Optional<Producto> resultado = service.findProducto(1L);

        // Comprobamos que el resultado es el esperado
        assertEquals(producto, resultado.get());

        // Verificamos que se llamó una vez al repositorio
        Mockito.verify(productoRepository, Mockito.times(1)).findById(1L);
    }
}



