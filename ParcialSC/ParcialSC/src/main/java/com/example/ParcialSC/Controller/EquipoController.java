package com.example.ParcialSC.Controller;

import com.example.ParcialSC.Model.Equipo;
import com.example.ParcialSC.Model.Jugador;
import com.example.ParcialSC.Service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Gestión de Equipos", description = "Operaciones CRUD y consultas específicas de equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // CRUD Básico
    @GetMapping
    @Operation(summary = "Obtener todos los equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        return ResponseEntity.ok(equipoService.getAllEquipos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Integer id) {
        return ResponseEntity.ok(equipoService.getEquipoById(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo equipo")
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        return new ResponseEntity<>(equipoService.createEquipo(equipo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar equipo existente")
    public ResponseEntity<Equipo> updateEquipo(
            @PathVariable Integer id,
            @RequestBody Equipo equipoDetails) {
        return ResponseEntity.ok(equipoService.updateEquipo(id, equipoDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar equipo por ID")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Integer id) {
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }

    // Consultas Nativas del Parcial
    @GetMapping("/{id}/jugadores")
    @Operation(summary = "Jugadores del equipo")
    public ResponseEntity<List<Jugador>> getJugadoresByEquipo(
            @PathVariable Integer id) {
        return ResponseEntity.ok(equipoService.getJugadoresByEquipoId(id));
    }

    @GetMapping("/{id}/goles-totales")
    @Operation(summary = "Total de goles históricos del equipo")
    public ResponseEntity<Integer> getTotalGolesEquipo(
            @PathVariable Integer id) {
        return ResponseEntity.ok(equipoService.getTotalGolesEquipo(id));
    }

    @GetMapping("/resultados-partidos")
    @Operation(summary = "Resultados de partidos con nombres de equipos")
    public ResponseEntity<List<Object[]>> getResultadosPartidos() {
        return ResponseEntity.ok(equipoService.getResultadosPartidos());
    }
}