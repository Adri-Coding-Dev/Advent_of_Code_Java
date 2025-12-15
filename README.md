# 🎄 Advent of Code Multi-Year Solutions in Java

[![Advent of Code](https://img.shields.io/badge/Advent_of_Code-Multi_Year-blueviolet)](https://adventofcode.com)
[![Java](https://img.shields.io/badge/Java-17+-orange)](https://openjdk.org/)
[![Eclipse](https://img.shields.io/badge/Built_with-Eclipse-2C2255)](https://eclipse.org)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

¡Bienvenido a mi repositorio estructurado para Advent of Code! Este proyecto contiene soluciones organizadas para **múltiples años** (2015-2025) con una arquitectura orientada a objetos limpia, extensible y bien documentada.

## ✨ ¿Qué es Advent of Code?

[Advent of Code](https://adventofcode.com) es un calendario de adviento con desafíos de programación creado por Eric Wastl. Cada año, del 1 al 25 de diciembre, se publican nuevos problemas que combinan algoritmia, lógica y creatividad.

> "¡Cada año, nuevos retos de programación para mantener tus habilidades afiladas y celebrar la temporada navideña!"

## 🏗️ Arquitectura del Proyecto

Este proyecto implementa una arquitectura **Orientada a Objetos** que permite:

- **Soporte multi-año**: Ejecutar desafíos de cualquier año (2015-2025+)
- **Extensibilidad**: Fácil adición de nuevos años y días
- **Reutilización**: Código común en clases base y utilidades
- **Organización clara**: Separación por responsabilidades y años

### 📁 Estructura del Proyecto

```
AdventOfCode/
├── src/
│   ├── core/                          # Núcleo del sistema
│   │   ├── Calendar.java              # Clase abstracta para calendarios
│   │   ├── Day.java                   # Clase abstracta para días
│   │   └── Main.java                  # Punto de entrada principal
│   │
│   ├── utils_2025/                    # Utilidades de 2025
│   │   ├── Utils.java                 # Utilidades generales para todos los días
│   │   ├── Utils_Day01.java           # Utilidades generales para todos los días
│   │   └── ...                        # Resto de Utilidades
│   │
│   ├── years/                         # Calendarios específicos por año
|   |   ├── Year_2025.java             # Calendario 2025
│   │   ├── Year_2024.java             # Calendario 2024
│   │   └── ...                        # Otros años
│   │
│   ├── year_2025/                     # Soluciones 2025
│   │   ├── Day_01_2025.java           # Día 1 de 2025
│   │   ├── Day_02_2025.java           # Día 2 de 2025
│   │   └── ...                        # Resto de Días
│   │
│   ├── year_2024/                     # Soluciones 2024
│   │   ├── Day_01_2024.java           # Día 1 de 2024
│   │   ├── Day_02_2024.java           # Día 2 de 2024
│   │   └── ...                        # Resto de Días
│   │
│   ├── inputs_2025/                   # Archivos de entrada 2025
│   │   ├── Input_Day01.txt            # Input día 1
│   │   ├── Input_Day02.txt            # Input día 2
│   │   └── ...                        # Resto de Inputs
│   │
│   ├── inputs_2024/                   # Archivos de entrada 2024
│   │   ├── Input_Day01.txt
│   │   ├── Input_Day02.txt
│   │   └── ...
│   │
│   └── instructions_2025/             # Instrucciones 2025
│       ├── Instructions_Day01.txt     # Instrucciones día 1
│       ├── Instructions_Day02.txt     # Instrucciones día 2
│       └── ...                        # Resto de Instrucciones
│
├── README.md                          # Este archivo
└── LICENSE                            # Licencia MIT
```

## 🚀 Características Principales

### ✅ **Arquitectura Orientada a Objetos**
- Clases abstractas `Calendar` y `Day` que definen el comportamiento base
- Herencia para especialización por año
- Encapsulación y polimorfismo

### ✅ **Gestión Multi-Año**
- Selección interactiva de año y día
- Ejecución individual o completa de años
- Estructura escalable para años futuros

### ✅ **Sistema de Entradas Integrado**
- Archivos de input dentro del classpath
- Lectura automática desde paquetes organizados
- Verificación de existencia de archivos

### ✅ **Utilidades Comunes**
- Métodos de parsing (enteros, grids, arrays)
- Funciones matemáticas (GCD, LCM, distancias)
- Herramientas para strings y colecciones

### ✅ **Documentación y Instrucciones**
- Instrucciones incluidas dentro del proyecto
- Acceso directo desde código
- Plantillas para nuevos días

## 🛠️ Requisitos y Configuración

### Prerrequisitos
- **Java JDK 17** o superior (compatible con Eclipse)
- **Eclipse IDE** (recomendado) o cualquier IDE Java
- **Git** para control de versiones

### Configuración en Eclipse

1. **Clonar el repositorio:**
```bash
git clone https://github.com/Adri-Coding-Dev/Advent_of_Code_Java?tab=readme-ov-file.git
```

2. **Importar en Eclipse:**
   - File → Import → Existing Projects into Workspace
   - Seleccionar la carpeta del proyecto
   - Finish

3. **Configurar estructura:**
   - Ejecutar `CreateProjectStructure.java` si es necesario
   - Refrescar proyecto (F5)

4. **Agregar tus inputs:**
   - Copiar tus archivos .txt a `src/inputs_2024/` y `src/inputs_2025/`
   - Los nombres deben seguir el formato `Input_DayXX.txt`

## 🎮 Cómo Ejecutar

### Usando la Interfaz Interactiva

Ejecuta `Main.java` en el paquete `core`:

```bash
cd AdventOfCode
javac -d bin src/core/*.java src/utils/*.java src/years/*.java src/year_2024/*.java src/year_2025/*.java
java -cp bin core.Main
```

**Opciones disponibles:**

```
🎄 ADVENT OF CODE RUNNER 🎄
1. Run day solution       # Ejecuta un día específico
2. Show instructions      # Muestra las instrucciones
3. Run entire year        # Ejecuta todos los días de un año
4. Show instructions and run # Instrucciones + ejecución
```

### Ejemplos de Uso

1. **Ejecutar año completo:**
```
Select option: 1
Enter year: 2025
```

2. **Ejecutar día específico:**
```
Select option: 2
Enter year: 2024
Enter day number: 3
```

## 📊 Progreso de Soluciones

### Advent of Code 2025

| Día | Título | Parte 1 | Parte 2 | Dificultad | Tiempo |
|-----|--------|:-------:|:-------:|:----------:|:------:|
| 01 | [Día 1](src/year_2025/Day_01_2025.java) | ✅ | ✅ | ⭐ | 30ms |
| 02 | [Día 2](src/year_2025/Day_02_2025.java) | ✅ | ✅ | ⭐ | 28ms |
| 03 | [Día 3](src/year_2025/Day_03_2025.java) | ✅ | ✅ | ⭐⭐ | 7ms |
| 04 | [Día 4](src/year_2025/Day_04_2025.java) | ✅ | ✅ | ⭐⭐⭐ | 26ms |
| 05 | [Día 5](src/year_2025/Day_05_2025.java) | ✅ | ✅ | ⭐⭐⭐⭐⭐ | 7ms |
| 06 | [Día 6](src/year_2025/Day_06_2025.java) | ✅ | ✅ | ⭐⭐⭐⭐ | 50ms |
| 07 | [Día 7](src/year_2025/Day_07_2025.java) | ✅ | ✅ | ⭐⭐⭐⭐ | 28ms |
| 08 | [Día 8](src/year_2025/Day_08_2025.java) | ✅ | ✅ | ⭐⭐⭐⭐⭐ | 328ms |
| 09 | [Día 9](src/year_2025/Day_09_2025.java) | ✅ | ✅ | ⭐⭐⭐⭐⭐⭐ | 68ms |
| 10 | [Día 10](src/year_2025/Day_10_2025.java) | ✅ | 🔄 | - | - |
| ... | ... | ... | ... | ... | ... |

### Advent of Code 2015 (Proximamente)


**Leyenda:** ✅ Completado | 🔄 En progreso | ⏳ Pendiente

## 🧠 Diseño Orientado a Objetos

### Diagrama de Clases Simplificado

```
                ┌─────────────────┐
                │   <<abstract>>  │
                │     Calendar    │
                ├─────────────────┤
                │ - year: int     │
                │ - days: Map     │
                ├─────────────────┤
                │ + runDay()      │
                │ + runYear()     │
                │ + addDay()      │
                └────────┬────────┘
                         │
            ┌────────────┴─────────────┐
           │                            │
┌───────────────────┐       ┌───────────────────┐
│     Year_2024     │       │     Year_2025     │
├───────────────────┤       ├───────────────────┤
│ + initializeDays()│       │ + initializeDays()│
│                   │       │                   │
└───────────────────┘       └───────────────────┘
           │                            │
           └────────────┬─────────────┘
                         │
                ┌────────▼────────┐
                │   <<abstract>>  │
                │       Day       │
                ├─────────────────┤
                │ - year: int     │
                │ - dayNumber: int│
                ├─────────────────┤
                │ + solvePart1()  │
                │ + solvePart2()  │
                │ + readLines()   │
                │ + run()         │
                └────────┬────────┘
                         │
    ┌────────────────────┼────────────────────┐
    │                    │                    │
┌───▼───────┐     ┌──────▼────┐     ┌─────────▼─┐
│Day_01_2024│     │Day_02_2024│ ... │Day_01_2025│
├───────────┤     ├───────────┤     ├───────────┤
│           │     │           │     │           │
└───────────┘     └───────────┘     └───────────┘
```

### Principios Aplicados

1. **Herencia**: `Year_2024` y `Year_2025` extienden `Calendar`
2. **Polimorfismo**: Cada `Day_XX_YYYY` implementa sus propios métodos `solvePart1/2`
3. **Encapsulación**: Campos privados con métodos públicos de acceso
4. **Abstracción**: Clases base definen comportamiento común

## 📈 Estadísticas del Proyecto

![Años soportados](https://img.shields.io/badge/Años_soportados-2-green)
![Días implementados](https://img.shields.io/badge/Días_implementados-50+-blue)
![Última actualización](https://img.shields.io/badge/Última_actualización-Diciembre_2025-lightgrey)

**Progreso total:** 
- 🔴 **2025:** 9/24 días (37,5%)
- 🟡 **Total:** 9/24 días (37,5%)

## 🎯 Cómo Contribuir o Extender

### Agregar un Nuevo Año

1. **Implementar calendario:**
```java
// En src/years/Year_2026.java
package years;
public class Year_2026 extends Calendar {
    public Year_2026() { super(2026); }
    @Override
    protected void initializeDays() {
        addDay(1, new year_2026.Day_01_2026());
        // ... días 2-25
    }
}
```

2. **Actualizar Main.java para añadir el año generado:**
```java
private static Calendar createCalendar(int year) {
    switch (year) {
        case 2026: return new Year_2026();
        // ... otros años
    }
}
```

### Agregar una Nueva Utilidad

1. **Añadir método en Utils.java (sin son metodos generales):**
```java
public static int[] findPattern(String text, String pattern) {
    // Implementación
}
```

2. **Importar en tus días:**
```java
import utils.Utils;
// Usar: Utils.findPattern(...)
```

## 🚨 Solución de Problemas

### Error: "Input file not found"
**Solución:**
1. Verificar que el archivo existe en `src/inputs_YYYY/`
2. El nombre debe ser exacto: `Input_Day01.txt`
3. Refrescar proyecto en Eclipse (F5)
4. Ejecutar opción 0 en Main.java para verificar estructura

### Error: Clase no encontrada
**Solución:**
1. Limpiar proyecto: Project → Clean
2. Reconstruir: Project → Build All
3. Verificar imports en las clases

### Error: Método no disponible en Utils
**Solución:**
1. Añadir método necesario en `Utils.java`
2. Recompilar proyecto
3. Actualizar imports si es necesario

## 📚 Recursos y Enlaces

- [Página oficial de Advent of Code](https://adventofcode.com)
- [Documentación de Java](https://docs.oracle.com/en/java/)
- [Subreddit de Advent of Code](https://www.reddit.com/r/adventofcode/)
- [Awesome Advent of Code](https://github.com/Bogdanp/awesome-advent-of-code)
- [Eclipse IDE](https://www.eclipse.org/ide/)

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras:

- 🐛 **Errores** en las soluciones
- 📈 **Optimizaciones** de rendimiento
- 🔧 **Mejoras** en la arquitectura
- 📚 **Documentación** incompleta

Por favor, abre un **Issue** o envía un **Pull Request**.

### Guía de Contribución
1. Fork del repositorio
2. Crear rama para tu feature (`git checkout -b feature/mejora`)
3. Commit cambios (`git commit -m 'Añade nueva utilidad'`)
4. Push a la rama (`git push origin feature/mejora`)
5. Abrir Pull Request

## 📄 Licencia

Este proyecto está licenciado bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## ⭐ Apoya el Proyecto

Si este repositorio te ha sido útil, ¡considera darle una estrella! ⭐

**¿Empezando con Advent of Code?**  
Revisa las soluciones del año 2024 para entender los patrones comunes.

**¿Buscando un desafío?**  
¡Implementa los días faltantes y envía un PR!

**¿Quieres mejorar la arquitectura?**  
¡Todas las ideas son bienvenidas!

---

## 🎅 Notas del Desarrollador

Este proyecto comenzó como una simple colección de soluciones y evolucionó hacia una arquitectura completa que soporta múltiples años. La meta es crear un framework que facilite:

1. **Aprendizaje** de patrones comunes en AoC
2. **Comparación** de soluciones entre años
3. **Reutilización** de código entre desafíos
4. **Organización** para futuros eventos

**¡Feliz codificación y felices fiestas!** 🎄🎁

---

*Este README se actualizará con cada nueva funcionalidad y año agregado.*