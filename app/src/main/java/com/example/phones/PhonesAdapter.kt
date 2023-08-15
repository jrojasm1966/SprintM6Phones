package com.example.phones

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phones.Modelo.local.entities.PhonesEntity
import com.example.phones.databinding.PhonesListBinding


class PhonesAdapter : RecyclerView.Adapter<PhonesAdapter.PhoneVH>(){

    // referencia para el adapter
    private var listPhones= listOf<PhonesEntity>()
    private  val selectedPhone= MutableLiveData<PhonesEntity>()

    fun update (list:List<PhonesEntity>){
        listPhones= list
        notifyDataSetChanged()
    }

    // función para seleccionar
    fun selectedPhone():LiveData<PhonesEntity> = selectedPhone

    inner class PhoneVH(private  val mbinding : PhonesListBinding):
            RecyclerView.ViewHolder(mbinding.root), View.OnClickListener{

                fun bind(phone: PhonesEntity){
                    Glide.with(mbinding.ivLogo).load(phone.image).centerCrop().into(mbinding.ivLogo)
                    mbinding.tvname.text= phone.name
                    mbinding.tvprice.text= "Precio:"+ phone.price
                    itemView.setOnClickListener(this)
                }
                override fun onClick(v:View) {
                    // referencia a la selección
                    selectedPhone.value= listPhones[adapterPosition]
                    Log.d("ONCLICK",adapterPosition.toString())
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
     return PhoneVH(PhonesListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listPhones.size

    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
       val phone= listPhones[position]
        holder.bind(phone)
    }
}