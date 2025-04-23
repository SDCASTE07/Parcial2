package com.example.ParcialSC.Controller;

import com.example.ParcialSC.Model.Entrenador;
import com.example.ParcialSC.Service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(name = "Gestión de Entrenadores", description = "Endpoints para operaciones CRUD y consultas de entrenadores")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    // CRUD
    @GetMapping
    @Operation(summary = "Obtener todos los entrenadores")
    public ResponseEntity<List<Entrenador>> getAllEntrenadores() {
        return ResponseEntity.ok(entrenadorService.getAllEntrenadores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un entrenador por ID")
    public ResponseEntity<Entrenador> getEntrenadorById(@PathVariable Integer id) {
        return ResponseEntity.ok(entrenadorService.getEntrenadorById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo entrenador")
    public ResponseEntity<Entrenador> createEntrenador(@RequestBody Entrenador entrenador) {
        return new ResponseEntity<>(entrenadorService.createEntrenador(entrenador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un entrenador existente")
    public ResponseEntity<Entrenador> updateEntrenador(
            @PathVariable Integer id,
            @RequestBody Entrenador entrenadorDetails) {
        return ResponseEntity.ok(entrenadorService.updateEntrenador(id, entrenadorDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un entrenador por ID")
    public ResponseEntity<Void> deleteEntrenador(@PathVariable Integer id) {
        entrenadorService.deleteEntrenador(id);
        return ResponseEntity.noContent().build();
    }

    // Consulta nativa: Entrenadores por equipo
    @GetMapping("/equipo/{idEquipo}")
    @Operation(summary = "Obtener entrenadores por ID de equipo")
    public ResponseEntity<List<Entrenador>> getEntrenadoresByEquipo(
            @PathVariable("idEquipo") Integer idEquipo) {
        return ResponseEntity.ok(entrenadorService.getEntrenadoresByEquipoId(idEquipo));
    }
}
