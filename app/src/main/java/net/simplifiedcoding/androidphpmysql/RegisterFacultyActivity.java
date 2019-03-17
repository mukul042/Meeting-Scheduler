package net.simplifiedcoding.androidphpmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterFacultyActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername1, editTextEmail1, editTextPassword1, editTextDepartment1, editTextLocation1;
    private Button buttonRegister1;
    private ProgressDialog progressDialog1;

    private TextView textViewLogin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_faculty);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        editTextEmail1 = (EditText) findViewById(R.id.editTextEmail1);
        editTextUsername1 = (EditText) findViewById(R.id.editTextUsername1);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        editTextDepartment1 = (EditText) findViewById(R.id.editTextDepartment1);
        editTextLocation1 = (EditText) findViewById(R.id.editTextLocation1);

        textViewLogin1 = (TextView) findViewById(R.id.textViewLogin1);

        buttonRegister1 = (Button) findViewById(R.id.buttonRegister1);

        progressDialog1 = new ProgressDialog(this);

        buttonRegister1.setOnClickListener(this);
        textViewLogin1.setOnClickListener(this);
    }

    private void registerUser() {
        final String email = editTextEmail1.getText().toString().trim();
        final String username = editTextUsername1.getText().toString().trim();
        final String password = editTextPassword1.getText().toString().trim();
        final String department = editTextDepartment1.getText().toString().trim();
        final String location = editTextLocation1.getText().toString().trim();

        progressDialog1.setMessage("Registering Faculty...");
        progressDialog1.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER_FACULTY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog1.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog1.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("department", department);
                params.put("location", location);
                return params;
            }
        };


        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister1)
            registerUser();
        if(view == textViewLogin1)
            startActivity(new Intent(this, LoginActivity.class));

    }
}
