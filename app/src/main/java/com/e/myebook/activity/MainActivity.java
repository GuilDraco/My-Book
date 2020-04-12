package com.e.myebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.e.myebook.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(R.color.colorAccent)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(R.layout.intro_1)
                //.canGoBackward(true)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.color_amarelo)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(R.layout.intro_2)
                //.canGoForward(true)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void btn_entrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btn_cadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
