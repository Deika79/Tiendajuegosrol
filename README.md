# Sistema de Gestión - Tienda de Juegos de Rol

Aplicación de consola en Java para gestionar **clientes**, **catálogo de juegos de rol** y **ventas** (con stock y reportes).

## Requisitos
- Java 17+
- Maven 3.9+
- VS Code (recomendado) + Extension Pack for Java

## Estructura del proyecto
├── pom.xml
├── README.md
├── .gitignore
├── docs/ # Javadoc generado
├── src
│ ├── main
│ │ └── java # Código de la app
│ └── test
│ └── java # Tests JUnit

bash
Copiar
Editar

## Cómo compilar, testear y ejecutar

```bash
# Limpiar y ejecutar tests
mvn -q clean test

# Ejecutar aplicación
mvn -q exec:java
Uso
Al ejecutar, se mostrará un menú en consola para gestionar:

Clientes

Juegos de rol (catálogo y stock)

Ventas (con cálculo de importe y ventas por cliente)

Documentación Javadoc
Generar la documentación HTML con:

bash
Copiar
Editar
mvn -q javadoc:javadoc
Copiar la documentación a la carpeta /docs:

macOS/Linux:

bash
Copiar
Editar
rm -rf docs && mkdir docs && cp -R target/site/apidocs/* docs/
Windows PowerShell:

powershell
Copiar
Editar
rm -r -fo docs; mkdir docs; cp -r target/site/apidocs/* docs/
Autoría y licencia
Autor: David García Rodríguez

Licencia: MIT