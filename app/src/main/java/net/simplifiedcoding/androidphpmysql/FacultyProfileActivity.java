package net.simplifiedcoding.androidphpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class FacultyProfileActivity extends AppCompatActivity {

    private TextView textViewUsername1, textViewUserEmail1,textViewUserdepartment,textViewUserlocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        textViewUsername1 = (TextView) findViewById(R.id.textViewUsername1);
        textViewUserEmail1 = (TextView) findViewById(R.id.textViewUseremail1);
        textViewUserdepartment = (TextView) findViewById(R.id.textViewUserdepartment);
        textViewUserlocation = (TextView) findViewById(R.id.textViewUserlocation);


        textViewUserEmail1.setText(SharedPrefManager.getInstance(this).getFacultyEmail());
        textViewUsername1.setText(SharedPrefManager.getInstance(this).getFacultyname());
        textViewUserdepartment.setText(SharedPrefManager.getInstance(this).getFacultyDepartment());
        textViewUserlocation.setText(SharedPrefManager.getInstance(this).getFacultyLocation());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout1:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings1:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}