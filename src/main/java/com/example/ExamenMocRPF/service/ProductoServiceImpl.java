package com.example.ExamenMocRPF.service;
import com.example.ExamenMocRPF.entry.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ExamenMocRPF.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAllProductos() {
        return this.productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findProducto(Long id) {
        return this.productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByCategoria(String categoria) {
        return this.productoRepository.findByCategoria(categoria);
    }

    @Override
    public List<Producto> findByPrecio(float precio) {
        return this.productoRepository.findByPrecio(precio);
    }

    @Override
    public List<Producto> findByPrecioAndCategoria(float precio, String categoria) {
        return this.productoRepository.findByPrecioAndCategoria(precio, categoria);
    }

    @Override
    public Producto addProducto(Producto producto) {
        return this.productoRepository.save(producto);
    }

    @Override
    public void eliminarProductoById(Long productoId) {
        Optional<Producto> producto = this.productoRepository.findById(productoId);
        producto.ifPresent(value -> this.productoRepository.delete(value));
    }

    @Override
    public Producto modificarProducto(Long productoId, Producto producto) {
        Optional<Producto> productoBbdd = this.productoRepository.findById(productoId);
        if (productoBbdd.isPresent()){
            return this.productoRepository.save(producto);
        }
        return null;
    }
}
