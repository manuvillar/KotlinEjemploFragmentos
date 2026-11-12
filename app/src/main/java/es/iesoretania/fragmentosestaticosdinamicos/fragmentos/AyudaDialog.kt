package es.iesoretania.fragmentosestaticosdinamicos.fragmentos

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.iesoretania.fragmentosestaticosdinamicos.R

/**
 * Diálogo de ayuda hecho con un DialogFragment.
 *
 * ¿Por qué un DialogFragment y no un AlertDialog creado directamente?
 * Porque el DialogFragment lo gestiona el FragmentManager: si giramos la pantalla
 * con el diálogo abierto, Android lo vuelve a crear y SIGUE ABIERTO.
 * Un AlertDialog suelto desaparecería al girar.
 *
 * Es solo informativo: no tiene que devolver ninguna respuesta.
 */
class AyudaDialog : DialogFragment() {

    // En un DialogFragment no se infla un layout en onCreateView:
    // se construye el diálogo en onCreateDialog() y se devuelve.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.ayuda_titulo)
            .setMessage(R.string.ayuda_mensaje)
            // null: el botón solo cierra el diálogo, no hace nada más
            .setPositiveButton(R.string.entendido, null)
            .create()
}
