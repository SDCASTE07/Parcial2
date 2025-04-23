package com.example.ParcialSC.Service;

import com.example.ParcialSC.Model.Entrenador;
import com.example.ParcialSC.Model.Equipo;
import com.example.ParcialSC.Repository.EntrenadorRepository;
import com.example.ParcialSC.Repository.EquipoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    // CRUD Básico
    @Transactional
    public List<Entrenador> getAllEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @Transactional
    public Entrenador getEntrenadorById(Integer id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con ID: " + id));
    }

    @Transactional
    public Entrenador createEntrenador(Entrenador entrenador) {
        // Validar que el equipo exista
        Equipo equipo = equipoRepository.findById(entrenador.getEquipo().getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + entrenador.getEquipo().getIdEquipo()));

        entrenador.setEquipo(equipo);
        return entrenadorRepository.save(entrenador);
    }

    @Transactional
    public Entrenador updateEntrenador(Integer id, Entrenador entrenadorDetails) {
        Entrenador entrenador = getEntrenadorById(id);

        // Actualizar campos
        entrenador.setNombre(entrenadorDetails.getNombre());
        entrenador.setEspecialidad(entrenadorDetails.getEspecialidad());

        // Validar equipo si es diferente
        if (!entrenador.getEquipo().getIdEquipo().equals(entrenadorDetails.getEquipo().getIdEquipo())) {
            Equipo nuevoEquipo = equipoRepository.findById(entrenadorDetails.getEquipo().getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Nuevo equipo no encontrado"));
            entrenador.setEquipo(nuevoEquipo);
        }

        return entrenadorRepository.save(entrenador);
    }

    @Transactional
    public void deleteEntrenador(Integer id) {
        Entrenador entrenador = getEntrenadorById(id);
        entrenadorRepository.delete(entrenador);
    }

    // Consulta personalizada (requiere método en Repository)
    @Transactional
    public List<Entrenador> getEntrenadoresByEquipoId(Integer idEquipo) {
        return entrenadorRepository.findByEquipoId(idEquipo);
    }
}