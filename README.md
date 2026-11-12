# Fragmentos estáticos y dinámicos

Ejemplo del **Tema 5: Fragmentos** de Programación Multimedia y Dispositivos Móviles (Android con Kotlin).

Una sola pantalla con los dos tipos de fragmentos:

- **Arriba, un fragmento estático** (`CabeceraFragment`): declarado en `activity_main.xml` con `FragmentContainerView` y `android:name`. Nunca cambia.
- **Abajo, un contenedor dinámico**: el botón alterna entre `PrimerFragment` y `SegundoFragment` con `replace` + `addToBackStack`. El botón Atrás deshace el cambio.
- **Paso de datos**: el nombre escrito en el campo de texto se envía a los fragmentos dinámicos como argumento (`bundleOf`) y lo leen con `requireArguments()`.

## Qué observar

1. Pulsa **Cambiar fragmento**: solo cambia la parte de abajo.
2. Pulsa **Atrás**: vuelve el fragmento anterior, con el nombre que tenía.
3. **Gira la pantalla**: en horizontal se usa `layout-land` y no se duplica ningún fragmento (`savedInstanceState == null`).
4. Cada fragmento muestra un **Toast** en `onStart`, `onResume`, `onPause`, `onStop`, `onDestroyView` y `onDestroy`. Al pulsar el botón solo aparecen los de los fragmentos dinámicos: el estático no se entera.

## Requisitos

- Android Studio con AGP 9 (Gradle 9.1)
- ViewBinding activado
