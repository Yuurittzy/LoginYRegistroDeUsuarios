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
import com.google.firebase.auth.FirebaseUser

class LoginFragment : Fragment() {

    lateinit var mauth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mauth = FirebaseAuth.getInstance()

        activity?.findViewById<Button>(R.id.loginButton)?.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val user = activity?.findViewById<EditText>(R.id.userTxt)?.text.toString()
        val password = activity?.findViewById<EditText>(R.id.passwordTxt)?.text.toString()

        mauth.signInWithEmailAndPassword(user,password).addOnCompleteListener {
            if (it.isSuccessful){
                this.view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_loginFragment_to_mainFragment) }
            } else {
                this.view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_loginFragment_to_register) }
                Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
            }
        }
    }

}