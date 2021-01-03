package halfdayman.qpush;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.action_new:
                        Log.v("menu_action","add a new channel...");
                        return true;
                    default:
                        return false;
                }
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.dialogsFragment) {
                    toolbar.setTitle("QPush");
                    toolbar.getMenu().clear();
                    toolbar.inflateMenu(R.menu.toolbar_menu);
                }
                else if(destination.getId() == R.id.squareFragment) {
                    toolbar.setTitle("Square");
                    Menu menu = toolbar.getMenu();
                    MenuItem item = menu.findItem(R.id.action_new);
                    item.setVisible(true);
                }
                else if(destination.getId() == R.id.settingsFragment) {
                    toolbar.setTitle("Settings");
                    Menu menu = toolbar.getMenu();
                    MenuItem item = menu.findItem(R.id.action_new);
                    item.setVisible(false);
                }
                else {
                    Log.v("Navigation","Wrong destination");
                }
            }
        });

    }

}