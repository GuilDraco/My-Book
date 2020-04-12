package com.e.myebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth firebaseAuth;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);

        Button btn_cadastrar = findViewById(R.id.btn_cadastro);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    validarCadastro();
                }
            });
    }

    public void cadastrarUsuario(){
        firebaseAuth = ConfiguracaoFireBase.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                }else{
                    String excessao;

                    try{
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthWeakPasswordException e){
                        excessao = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excessao = "Digite um e-mail válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        excessao = "Conta já cadastrada!";
                    }catch (Exception e){
                        excessao = "Erro ao Cadastrar Usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excessao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void validarCadastro(){
        String txtNome = campoNome.getText().toString();
        String txtEmail = campoEmail.getText().toString();
        String txtSenha = campoSenha.getText().toString();

        if(!txtNome.isEmpty()){
                if(!txtEmail.isEmpty()){
                    if(!txtSenha.isEmpty()){
                        usuario = new Usuario();
                        usuario.setNome(txtNome);
                        usuario.setEmail(txtEmail);
                        usuario.setSenha(txtSenha);
                        cadastrarUsuario();

                    }else{
                        Toast.makeText(CadastroActivity.this, "Preencha a Senha!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this, "Preencha o E-mail!", Toast.LENGTH_LONG).show();
                }
        }else{
            Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_LONG).show();
        }
    }
}
