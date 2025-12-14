package com.example.ExamenMocRPF.Service;
import com.example.ExamenMocRPF.entry.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.ExamenMocRPF.repository.ProductoRepository;
import com.example.ExamenMocRPF.service.ProductoServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarProductos() {
        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Test");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Producto> result = productoService.findProducto(1L);

        assertNotNull(result);
        assertEquals("Test", result.get().getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    /*@Test
    void testBuscarPorId() {
        Producto producto = new Producto(1L, "Arroz", 2.0);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Arroz", resultado.getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testGuardarProducto() {
        Producto producto = new Producto(1L, "Huevos", 3.5);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.guardarProducto(producto);

        assertEquals("Huevos", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testEliminarProducto() {
        productoService.eliminarProducto(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }*/
}

