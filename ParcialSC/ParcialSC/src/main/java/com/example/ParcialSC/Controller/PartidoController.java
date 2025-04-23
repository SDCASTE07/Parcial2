package com.example.ParcialSC.Controller;

import com.example.ParcialSC.Model.Partido;
import com.example.ParcialSC.Service.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@Tag(name = "Gestión de Partidos", description = "Operaciones CRUD y consultas relacionadas con partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    // CRUD Básico
    @GetMapping
    @Operation(summary = "Obtener todos los partidos")
    public ResponseEntity<List<Partido>> getAllPartidos() {
        return ResponseEntity.ok(partidoService.getAllPartidos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener partido por ID")
    public ResponseEntity<Partido> getPartidoById(@PathVariable Integer id) {
        return ResponseEntity.ok(partidoService.getPartidoById(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo partido")
    public ResponseEntity<Partido> createPartido(@RequestBody Partido partido) {
        return new ResponseEntity<>(partidoService.createPartido(partido), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar partido existente")
    public ResponseEntity<Partido> updatePartido(
            @PathVariable Integer id,
            @RequestBody Partido partidoDetails) {
        return ResponseEntity.ok(partidoService.updatePartido(id, partidoDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar partido por ID")
    public ResponseEntity<Void> deletePartido(@PathVariable Integer id) {
        partidoService.deletePartido(id);
        return ResponseEntity.noContent().build();
    }

    // Consultas Nativas del Parcial
    @GetMapping("/resultados")
    @Operation(summary = "Obtener resultados con nombres de equipos")
    public ResponseEntity<List<Object[]>> getResultadosConNombres() {
        return ResponseEntity.ok(partidoService.getResultadosConNombresEquipos());
    }

    @GetMapping("/equipos/{id}/goles-totales")
    @Operation(summary = "Obtener total de goles históricos de un equipo")
    public ResponseEntity<Integer> getTotalGolesEquipo(@PathVariable Integer id) {
        return ResponseEntity.ok(partidoService.getTotalGolesEquipo(id));
    }
}