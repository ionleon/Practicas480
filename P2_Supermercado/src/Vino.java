import java.time.LocalDate;

public class Vino extends Producto implements IAlimento, ILiquido, IDesuento{


    private final float gradosAlcohol;
    private final String tipo;
    private final LocalDate caducidad;
    private final float descuento;
    private final float volumen;
    private final String envase;

    public Vino(Builder builder) {

        super(builder.marca, builder.precio);
        this.gradosAlcohol = builder.gradosAlcohol;
        this.tipo = builder.tipo;
        this.caducidad = builder.caducidad;
        this.descuento = builder.descuento;
        this.volumen = builder.volumen;
        this.envase = builder.envase;

        if (gradosAlcohol <= 0) {
            throw new IllegalArgumentException("Grados de alcohol deben ser positivos");
        }

        if (descuento <= 0) {
            throw new IllegalArgumentException("El descuento debe de ser positivo");
        }

        if (volumen <= 0) {
            throw new IllegalArgumentException("El volumen deben ser de positivo");
        }

    }

    //Setters


    @Override
    public void setCaducidad(LocalDate caducidad) {
        throw new UnsupportedOperationException("Caducidad solo se asigna en construcción");
    }

    @Override
    public void setDescuento(float descuento) {
        throw new UnsupportedOperationException("Descuento solo se asigna en construcción");

    }

    @Override
    public void setVolumen(float volumen) {
        throw new UnsupportedOperationException("Volumen solo se asigna en construcción");

    }

    @Override
    public void setEnvase(String envase) {
        throw new UnsupportedOperationException("Envase solo se asigna en construcción");

    }

    //Getters

    public float getGradosAlcohol() {
        return gradosAlcohol;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public LocalDate getCaducidad() {
        return caducidad;
    }

    @Override
    public int getCalorias() {
        return (int) (getGradosAlcohol()*10);
    }

    @Override
    public float getDescuento() {
        return descuento;
    }

    @Override
    public float getVolumen() {
        return volumen;
    }

    @Override
    public String getEnvase() {
        return envase;
    }

    @Override
    public float getPrecioDescuento() {
        return getPrecio()-(getPrecio()*getDescuento());
    }


    //toString


    @Override
    public String toString(){
        return String.format(
                "Datos Vino: %s, Graduacion Alcohol: %.1f%%, Tipo: %s, Fecha de Caducidad: %s, Descuento: %.0f%%, Volumen: %.2fL, Envase: %s, Precio con Descuento: %.2f",
                super.toString(),getGradosAlcohol(),getTipo(),getCaducidad(),getDescuento(),getVolumen(),getEnvase(),getPrecioDescuento()
        );
    }

    //Builder

    public static class Builder implements ProductoBuilder{

        //Valores Defecto

        private static final String MARCA_DEFAULT = "Hacendado";
        private static final float PRECIO_DEFAULT = 15.5f;
        private static final float GRALCOHOL_DEFAULT = 11f ;
        private static final String TIPO_DEFAULT = "Tinto";
        private static final LocalDate CADUCIDAD_DEFAULT = LocalDate.now().plusYears(2);
        private static final float DESCUENTO_DEFAULT = 0.2f;
        private static final float VOLUMEN_DEFAULT = 1f;
        private static final String ENVASE_DEFAULT = "Cristal";

        // Atributos

        private String marca = MARCA_DEFAULT;
        private float precio = PRECIO_DEFAULT;
        private float gradosAlcohol = GRALCOHOL_DEFAULT;
        private String tipo = TIPO_DEFAULT;
        private LocalDate caducidad = CADUCIDAD_DEFAULT;
        private float descuento = DESCUENTO_DEFAULT;
        private float volumen = VOLUMEN_DEFAULT;
        private String envase = ENVASE_DEFAULT;

        public Builder marca(String marca){
            this.marca = marca;
            return this;
        }

        public Builder precio(float precio){
            if (precio < 0) {
                System.out.println("El precio no puede ser negativo. Se usará el valor por defecto.");
                this.precio = PRECIO_DEFAULT;
            } else {
                this.precio = precio;
            }
            return this;
        }

        public Builder gradosAlcohol(float gradosAlcohol){
            this.gradosAlcohol = gradosAlcohol;
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


        public Builder descuento(float descuento){
            if (descuento < 0 || descuento > 100) {
                System.out.println("El descuento debe estar entre 0 y 100%. Se usará el valor por defecto.");
                this.descuento = DESCUENTO_DEFAULT;
            } else {
                this.descuento = descuento / 100f;
            }
            return this;
        }

        public Builder volumen(float volumen){
            if (volumen < 0) {
                System.out.println("El volumen no puede ser negativo. Se usará el valor por defecto.");
                this.volumen = VOLUMEN_DEFAULT;
            } else {
                this.volumen = volumen;
            }
            return this;
        }

        public Builder envase(String envase){
            this.envase = envase;
            return this;
        }

        @Override
        public String toString() {
            Vino temp = new Vino(this);
            return String.format(
                    "Datos Vino: Marca: %s, Precio base: %.2f€, Graduación Alcohol: %.1f%%, Tipo: %s, Caducidad: %s, Descuento: %.0f%%, Volumen: %.2fL, Envase: %s, Precio con descuento: %.2f€",
                    marca, precio, gradosAlcohol, tipo,
                    caducidad, descuento, volumen, envase, temp.getPrecioDescuento()
            );
        }

        public Vino build(){

            if (marca == null || marca.isBlank()) {
                marca = MARCA_DEFAULT;
            }

            if (tipo == null || tipo.isBlank()) {
                tipo = TIPO_DEFAULT;
            }

            if (envase == null || envase.isBlank()) {
                envase = ENVASE_DEFAULT;
            }

            return new Vino(this);
        }

    }

}
