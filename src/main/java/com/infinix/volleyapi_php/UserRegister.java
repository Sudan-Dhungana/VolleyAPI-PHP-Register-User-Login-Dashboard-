package com.infinix.volleyapi_php;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRegister extends AppCompatActivity {
    TextInputEditText edtUser, edtEmail, edtContact, edtAddress, edtPassword;
    AppCompatButton btnRegister;
    String strUser, strEmail, strContact, strAddress, strPassword;
    String url = "http://192.168.1.103/volley_api_php/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        edtUser = findViewById(R.id.edtUser);
        edtEmail = findViewById(R.id.edtEmail);
        edtContact = findViewById(R.id.edtContact);
        edtAddress = findViewById(R.id.edtAddress);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(view -> {
            if(Objects.requireNonNull(edtUser.getText()).toString().equals(""))
            {
                Toast.makeText(UserRegister.this, "Enter Username", Toast.LENGTH_SHORT).show();
            } else if (Objects.requireNonNull(edtEmail.getText()).toString().equals("")) {
                Toast.makeText(UserRegister.this, "Enter Email", Toast.LENGTH_SHORT).show();
            } else if (Objects.requireNonNull(edtContact.getText()).toString().equals("")) {
                Toast.makeText(UserRegister.this, "Enter Contact", Toast.LENGTH_SHORT).show();
            } else if (Objects.requireNonNull(edtAddress.getText()).toString().equals("")) {
                Toast.makeText(UserRegister.this, "", Toast.LENGTH_SHORT).show();
            } else if (Objects.requireNonNull(edtPassword.getText()).toString().equals("")) {
                Toast.makeText(UserRegister.this, "Enter Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                strUser = edtUser.getText().toString().trim();
                strEmail = edtEmail.getText().toString().trim();
                strContact = edtContact.getText().toString().trim();
                strAddress = edtAddress.getText().toString().trim();
                strPassword = edtPassword.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
                    Toast.makeText(UserRegister.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                    Log.e("error", "onResponse: "+response );
                }, error -> {
                    error.printStackTrace();
                    Toast.makeText(UserRegister.this, "Error: error1" , Toast.LENGTH_SHORT).show();
                }
                ) {

                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("user",strUser);
                        params.put("email",strEmail);
                        params.put("contact",strContact);
                        params.put("address",strAddress);
                        params.put("password",strPassword);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(UserRegister.this);
                requestQueue.add(request);
            }
        });

    }
}
