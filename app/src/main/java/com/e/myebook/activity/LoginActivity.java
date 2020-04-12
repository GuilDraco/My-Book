package com.e.myebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.myebook.Model.Usuario;
import com.e.myebook.R;
import com.e.myebook.activity.Config.ConfiguracaoFireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        Button btnLogin = findViewById(R.id.btn_entrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCadastro();
            }
        });
    }

    public void validarCadastro(){
        String txtEmail = campoEmail.getText().toString();
        String txtSenha = campoSenha.getText().toString();

            if(!txtEmail.isEmpty()){
                if(!txtSenha.isEmpty()){
                    usuario = new Usuario();
                    usuario.setEmail(txtEmail);
                    usuario.setSenha(txtSenha);

                    validarLogin();
                }else{
                    Toast.makeText(LoginActivity.this, "Preencha a Senha!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "Preencha o E-mail!", Toast.LENGTH_LONG).show();
            }
    }

    public void validarLogin(){
        FirebaseAuth firebaseAuth = ConfiguracaoFireBase.getFirebaseAuth();
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                    finish();
                }else {
                    String excessao;

                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthInvalidUserException e) {
                        excessao = "Usuário não esta cadastrado!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excessao = "E-mail e senha não correspondem a um usuário cadastrado!";
                    }catch (Exception e) {
                        excessao = "Erro ao Cadastrar Usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excessao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
