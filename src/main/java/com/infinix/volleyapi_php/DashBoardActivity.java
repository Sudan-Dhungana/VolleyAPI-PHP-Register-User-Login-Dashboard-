package com.infinix.volleyapi_php;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash_board);
        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();

        TextView showUserName = findViewById(R.id.showUserName);

        // Get the Intent that started this activity
        Intent i = getIntent();

        // Get the user name from the Intent
        String username = i.getStringExtra("username");
            
        // Set the text of the showUserName TextView
        String welcome = "Welcome, ";
        showUserName.setText(welcome);
        showUserName.append(username);


        ImageView logoutUser = findViewById(R.id.logoutUser);

        logoutUser.setOnClickListener(v ->{
            // Get the RequestQueue
            RequestQueue queue = Volley.newRequestQueue(this);

            // Define the URL for logout
            String url = "http://192.168.1.103/volley_api_php/logout.php";

            // Create a StringRequest for logout
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        // Parse the JSON response
                        //try {
                        //    JSONObject jsonObject = new JSONObject(response);
                        //    boolean error = jsonObject.getBoolean("error");
                        //    String message = jsonObject.getString("message");

                            // Display the message
                            Toast.makeText(DashBoardActivity.this, response, Toast.LENGTH_SHORT).show();

                            // Check if logout was successful
                            if(response.equals("Logged out successfully")){
                                i.removeExtra("username");
                                // Start the login activity and finish this one
                                Intent intent = new Intent(DashBoardActivity.this, UserLogin.class);
                                startActivity(intent);
                                finish();
                            }

                        //} catch (JSONException e) {
                         //   e.printStackTrace();
                        //}
                    }, error -> {
                        // Handle error
                        Toast.makeText(DashBoardActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                    });

            // Add the request to the RequestQueue
            queue.add(stringRequest);
        });
    }
    @Override
    public void onBackPressed() {
        // Do nothing or show a message
        Toast.makeText(this, "You cannot go back to the login activity", Toast.LENGTH_SHORT).show();
    }
}
