package com.skroyal00000.dailyworkout.Detail;

import androidx.annotation.NonNull;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skroyal00000.dailyworkout.ExerciseDays;
import com.skroyal00000.dailyworkout.HomePage;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.R;

import java.util.HashMap;

public class Detail_intro extends AppCompatActivity {
    private Button nextBtn,finishbtn;
    private TextView btnskip;
    private RadioGroup radio_grp_gender,radio_grp_level;
    private RadioButton gender_selection_radio_btn, level_selection_radio_btn;
    private ImageView backbtn;
    private String gender_string,level_string,previousExtrasValues = "";
    LinearLayout levelpage,genderPage,agePage,phoneNoPage,namePage,heightPage,weightPage,locationPage;
    String userGender, userLevel, userName,userLocation, userAge,userPhoneNo,userHeight,userWeight,userEmail;
    EditText ageEditText,phoneNoEditText,nameEditText,heightEditText,weightEditText,locationEditText;
    int whichFunctionIsOn;
    int clickCount = 1;
    String personName;
    String personGivenName;
    String personFamilyName;
    String personEmail;
    String personId;
    boolean nextBtnPressed = false;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Details userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intro);

        nextBtn = findViewById(R.id.nextbtn);
        backbtn = (ImageView) findViewById(R.id.btnback);
        radio_grp_gender = (RadioGroup) findViewById(R.id.radio_grp_gender);
        radio_grp_level = (RadioGroup) findViewById(R.id.radio_grp_level);
        btnskip = (TextView) findViewById(R.id.btnskip);

        levelpage = findViewById(R.id.nextPage);
        agePage = findViewById(R.id.agePage);
        phoneNoPage = findViewById(R.id.phoneNoPage);
        namePage = findViewById(R.id.namePage);
        heightPage = findViewById(R.id.heightPage);
        weightPage = findViewById(R.id.weightPage);
        locationPage = findViewById(R.id.locationPage);
        ageEditText = findViewById(R.id.ageEditText);
        phoneNoEditText = findViewById(R.id.phoneNoEditText);
        nameEditText = findViewById(R.id.nameEditText);
        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        locationEditText = findViewById(R.id.locationEditText);
        genderPage = findViewById(R.id.genderPage);
        genderPage = findViewById(R.id.genderPage);

        Animation fade = AnimationUtils.loadAnimation(Detail_intro.this,android.R.anim.fade_in);
//        firebase

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("userDetails");

        // initializing our object
        // class variable.
        userDetails = new Details();


        //google information
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            personName = acct.getDisplayName();
            personGivenName = acct.getGivenName();
            personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
            Toast.makeText(Detail_intro.this, "Details " + personName + personGivenName + personFamilyName + personEmail + personId, Toast.LENGTH_SHORT).show();
        }



        getExtras();

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                switch(whichFunctionIsOn) {
//                    case 1:
//                        // code block
//                        break;
//                    case 2:
//                        gender_selecion();
//                        break;
//                    case 3:
//                        level_selection();
//                        break;
//                    case 4:
//                        age_selection();
//                        break;
//                    case 5:
//                        PhoneNoSelection();
//                        break;
//                    case 6:
//                        NameSelection();
//                        break;
//                    case 7:
//                        HeightSelection();
//                        break;
//                    case 8:
//                        WeightSelection();
//                        break;
//                    default:
//                        // code block
//                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(clickCount) {
                    case 1:
                        gender_selecion();
                        break;
                    case 2:
                        level_selection();
                        break;
                    case 3:
                        age_selection();
                        break;
                    case 4:
                        PhoneNoSelection();
                        break;
                    case 5:
                        NameSelection();
                        break;
                    case 6:
                        HeightSelection();
                        break;
                    case 7:
                        WeightSelection();
                        break;
                    case 8:
                        LocationSelection();
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


            }
        });


    if(PrefConfig.loadGender(Detail_intro.this) != null && PrefConfig.loadLevel(Detail_intro.this) != null){
        if( previousExtrasValues.equalsIgnoreCase("Custom_pressed")){
            Intent intent = new Intent(Detail_intro.this, ExerciseDays.class);
            startActivity(intent);
            finish();
        }
    }
    }


    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            previousExtrasValues = extras.getString("customPressed");

        }
    }

    private void nextExtaDepandActivity() {
        if (previousExtrasValues.equalsIgnoreCase("Custom_pressed")){
            Intent intent = new Intent(Detail_intro.this, ExerciseDays.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(Detail_intro.this, HomePage.class);
            startActivity(intent);
            finish();

        }
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
               levelpage.setVisibility(View.VISIBLE);
               genderPage.setVisibility(View.GONE);
               clickCount = 2;
           }
       }


    public void level_selection(){
        whichFunctionIsOn = 2;


          if(radio_grp_level.getCheckedRadioButtonId() == -1)
          {
              Toast.makeText(this,"please select level",Toast.LENGTH_SHORT).show();
          }
          else
          {
              int radioId = radio_grp_level.getCheckedRadioButtonId();
              level_selection_radio_btn = findViewById(radioId);
              level_string = "" + level_selection_radio_btn.getText();
              userLevel = level_string;
              PrefConfig.saveLevel(Detail_intro.this,level_string);
              levelpage.setVisibility(View.GONE);
              agePage.setVisibility(View.VISIBLE);
              clickCount = 3;

      }


    }

    public void age_selection(){
        userAge = ageEditText.getText().toString();
        whichFunctionIsOn = 3;

        if(userAge.isEmpty())
        {
            Toast.makeText(this,"please enter age",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Text Insert Successfully",Toast.LENGTH_SHORT).show();
            agePage.setVisibility(View.GONE);
            phoneNoPage.setVisibility(View.VISIBLE);
            clickCount = 4;

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
            Toast.makeText(this,"Text Insert Successfully",Toast.LENGTH_SHORT).show();
            phoneNoPage.setVisibility(View.GONE);
            namePage.setVisibility(View.VISIBLE);
            clickCount = 5;

        }
    }

    private void NameSelection() {
        userName = nameEditText.getText().toString();
        whichFunctionIsOn = 5;


        if(userName.isEmpty())
        {
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Text Insert Successfully",Toast.LENGTH_SHORT).show();
            namePage.setVisibility(View.GONE);
            heightPage.setVisibility(View.VISIBLE);
            clickCount = 6;

        }
    }

    private void HeightSelection() {
        userHeight = heightEditText.getText().toString();
        whichFunctionIsOn = 6;

        if(userHeight.isEmpty())
        {
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Text Insert Successfully",Toast.LENGTH_SHORT).show();
            heightPage.setVisibility(View.GONE);
            weightPage.setVisibility(View.VISIBLE);
            clickCount = 7;

        }
    }

    private void WeightSelection() {
        userWeight = weightEditText.getText().toString();
        whichFunctionIsOn = 7;

        if(userWeight.isEmpty())
        {
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Text Insert Successfully",Toast.LENGTH_SHORT).show();
            weightPage.setVisibility(View.GONE);
            locationPage.setVisibility(View.VISIBLE);

            clickCount = 8;

        }
    }

    private void LocationSelection() {
        userLocation = locationEditText.getText().toString();
        whichFunctionIsOn = 8;

        if(userLocation.isEmpty())
        {
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Text Insert Successfully " + userAge  + userHeight+userWeight+userGender+userLevel+userPhoneNo+userName,Toast.LENGTH_SHORT).show();
            addDatatoFirebase(userAge,userHeight,userWeight,userGender,userLevel,userPhoneNo,userName,personEmail,userLocation);
            clickCount = 9;

        }
    }
    private void addDatatoFirebase(String userAge,String userHeight,String userWeight,String userGender,String userLevel,String userPhoneNo,String userName,String userEmail,String userLocation) {
        // below 3 lines of code is used to set
        // data in our object class.
        userDetails.setUserAge(userAge);
        userDetails.setUserHeight(userHeight);
        userDetails.setUserWeight(userWeight);
        userDetails.setUserGender(userGender);
        userDetails.setUserLevel(userLevel);
        userDetails.setUserPhoneNo(userPhoneNo);
        userDetails.setUserName(userName);
        userDetails.setUserEmail(userEmail);
        userDetails.setUserLocation(userLocation);

        // we are use add value event listener method
        // which is called with database reference.


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("User Detail");
                String TrimUserEmail = personEmail.substring(0, personEmail.indexOf('@'));
                reference1.child("" + TrimUserEmail).setValue(userDetails);

                // after adding this data we are showing toast message.
                Toast.makeText(Detail_intro.this, "data added", Toast.LENGTH_SHORT).show();
                IntentToHome();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(Detail_intro.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IntentToHome() {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }


}