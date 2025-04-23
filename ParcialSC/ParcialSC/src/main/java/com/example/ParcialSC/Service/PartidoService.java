package com.example.ParcialSC.Service;

import com.example.ParcialSC.Model.Equipo;
import com.example.ParcialSC.Model.Partido;
import com.example.ParcialSC.Repository.EquipoRepository;
import com.example.ParcialSC.Repository.PartidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    // CRUD Básico
    @Transactional
    public List<Partido> getAllPartidos() {
        return partidoRepository.findAll();
    }

    @Transactional
    public Partido getPartidoById(Integer id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));
    }

    @Transactional
    public Partido createPartido(Partido partido) {
        // Validar equipos local y visitante
        Equipo local = equipoRepository.findById(partido.getEquipoLocal().getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));

        Equipo visita = equipoRepository.findById(partido.getEquipoVisita().getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));

        partido.setEquipoLocal(local);
        partido.setEquipoVisita(visita);
        return partidoRepository.save(partido);
    }

    @Transactional
    public Partido updatePartido(Integer id, Partido partidoDetails) {
        Partido partido = getPartidoById(id);

        // Actualizar campos básicos
        partido.setFecha(partidoDetails.getFecha());
        partido.setEstadio(partidoDetails.getEstadio());
        partido.setGolesLocal(partidoDetails.getGolesLocal());
        partido.setGolesVisita(partidoDetails.getGolesVisita());

        // Validar y actualizar equipos si cambian
        if (!partido.getEquipoLocal().getIdEquipo().equals(partidoDetails.getEquipoLocal().getIdEquipo())) {
            Equipo nuevoLocal = equipoRepository.findById(partidoDetails.getEquipoLocal().getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Nuevo equipo local no encontrado"));
            partido.setEquipoLocal(nuevoLocal);
        }

        if (!partido.getEquipoVisita().getIdEquipo().equals(partidoDetails.getEquipoVisita().getIdEquipo())) {
            Equipo nuevaVisita = equipoRepository.findById(partidoDetails.getEquipoVisita().getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Nuevo equipo visitante no encontrado"));
            partido.setEquipoVisita(nuevaVisita);
        }

        return partidoRepository.save(partido);
    }

    @Transactional
    public void deletePartido(Integer id) {
        Partido partido = getPartidoById(id);
        partidoRepository.delete(partido);
    }

    // Consultas Nativas del Parcial
    @Transactional
    public List<Object[]> getResultadosConNombresEquipos() {
        return partidoRepository.getResultadosPartidosConNombres();
    }

    @Transactional
    public Integer getTotalGolesEquipo(Integer idEquipo) {
        return partidoRepository.getTotalGolesEquipo(idEquipo);
    }
}