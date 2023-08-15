package com.example.phones.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.phones.ViewModel.PhonesViewModel
import com.example.phones.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var  mBinding : FragmentSecondBinding
    private val mViewModel : PhonesViewModel by activityViewModels()
    private var phoneId : String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            phoneId = bundle.getString("courseId")
            Log.d("seleccion", phoneId.toString())
        }

        phoneId?.let { id ->
            mViewModel.getPhoneDetailByIdFromInternet(id.toInt())
        }

        mViewModel.getPhoneDetail().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion3", phoneId.toString())

            var id = it.id
            var name = it.name

// cargamos los datos desde la seleccion
            Glide.with(mBinding.ivLogo).load(it.image).into(mBinding.ivLogo)
            mBinding.tvname.text = it.name
            mBinding.tvdescription.text = it.description
            mBinding.tvprice.text = "Precio : ${it.price}"
            mBinding.tvlastprice.text = "Precio Final : ${it.lastPrice}"
            mBinding.tvcredit.text = "Credito ( Acepta / No Acepta ): ${it.credit}"

            // ACCION DE ENVIAR CORREO Eléctronico
            mBinding.btMail.setOnClickListener {
                Log.d("Button", "Click")

                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type = "text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@novaera.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Solicito información sobre este Phone " + name
                )

                mintent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n" +
                            "Quisiera pedir información sobre este Phone " + name + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {

                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}