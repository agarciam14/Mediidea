package mediimento;

public class ModelTable {
    String nombre;
    String dosis;
    String presentacion;
    String descripcion;

    public ModelTable(String nombre, String dosis, String presentacion, String descripcion) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
