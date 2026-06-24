# Prompt para Mejorar el Codigo Base

Copia y pega el siguiente contenido completo en un asistente de IA (Claude, ChatGPT, etc.)
para obtener un ZIP con el proyecto corregido y listo para compilar.

---

```
Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación o descripciones sin código, genera los archivos
correspondientes sin aplicar análisis de compilación
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:
// === ARCHIVO: src/main/java/com/pragma/domain/model/PaymentEvent.java ===
package com.pragma.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record PaymentEvent(@Id String id, String operationNumber, String channel, String status) {}

// === ARCHIVO: src/main/java/com/pragma/application/service/PaymentCommandService.java ===
package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentCommandService {
    private final PaymentEventRepository repository;

    public PaymentCommandService(PaymentEventRepository repository) {
        this.repository = repository;
    }

    public Mono<PaymentEvent> processPayment(String operationNumber, String channel) {
        String id = operationNumber + "_" + channel;
        PaymentEvent event = new PaymentEvent(id, operationNumber, channel, "PENDING");
        return repository.save(event);
    }
}

// === ARCHIVO: src/main/java/com/pragma/application/service/PaymentQueryService.java ===
package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentQueryService {
    private final PaymentEventRepository repository;

    public PaymentQueryService(PaymentEventRepository repository) {
        this.repository = repository;
    }

    public Mono<PaymentEvent> getPaymentById(String id) {
        return repository.findById(id);
    }
}

// === ARCHIVO: src/main/java/com/pragma/infrastructure/repository/PaymentEventRepository.java ===
package com.pragma.infrastructure.repository;

import com.pragma.domain.model.PaymentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface PaymentEventRepository extends JpaRepository<PaymentEvent, String> {
    Mono<PaymentEvent> findById(String id);
}

// === ARCHIVO: src/main/resources/config/application.yml ===
spring:
  application:
    name: payment-service
  cloud:
    config:
      uri: http://localhost:8888
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password: password
  jpa:
    hibernate:
      ddl-auto: create-drop

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

// === ARCHIVO: src/test/java/com/pragma/domain/model/PaymentEventTest.java ===
package com.pragma.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentEventTest {
    @Test
    public void testPaymentEventCreation() {
        String id = "123_channel";
        PaymentEvent event = new PaymentEvent(id, "123", "channel", "PENDING");
        Assertions.assertEquals(id, event.id());
    }
}

// === ARCHIVO: src/test/java/com/pragma/application/service/PaymentCommandServiceTest.java ===
package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PaymentCommandServiceTest {
    @Test
    public void testProcessPayment() {
        PaymentEventRepository repository = Mockito.mock(PaymentEventRepository.class);
        PaymentCommandService service = new PaymentCommandService(repository);
        Mockito.when(repository.save(Mockito.any(PaymentEvent.class)))
               .thenReturn(Mono.just(new PaymentEvent("123_channel", "123", "channel", "PENDING")));
        StepVerifier.create(service.processPayment("123", "channel"))
               .expectNextMatches(event -> "123_channel".equals(event.id()))
               .verifyComplete();
    }
}

// === ARCHIVO: src/test/java/com/pragma/application/service/PaymentQueryServiceTest.java ===
package com.pragma.application.service;

import com.pragma.domain.model.PaymentEvent;
import com.pragma.infrastructure.repository.PaymentEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PaymentQueryServiceTest {
    @Test
    public void testGetPaymentById() {
        PaymentEventRepository repository = Mockito.mock(PaymentEventRepository.class);
        PaymentQueryService service = new PaymentQueryService(repository);
        Mockito.when(repository.findById("123_channel"))
               .thenReturn(Mono.just(new PaymentEvent("123_channel", "123", "channel", "PENDING")));
        StepVerifier.create(service.getPaymentById("123_channel"))
               .expectNextMatches(event -> "123_channel".equals(event.id()))
               .verifyComplete();
    }
}

// === ARCHIVO: src/main/java/com/pragma/infrastructure/client/AntiFraudServiceClient.java ===
package com.pragma.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "anti-fraud-service")
public interface AntiFraudServiceClient {
    @PostMapping("/check")
    String checkFraud(@RequestParam String operationNumber);
}

```
