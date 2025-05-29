public class TelegraphSystem {

    private static Message mensaje = new Message("SOS SOS",100);
    private static Transmitter transmisor = new Transmitter(true);
    private static Cable cable = new Cable(20,1);
    private static Cable cable2 = new Cable(20,1);
    private static Relay relay = new Relay(true);
    private static Receiver receiver = new Receiver();

    public static void run(){
        transmisor.send_signal(mensaje);
        cable.transmit(mensaje);
        relay.amplify_signal(mensaje);
        cable2.transmit(mensaje);
        receiver.receive_signal(mensaje);
        receiver.display_message();
    }


}
