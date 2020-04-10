package com.e.myebook.activity;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import com.e.myebook.Fragment.ComprarFragment;
import com.e.myebook.Fragment.LendoFragment;
import com.e.myebook.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }*/
    }

    public void btn_entrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btn_cadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
