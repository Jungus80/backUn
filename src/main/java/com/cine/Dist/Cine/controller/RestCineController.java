package com.cine.Dist.Cine.controller;

import Cine.Asientos;
import Cine.Compra;
import Cine.Pelicula;
import Cine.Tanda;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

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
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{peliculaId}/tanda/{tandaId}")
    public Tanda getTandaByPeliculaIdAndTandaId(@PathVariable int peliculaId, @PathVariable int tandaId) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == peliculaId) {
                for (Tanda tanda : pelicula.getTandas()) {
                    if (tanda.getId() == tandaId) {
                        return tanda;
                    }
                }
                throw new RuntimeException("Tanda no encontrada");
            }
        }
        throw new RuntimeException("Pelicula no encontrada");
    }

    @PostMapping("/peliculas/{peliculaId}/tanda/{tandaId}/asientos/ocupar")
    public String ocuparAsientos(@PathVariable int peliculaId, @PathVariable int tandaId, @RequestBody List<String> asientos) {
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
                                return "Asiento " + asientoIdentificador + " no encontrado";
                            }
                        }
                        return "Asientos ocupados correctamente";
                    }
                }
                return "Tanda no encontrada";
            }
        }
        return "Pelicula no encontrada";
    }

    @PutMapping("/peliculas/{peliculaId}/tanda/{tandaId}/asientos/actualizar")
    public String actualizarAsientosOcupados(@PathVariable int peliculaId, @PathVariable int tandaId, @RequestBody List<String> asientos) {
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
                                return "Asiento " + asientoIdentificador + " no encontrado";
                            }
                        }
                        return "Asientos actualizados a ocupado correctamente";
                    }
                }
                return "Tanda no encontrada";
            }
        }
        return "Pelicula no encontrada";
    }

    @PostMapping("/compras")
    public String registrarCompra(@RequestBody Compra compra) {
        compras.add(compra);
        return "Compra registrada correctamente";
    }

    @GetMapping("/compras")
    public List<Compra> getCompras() {
        return compras;
    }

    @GetMapping("/compras/{numeroOrden}")
    public Compra getCompraByNumeroOrden(@PathVariable int numeroOrden) {
        for (Compra compra : compras) {
            if (compra.getNumeroOrden() == numeroOrden) {
                return compra;
            }
        }
        throw new RuntimeException("Compra no encontrada");
    }
}
