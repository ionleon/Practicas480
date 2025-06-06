public class Detergente extends Producto implements ILiquido, IDesuento{


    private float volumen;
    private String envase;
    private float descuento;

    public Detergente(Builder builder) {
        super(builder.marca, builder.precio);
        this.volumen = builder.volumen;
        this.envase = builder.envase;
        this.descuento = builder.descuento;

    }

    //Setters

    @Override
    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    @Override
    public void setEnvase(String envase) {
        this.envase = envase;
    }

    @Override
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    //Getter

    @Override
    public float getVolumen() {
        return volumen;
    }

    @Override
    public String getEnvase() {
        return envase;
    }

    @Override
    public float getDescuento() {
        return descuento;
    }

    @Override
    public float getPrecioDescuento() {
        return getPrecio()-(getPrecio()*getDescuento());
    }


    //Builder

    public static class Builder implements ProductoBuilder {

        //Valores Defecto

        private static final String MARCA_DEFAULT = "Bosque Verde";
        private static final float PRECIO_DEFAULT = 4f;
        private static final float DESCUENTO_DEFAULT = 0.2f;
        private static final float VOLUMEN_DEFAULT = 2.5f;
        private static final String ENVASE_DEFAULT = "Plástico";

        // Atributos

        private String marca = MARCA_DEFAULT;
        private float precio = PRECIO_DEFAULT;
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
            Detergente temp = new Detergente(this);
            return String.format(
                    "Producto: Marca: %s, Precio base: %.2f€, Descuento: %.0f%%, Volumen: %.2fL, Envase: %s, Precio con descuento: %.2f€",
                    marca, precio, descuento * 100, volumen, envase, temp.getPrecioDescuento()
            );
        }


        public Detergente build(){
            if (marca == null || marca.isBlank()) {
                marca = MARCA_DEFAULT;
            }

            if (envase == null || envase.isBlank()) {
                envase = ENVASE_DEFAULT;
            }

            return new Detergente(this);
        }

    }


    //toString

    @Override
    public String toString(){
        return String.format("Datos Detergente: %s, Descuento: %.0f%%, Volumen: %.2fL, Envase: %s, Precio con descuento: %.2f€",
                super.toString(), getDescuento()* 100, getVolumen(), getEnvase(), getPrecioDescuento()
                );
    }
}
