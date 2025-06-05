import java.time.LocalDate;


public class Cereales extends Producto implements IAlimento{


    private String tipo;
    private LocalDate caducidad;

    public Cereales(Builder builder) {
        super(builder.marca, builder.precio);
        this.tipo = builder.tipo;
        this.caducidad = builder.caducidad;
    }


    //Setters


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }


    //Getters


    public String getTipo() {
        return this.tipo;
    }

    @Override
    public LocalDate getCaducidad() {
        return this.caducidad;
    }

    @Override
    public  int getCalorias() {

        switch(tipo){

            case "espelta":
                return 5;
            case "maiz":
                return 8;
            default:
                return 15;
        }
    }

    public static class Builder implements ProductoBuilder{

        //Valores Defecto

        private static final String MARCA_DEFAULT = "Hacendado";
        private static final float PRECIO_DEFAULT = 1.65f;
        private static final String TIPO_DEFAULT = "maiz";
        private static final LocalDate CADUCIDAD_DEFAULT = LocalDate.now().plusYears(1);

        // Atributos

        private String marca = MARCA_DEFAULT;
        private float precio = PRECIO_DEFAULT;
        private String tipo = TIPO_DEFAULT;
        private LocalDate caducidad = CADUCIDAD_DEFAULT;


        public Builder marca(String marca){
            this.marca = marca;
            return this;
        }

        public Builder precio(float precio) {
            if (precio < 0) {
                System.out.println("El precio no puede ser negativo. Se usará el valor por defecto.");
                this.precio = PRECIO_DEFAULT;
            } else {
                this.precio = precio;
            }
            return this;
        }


        public Builder tipo(String tipo){
            this.tipo = tipo;
            return this;
        }

        public Builder caducidad(LocalDate caducidad){
            if (caducidad != null && caducidad.isBefore(LocalDate.now())) {
                System.out.println("La fecha de caducidad está en el pasado. Se usará la fecha por defecto.");
                this.caducidad = CADUCIDAD_DEFAULT;
            } else {
                this.caducidad = caducidad;
            }
            return this;
        }

        @Override
        public String toString(){
            Cereales temp = new Cereales(this);
            return String.format(
                    "Datos Cereales: Marca %s, Precio %.2f€, Tipo: %s, Fecha de Caducidad: %s, Calorías: %d",
                    marca, precio, tipo, caducidad, temp.getCalorias()
            );
        }


        public Cereales build() {

            if (marca == null || marca.isBlank()) {
                marca = MARCA_DEFAULT;
            }


            if (tipo == null || tipo.isBlank()) {
                tipo = TIPO_DEFAULT;
            }



            return new Cereales(this);
        }

    }



    //toString

    @Override
    public String toString(){
        return String.format(
                "Datos Cereales: %s, Tipo: %s, Fecha de Caducidad: %s, Calorias: %s",
                super.toString(), getTipo(), getCaducidad(), getCalorias()
        );
    }
}
