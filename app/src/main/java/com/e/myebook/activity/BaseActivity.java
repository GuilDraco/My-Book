package com.e.myebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.e.myebook.DataBase.Base64Custom;
import com.e.myebook.Fragment.ComprarFragment;
import com.e.myebook.Fragment.LendoFragment;
import com.e.myebook.Model.Usuario;
import com.e.myebook.R;
import com.e.myebook.activity.Config.ConfiguracaoFireBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity {

    private TextView campoReceitaTotal;
    private TextView campoNomeUsuario;
    private DatabaseReference fireBaseReference = ConfiguracaoFireBase.getFirebaseDatabase();
    private FirebaseAuth firebaseAuth = ConfiguracaoFireBase.getFirebaseAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        campoReceitaTotal = findViewById(R.id.txtReceitaTotal);
        campoNomeUsuario = findViewById(R.id.txtNomeUsuario);
        recuperarReceitaTotal();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My eBook");
        setSupportActionBar(toolbar);

        //Configuração Tabs
        FragmentPagerItemAdapter adapterTabs = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Lendo", LendoFragment.class)
                        .add("Comprar", ComprarFragment.class)
                        .create()
        );

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter( adapterTabs);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void recuperarReceitaTotal(){
        String emailUsuario = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail();
        if(emailUsuario != null) {
            final String idUsuario = Base64Custom.codificarBase64(emailUsuario);
            final DatabaseReference usuarioRef = fireBaseReference.child("usuarios")
                    .child(idUsuario);

            usuarioRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    assert usuario != null;
                    campoReceitaTotal.setText(("R$: " + usuario.getReceitaTotal()));
                    campoNomeUsuario.setText(usuario.getNome());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        firebaseAuth.signOut();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
    }
}
