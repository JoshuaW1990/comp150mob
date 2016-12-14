package cs.tufts.edu.pocketcritic;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.Window;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;



import com.crashlytics.android.Crashlytics;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import cs.tufts.edu.pocketcritic.fragment.ImageFragment;
import cs.tufts.edu.pocketcritic.fragment.LoginFragment;
import cs.tufts.edu.pocketcritic.fragment.SearchFragment;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
        //implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());


        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button logout = (Button) findViewById(R.id.main_logout);

        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Fragment fragment = new LoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });



        Fragment fragment = new LoginFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();


        
    }







}