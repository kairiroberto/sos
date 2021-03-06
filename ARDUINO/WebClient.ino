#include <SPI.h>
#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char server[] = "10.0.0.103";
//IPAddress server(208,113,198,201);

EthernetClient client;

int contador = 0;
int tentativas = 1;
String post = "serie=3";

void setup() {
  Serial.begin(9600);
  Serial.print("Conectando a rede. IP: ");
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Conexão falhou");
    while(true);
  } else {
    Serial.println(Ethernet.localIP());
  }
  delay(100000);
}

void loop() {
  if (contador == 0) {
    if (client.connect(server, 8080)) {
      Serial.print("Conectado ao cliente: ");
      Serial.println(server);
      client.print("GET ");
      client.print("/WebAppSos/newjsp.jsp ");
      client.println("HTTP/1.1");
      client.print("Host: ");
      client.println(server);
      client.println("Connection: close");
      client.println();
      Serial.println("Dados enviados!!!");
      contador = 1;
      client.stop(); 
    } else {
      Serial.print("Falhou ao conectar-se com o cliente ");
      Serial.println(server);
      Serial.print("Tentativa ");
      Serial.println(tentativas);
      tentativas = tentativas + 1;
    }
  }
}
