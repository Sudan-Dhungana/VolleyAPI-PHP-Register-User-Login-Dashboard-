package com.infinix.volleyapi_php;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserLogin extends AppCompatActivity {
    TextView txtRegister;
    TextInputEditText txtUser, txtPassword;
    AppCompatButton btnLogin;
    String strUser, strPassword;
    String url = "http://192.168.1.103/volley_api_php/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(view -> {
            Intent intent = new Intent(UserLogin.this, UserRegister.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> {
            if(Objects.requireNonNull(txtUser.getText()).toString().equals(""))
            {
                Toast.makeText(UserLogin.this, "Enter Username", Toast.LENGTH_SHORT).show();
            }
            else if (Objects.requireNonNull(txtPassword.getText()).toString().equals(""))
            {
                Toast.makeText(UserLogin.this, "Enter Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                strUser = txtUser.getText().toString().trim();
                strPassword = txtPassword.getText().toString().trim();
                StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean error = jsonObject.getBoolean("error");
                        String message = jsonObject.getString("message");
                        txtUser.setText("");
                        txtPassword.setText("");
                        Toast.makeText(UserLogin.this, message, Toast.LENGTH_SHORT).show();

                        // Check if the response is successful
                        if (!error) {
                            // Start the dashboard activity
                            Intent intent = new Intent(UserLogin.this, DashBoardActivity.class);
                            intent.putExtra("username", strUser);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(UserLogin.this, error.getMessage(), Toast.LENGTH_SHORT).show()
                ) {

                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("user",strUser);
                        params.put("password",strPassword);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(UserLogin.this);
                requestQueue.add(request);
            }
        });
    }
}
