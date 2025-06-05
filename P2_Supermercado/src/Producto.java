public abstract class Producto {

    private String marca;
    private float precio;

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public float getPrecio() {
        return precio;
    }

    public Producto (String marca, float precio){
        this.setMarca(marca);
        this.setPrecio(precio);
    }

    @Override
    public String toString(){
        return String.format(
                "Marca: %s, Precio: %.2f€",
                getMarca(), getPrecio()
        );
    }


}
