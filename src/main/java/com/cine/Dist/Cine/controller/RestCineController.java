package com.cine.Dist.Cine.controller;

import Cine.Asientos;
import Cine.Compra;
import Cine.Pelicula;
import Cine.Tanda;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RestCineController {
    private List<Pelicula> peliculas = new ArrayList<>();
    private List<Compra> compras = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Inicializar películas y tandas
        Pelicula pelicula1 = new Pelicula(101, "Los Vengadores");
        Pelicula pelicula2 = new Pelicula(202, "Intensamente2");
        Pelicula pelicula3 = new Pelicula(303, "Oppenheimer");
        Pelicula pelicula4 = new Pelicula(404, "BAD BOYS");

        Tanda tanda1 = new Tanda(1250, "12:50");
        Tanda tanda2 = new Tanda(800, "8:00");

        Tanda tanda3 = new Tanda(1250, "12:50");
        Tanda tanda4 = new Tanda(800, "8:00");

        Tanda tanda5 = new Tanda(1250, "12:50");
        Tanda tanda6 = new Tanda(800, "8:00");

        Tanda tanda7 = new Tanda(1250, "12:50");
        Tanda tanda8 = new Tanda(800, "8:00");

        pelicula1.getTandas().add(tanda1);
        pelicula1.getTandas().add(tanda2);

        pelicula2.getTandas().add(tanda3);
        pelicula2.getTandas().add(tanda4);

        pelicula3.getTandas().add(tanda5);
        pelicula3.getTandas().add(tanda6);

        pelicula4.getTandas().add(tanda7);
        pelicula4.getTandas().add(tanda8);

        peliculas.add(pelicula1);
        peliculas.add(pelicula2);
        peliculas.add(pelicula3);
        peliculas.add(pelicula4);
    }

    @GetMapping("/peliculas")
    public ResponseEntity<List<Pelicula>> getPeliculas() {
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/peliculas/{peliculaId}/tanda/{tandaId}")
    public ResponseEntity<Tanda> getTandaByPeliculaIdAndTandaId(@PathVariable int peliculaId, @PathVariable int tandaId) {
        try {
            for (Pelicula pelicula : peliculas) {
                if (pelicula.getId() == peliculaId) {
                    for (Tanda tanda : pelicula.getTandas()) {
                        if (tanda.getId() == tandaId) {
                            return ResponseEntity.ok(tanda);
                        }
                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/peliculas/{peliculaId}/tanda/{tandaId}/asientos/ocupar")
    public ResponseEntity<String> ocuparAsientos(@PathVariable int peliculaId, @PathVariable int tandaId, @RequestBody List<String> asientos) {
        String validationError = validateAsientos(asientos);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        try {
            for (Pelicula pelicula : peliculas) {
                if (pelicula.getId() == peliculaId) {
                    for (Tanda tanda : pelicula.getTandas()) {
                        if (tanda.getId() == tandaId) {
                            for (String asientoIdentificador : asientos) {
                                boolean asientoEncontrado = false;
                                for (Asientos asiento : tanda.getAsientos()) {
                                    if (asiento.getIdentificador().equals(asientoIdentificador)) {
                                        asiento.setOcupado(true);
                                        asientoEncontrado = true;
                                        break;
                                    }
                                }
                                if (!asientoEncontrado) {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Asiento " + asientoIdentificador + " no encontrado");
                                }
                            }
                            return ResponseEntity.ok("Asientos ocupados correctamente");
                        }
                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tanda no encontrada");
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al ocupar asientos");
        }
    }

    @PutMapping("/peliculas/{peliculaId}/tanda/{tandaId}/asientos/actualizar")
    public ResponseEntity<String> actualizarAsientosOcupados(@PathVariable int peliculaId, @PathVariable int tandaId, @RequestBody List<String> asientos) {
        String validationError = validateAsientos(asientos);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        try {
            for (Pelicula pelicula : peliculas) {
                if (pelicula.getId() == peliculaId) {
                    for (Tanda tanda : pelicula.getTandas()) {
                        if (tanda.getId() == tandaId) {
                            for (String asientoIdentificador : asientos) {
                                boolean asientoEncontrado = false;
                                for (Asientos asiento : tanda.getAsientos()) {
                                    if (asiento.getIdentificador().equals(asientoIdentificador)) {
                                        asiento.setOcupado(true);
                                        asientoEncontrado = true;
                                        break;
                                    }
                                }
                                if (!asientoEncontrado) {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Asiento " + asientoIdentificador + " no encontrado");
                                }
                            }
                            return ResponseEntity.ok("Asientos actualizados a ocupado correctamente");
                        }
                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tanda no encontrada");
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar asientos");
        }
    }

    @PostMapping("/compras")
    public ResponseEntity<String> registrarCompra(@RequestBody Compra compra) {
        // Validación manual
        String validationError = validateCompra(compra);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        try {
            compras.add(compra);
            return ResponseEntity.ok("Compra registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la compra");
        }
    }

    @GetMapping("/compras")
    public ResponseEntity<List<Compra>> getCompras() {
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/compras/{numeroOrden}")
    public ResponseEntity<Compra> getCompraByNumeroOrden(@PathVariable int numeroOrden) {
        if (numeroOrden <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            for (Compra compra : compras) {
                if (compra.getNumeroOrden() == numeroOrden) {
                    return ResponseEntity.ok(compra);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String validateCompra(Compra compra) {
        // Validación del número de orden (debe ser un número positivo)
        if (compra.getNumeroOrden() <= 0) {
            return "Número de orden debe ser un número positivo";
        }
        // Validación de nombre de la película (cadena no vacía)
        if (compra.getNombrePelicula() == null || compra.getNombrePelicula().isEmpty()) {
            return "Nombre de la película es requerido";
        }
        // Validación de la lista de asientos (no vacía y todos los elementos son cadenas no vacías)
        if (compra.getAsientos() == null || compra.getAsientos().isEmpty()) {
            return "Lista de asientos es requerida";
        } else {
            for (String asiento : compra.getAsientos()) {
                if (asiento == null || asiento.isEmpty()) {
                    return "Cada asiento debe ser una cadena no vacía";
                }
            }
        }
        // Validación del nombre completo (cadena no vacía)
        if (compra.getNombreCompleto() == null || compra.getNombreCompleto().isEmpty()) {
            return "Nombre completo es requerido";
        }
        // Validación del correo (cadena no vacía y debe contener '@')
        if (compra.getCorreo() == null || compra.getCorreo().isEmpty() || !compra.getCorreo().contains("@")) {
            return "Correo válido es requerido";
        }
        // Validación de la hora (cadena no vacía)
        if (compra.getTime() == null || compra.getTime().isEmpty()) {
            return "Hora es requerida";
        }
        // Validación del total (debe ser un número positivo)
        if (compra.getTotal() <= 0) {
            return "Total debe ser un número positivo";
        }
        return null; // No hay errores
    }

    private String validateAsientos(List<String> asientos) {
        if (asientos == null || asientos.isEmpty()) {
            return "Lista de asientos es requerida";
        } else {
            for (String asiento : asientos) {
                if (asiento == null || asiento.isEmpty()) {
                    return "Cada asiento debe ser una cadena no vacía";
                }
            }
        }
        return null;
    }
}
