package com.skroyal00000.dailyworkout.Detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.skroyal00000.dailyworkout.ExerciseDays;
import com.skroyal00000.dailyworkout.HomePage;
import com.skroyal00000.dailyworkout.LoginPage;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Register;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import java.util.HashMap;
import java.util.Map;

public class Detail_intro extends AppCompatActivity {
    private Button nextBtn;
    private TextView btnskip;
    private RadioGroup radio_grp_gender;
    private RadioButton gender_selection_radio_btn;
    private ImageView backbtn;
    private String gender_string,previousExtrasValues = "";
    LinearLayout genderPage,agePage,phoneNoPage ;
    String userGender, userAge,userPhoneNo ;
    EditText ageEditText,phoneNoEditText;
    int whichFunctionIsOn;
    int clickCount = 1;

    boolean nextBtnPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intro);
        nextBtn = findViewById(R.id.nextbtn);
        backbtn = (ImageView) findViewById(R.id.btnback);
        radio_grp_gender = (RadioGroup) findViewById(R.id.radio_grp_gender);
        btnskip = (TextView) findViewById(R.id.btnskip);
        agePage = findViewById(R.id.agePage);
        phoneNoPage = findViewById(R.id.phoneNoPage);
        ageEditText = findViewById(R.id.ageEditText);
        phoneNoEditText = findViewById(R.id.phoneNoEditText);
        genderPage = findViewById(R.id.genderPage);

        Animation fade = AnimationUtils.loadAnimation(Detail_intro.this, android.R.anim.fade_in);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 1:
                        gender_selecion();
                        break;
                    case 2:
                        age_selection();
                        break;
                    case 3:
                        PhoneNoSelection();
                        break;
                    default:
                        Toast.makeText(Detail_intro.this, "Please Wait", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_intro.this,HomePage.class);
                startActivity(intent);
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_intro.this,HomePage.class);
                startActivity(intent);
            }
        });

    }




    public void gender_selecion(){



           if(radio_grp_gender.getCheckedRadioButtonId() == -1)
           {
               Toast.makeText(this,"please select gender",Toast.LENGTH_SHORT).show();
           }
           else
           {

               int radioId = radio_grp_gender.getCheckedRadioButtonId();
               gender_selection_radio_btn = findViewById(radioId);
               gender_string = "" + gender_selection_radio_btn.getText();
               userGender = gender_string;
               PrefConfig.saveGender(Detail_intro.this,gender_string);
               agePage.setVisibility(View.VISIBLE);
               genderPage.setVisibility(View.GONE);
               clickCount = 2;
           }
       }




    public void age_selection(){
        userAge = ageEditText.getText().toString();

        if(userAge.isEmpty())
        {
            Toast.makeText(this,"please enter age",Toast.LENGTH_SHORT).show();
        }
        else
        {
            agePage.setVisibility(View.GONE);
            phoneNoPage.setVisibility(View.VISIBLE);
            clickCount = 3;

        }
    }
    private void PhoneNoSelection() {
        userPhoneNo = phoneNoEditText.getText().toString();
        whichFunctionIsOn = 4;


        if(userPhoneNo.isEmpty())
        {
            Toast.makeText(this,"please enter phone no",Toast.LENGTH_SHORT).show();
        }
        else
        {
            saveData();
        }
    }

    private void saveData(){
        String username = PrefConfig.loadUsername(getApplicationContext());
        String gender = gender_string;
        String age = userAge;
        String phone_no = userPhoneNo;
        LinkApi linkApi = new LinkApi();
        String url = linkApi.userUpdateData;

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Detail_intro.this, response, Toast.LENGTH_SHORT).show();
                if(response.equalsIgnoreCase("success")){
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Detail_intro.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("userName",username);
                params.put("gender",gender);
                params.put("age",age);
                params.put("phone_no",phone_no);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Detail_intro.this);
        requestQueue.add(request);

    }

}