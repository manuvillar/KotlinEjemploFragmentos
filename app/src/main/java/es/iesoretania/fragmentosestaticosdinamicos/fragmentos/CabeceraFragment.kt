package es.iesoretania.fragmentosestaticosdinamicos.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.iesoretania.fragmentosestaticosdinamicos.databinding.FragmentCabeceraBinding

/**
 * Fragmento ESTÁTICO.
 * Está declarado en activity_main.xml con android:name, así que lo crea el sistema
 * al inflar el layout de la Activity. Nadie lo sustituye mientras la app se ejecuta.
 */
class CabeceraFragment : Fragment() {

    // El binding es nullable: la vista del fragmento no existe siempre
    private var _binding: FragmentCabeceraBinding? = null

    // Atajo no nulo para usar SOLO entre onCreateView() y onDestroyView()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflamos el layout del fragmento y devolvemos su vista raíz.
        // false: lo añade al contenedor el FragmentManager, no nosotros.
        _binding = FragmentCabeceraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // La vista se destruye: soltamos el binding para no retenerla en memoria
        _binding = null
        aviso("onDestroyView")   // la vista muere; el fragmento puede seguir vivo
    }

    // Al pulsar "Cambiar fragmento" este fragmento NO muestra ningún Toast:
    // el botón solo afecta al contenedor dinámico.

    // ---------- Ciclo de vida: un Toast en cada método ----------
    // Así vemos cuándo se llama cada uno. El contexto se obtiene con requireContext().
    private fun aviso(metodo: String) {
        Toast.makeText(requireContext(), "Cabecera (estático): $metodo", Toast.LENGTH_SHORT).show()
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
