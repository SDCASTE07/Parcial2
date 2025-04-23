package com.example.ParcialSC.Controller;

import com.example.ParcialSC.Model.EstadisticasJugador;
import com.example.ParcialSC.Service.EstadisticasJugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadisticas")
@Tag(name = "Gestión de Estadísticas", description = "Operaciones CRUD y consultas de estadísticas de jugadores")
public class EstadisticasJugadorController {

    @Autowired
    private EstadisticasJugadorService estadisticasService;

    // CRUD Básico
    @PostMapping
    @Operation(summary = "Crear nueva estadística")
    public ResponseEntity<EstadisticasJugador> createEstadistica(@RequestBody EstadisticasJugador estadistica) {
        return new ResponseEntity<>(estadisticasService.createEstadistica(estadistica), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las estadísticas")
    public ResponseEntity<List<EstadisticasJugador>> getAllEstadisticas() {
        return ResponseEntity.ok(estadisticasService.getAllEstadisticas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estadística por ID")
    public ResponseEntity<EstadisticasJugador> getEstadisticaById(@PathVariable Integer id) {
        return ResponseEntity.ok(estadisticasService.getEstadisticaById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estadística existente")
    public ResponseEntity<EstadisticasJugador> updateEstadistica(
            @PathVariable Integer id,
            @RequestBody EstadisticasJugador estadisticaDetails) {
        return ResponseEntity.ok(estadisticasService.updateEstadistica(id, estadisticaDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estadística por ID")
    public ResponseEntity<Void> deleteEstadistica(@PathVariable Integer id) {
        estadisticasService.deleteEstadistica(id);
        return ResponseEntity.noContent().build();
    }

    // Consultas Específicas
    @GetMapping("/jugador/{idJugador}")
    @Operation(summary = "Obtener estadísticas por jugador")
    public ResponseEntity<List<EstadisticasJugador>> getByJugador(@PathVariable Integer idJugador) {
        return ResponseEntity.ok(estadisticasService.getEstadisticasByJugador(idJugador));
    }

    @GetMapping("/partido/{idPartido}")
    @Operation(summary = "Obtener estadísticas por partido")
    public ResponseEntity<List<EstadisticasJugador>> getByPartido(@PathVariable Integer idPartido) {
        return ResponseEntity.ok(estadisticasService.getEstadisticasByPartido(idPartido));
    }

    @GetMapping("/jugador/{idJugador}/goles-totales")
    @Operation(summary = "Obtener total de goles de un jugador")
    public ResponseEntity<Integer> getTotalGolesJugador(@PathVariable Integer idJugador) {
        return ResponseEntity.ok(estadisticasService.getTotalGolesJugador(idJugador));
    }
}
