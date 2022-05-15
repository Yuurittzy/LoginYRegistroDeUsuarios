package com.example.yuritzy_firebase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var mauth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mauth = FirebaseAuth.getInstance()

        view.findViewById<Button>(R.id.RegisterButton).setOnClickListener {
            register()
        }
    }

    private fun register() {
        val user = activity?.findViewById<EditText>(R.id.userTxt)?.text.toString()
        val password = activity?.findViewById<EditText>(R.id.passwordTxt)?.text.toString()

        mauth.createUserWithEmailAndPassword(user,password).addOnCompleteListener {
             if(it.isSuccessful){
                 view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_register_to_loginFragment) }
            } else {
                Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
            }
        }
    }

}