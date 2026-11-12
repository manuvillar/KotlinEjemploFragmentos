package es.iesoretania.fragmentosestaticosdinamicos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.iesoretania.fragmentosestaticosdinamicos.databinding.ActivityMainBinding
import es.iesoretania.fragmentosestaticosdinamicos.fragmentos.ARG_NOMBRE
import es.iesoretania.fragmentosestaticosdinamicos.fragmentos.PrimerFragment
import es.iesoretania.fragmentosestaticosdinamicos.fragmentos.SegundoFragment

/**
 * Activity con los dos tipos de fragmentos:
 *  - ARRIBA, un fragmento ESTÁTICO (CabeceraFragment), declarado en activity_main.xml.
 *    La Activity no tiene que hacer nada con él: se crea solo al inflar el layout.
 *  - ABAJO, un contenedor DINÁMICO donde cargamos PrimerFragment o SegundoFragment
 *    con transacciones del FragmentManager, pasándoles el nombre como argumento.
 */
class MainActivity : AppCompatActivity() {

    // Objeto generado por ViewBinding a partir de activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()   // dibujamos de borde a borde

        // Al inflar el layout se crea también el fragmento estático (CabeceraFragment)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 16dp extra convertidos a píxeles según la densidad de la pantalla
        val extra = (16 * resources.displayMetrics.density).toInt()

        // Relleno = zona ocupada por las barras del sistema y la cámara + 16dp.
        // Solo se hace aquí, en la Activity: los fragmentos se dibujan dentro de binding.main.
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { vista, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            vista.setPadding(
                barras.left + extra, barras.top + extra,
                barras.right + extra, barras.bottom + extra
            )
            insets
        }

        // Cargamos el primer fragmento dinámico SOLO la primera vez que se crea la Activity.
        // Al girar la pantalla, savedInstanceState no es null y el FragmentManager ya
        // restaura por sí solo el fragmento que hubiera (con sus argumentos).
        // Si lo cargáramos otra vez, aparecería duplicado.
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                // Sin addToBackStack: es el estado inicial, no hay nada que deshacer
                replace<PrimerFragment>(R.id.contenedor, args = crearArgumentos())
            }
        }

        binding.btnCambiar.setOnClickListener { cambiarFragmento() }
    }

    /**
     * Sustituye el fragmento del contenedor dinámico por el otro.
     * El fragmento estático de arriba no se entera de nada.
     */
    private fun cambiarFragmento() {
        // Preguntamos al FragmentManager qué fragmento ocupa ahora el contenedor.
        // No usamos una variable propia porque se perdería al girar la pantalla
        // y dejaría de coincidir con lo que se ve después de pulsar Atrás.
        val actual = supportFragmentManager.findFragmentById(R.id.contenedor)

        supportFragmentManager.commit {
            setReorderingAllowed(true)   // optimiza la transacción (siempre recomendado)

            // replace (no add): quita el fragmento actual y pone el otro
            if (actual is PrimerFragment) {
                replace<SegundoFragment>(R.id.contenedor, args = crearArgumentos())
            } else {
                replace<PrimerFragment>(R.id.contenedor, args = crearArgumentos())
            }

            addToBackStack(null)   // el botón Atrás deshará este cambio
        }
    }

    /**
     * Prepara el Bundle de argumentos con el nombre escrito por el usuario.
     * Los fragmentos tienen constructor vacío: los datos se les pasan SIEMPRE así.
     */
    private fun crearArgumentos(): Bundle =
        bundleOf(ARG_NOMBRE to binding.etNombre.text.toString().trim())
}
