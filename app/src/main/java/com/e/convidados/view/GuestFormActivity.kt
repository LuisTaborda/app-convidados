package com.e.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.convidados.viewmodel.GuestFormViewModel
import com.e.convidados.R
import com.e.convidados.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*
import kotlinx.android.synthetic.main.row_guest.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        loadData()
        setListeners()
        observe()
    }

    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            val id = bundle.getInt(GuestConstants.GUESTID)

            mViewModel.load(id)
        }
    }

    override fun onClick(v: View) {
        val id = v.id;
        if(id== R.id.button_save){

            val name = edit_name.text.toString()
            val presence = radio_presence.isChecked

            mViewModel.save(name, presence)
        }
    }

    private fun observe(){
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        mViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name);
            if(it.presence){
                radio_presence.isChecked = true
            } else {
                radio_absent.isChecked = true
            }
        })
    }

    private fun setListeners(){
        button_save.setOnClickListener(this)
    }
}