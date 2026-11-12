package es.iesoretania.fragmentosestaticosdinamicos.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.iesoretania.fragmentosestaticosdinamicos.R
import es.iesoretania.fragmentosestaticosdinamicos.databinding.FragmentSegundoBinding

/**
 * Fragmento DINÁMICO: el segundo de los dos fragmentos dinámicos.
 * La Activity lo carga en el contenedor con una transacción (replace)
 * y le pasa el nombre del usuario en un Bundle de argumentos.
 */
class SegundoFragment : Fragment() {

    // El binding es nullable: la vista del fragmento no existe siempre
    private var _binding: FragmentSegundoBinding? = null

    // Atajo no nulo para usar SOLO entre onCreateView() y onDestroyView()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSegundoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Leemos el argumento que nos pasó la Activity.
        // Los argumentos se conservan aunque el fragmento se destruya y se vuelva
        // a crear (por ejemplo, al girar la pantalla o al volver con Atrás).
        // Si el usuario no escribió nada, usamos "anónimo".
        val nombre = requireArguments().getString(ARG_NOMBRE)
            .orEmpty()
            .ifBlank { getString(R.string.anonimo) }

        binding.tvSaludo.text = getString(R.string.saludo, nombre)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // La vista muere, pero el fragmento puede seguir vivo en la pila de retroceso
        _binding = null
        aviso("onDestroyView")   // la vista muere; el fragmento puede seguir vivo
    }

    // ---------- Ciclo de vida: un Toast en cada método ----------
    // Así vemos cuándo se llama cada uno. El contexto se obtiene con requireContext().
    private fun aviso(metodo: String) {
        Toast.makeText(requireContext(), "SegundoFragment: $metodo", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        aviso("onStart")
    }

    override fun onResume() {
        super.onResume()
        aviso("onResume")
    }

    override fun onPause() {
        super.onPause()
        aviso("onPause")
    }

    override fun onStop() {
        super.onStop()
        aviso("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        aviso("onDestroy")
    }
}
