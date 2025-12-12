package controller;

import entry.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductoService;

import java.util.List;
import java.util.Optional;

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/productos")
    public Producto addProducto(@RequestBody Producto producto) {
        return this.productoService.addProducto(producto);
    }

    @DeleteMapping(value = "/producto/{productoId}")
    public void deleteProducto(@PathVariable Long productoId) {
        this.productoService.eliminarProductoById(productoId);
    }

    @PutMapping(value = "/producto/{productoId}")
    public Producto modificarProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return this.productoService.modificarProducto(productoId,producto);
    }


    @GetMapping(value = "/productos")
    public List<Producto> getProductos(@RequestParam(defaultValue = "0.0") Float precio,
                                       @RequestParam(defaultValue = "") String categoria) {

        // Si no se indica ni precio ni categoría -> obtener todos los productos
        if (precio == 0.0 && categoria.isEmpty()) {
            return this.productoService.findAllProductos();
        }

        // Si se indica el precio -> obtener los productos con ese precio
        if (precio != 0.0 && categoria.isEmpty()) {
            return this.productoService.findByPrecio(precio);
        }

        // Si se indica la categoría -> obtener los productos con esa categoría
        if (precio == 0.0 && !categoria.isEmpty()) {
            return this.productoService.findByCategoria(categoria);
        }

        // Si se indican ambos (precio y categoría), por si se quiere soportar
        return this.productoService.findByPrecioAndCategoria(precio, categoria);
    }


    @GetMapping(value = "/producto/{productoId}")
    public Optional<Producto> getProducto(@PathVariable Long productoId) {
        return this.productoService.findProducto(productoId);
    }

}