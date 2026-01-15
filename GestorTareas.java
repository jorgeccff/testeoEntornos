import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorTareas {
    private List<Tarea> tareas = new ArrayList<>();

    public void agregarTarea(String descripcion) {
        tareas.add(new Tarea(descripcion));
    }

    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    public List<Tarea> obtenerPendientes() {
        return tareas.stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public void eliminarTarea(String descripcion) {
        boolean eliminada = tareas.removeIf(t -> t.getDescripcion().equals(descripcion));
        if (!eliminada) {
            throw new RuntimeException("Tarea no encontrada");
        }
    }
}