/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conection;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/**
 *
 * @author marce
 */
public class MqttManager {
    private MqttClient client;

    public MqttManager(String brokerUrl, String clientId) throws MqttException {
        client = new MqttClient(brokerUrl, clientId);
        // Configura aquí más opciones si es necesario
    }
    public void connect() throws MqttException {
        client.connect();
        // Configura aquí el callback y más opciones de conexión si es necesario
    }
    public void subscribe(String topic) throws MqttException {
        client.subscribe(topic);
    }
    public void publish(String topic, String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        client.publish(topic, message);
    }
    // Métodos adicionales para manejar la desconexión, reconexión, etc.
    public void setCallback(MqttCallback callback) {
        client.setCallback(callback);
    }
}
