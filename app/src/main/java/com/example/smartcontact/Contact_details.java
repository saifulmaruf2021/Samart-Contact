package com.example.smartcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contact_details extends AppCompatActivity {

    ImageView tvImage, fav_icon, fav_icon2, call_icon, call_icon2, delete_icon,pen_icon, btn_back,
                share, email_icon;
    TextView tvFirst_name, tvMedien_name, tvLast_name, tvPhone, tvEmail, tvAge, tvBirthDay, tvGender, tvBlood, tvHeight, tvWeight, tvAddress,
            tvCity, tvCard,tvCardType, tvCurrency, tvExpire, tvIban, tvToast, tvToast2, tvComAddress,tvComCity;

    public static String first_name, maiden_name, last_name, age, gender, email, phone, birth_date, hight, weight, blood_group,
            cardExpire, cardNumber, cardType, currency, iban, address, city, comAddress, comCity;
    public static Bitmap MY_BITMAP = null;

    private boolean isImageVisible = false; //>>>>>>>>>>>>>>>>fav_icon<<<<<<

    LinearLayout fav_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        tvImage=findViewById(R.id.tvImage);
        fav_icon=findViewById(R.id.fav_icon);
        fav_icon2=findViewById(R.id.fav_icon2);
        fav_btn=findViewById(R.id.fav_btn);
        call_icon=findViewById(R.id.call_icon);
        call_icon2=findViewById(R.id.call_icon2);
        delete_icon=findViewById(R.id.delete_icon);
        pen_icon=findViewById(R.id.pen_icon);
        share=findViewById(R.id.share);
        btn_back=findViewById(R.id.btn_back);
        tvFirst_name=findViewById(R.id.tvFirst_name);
        tvMedien_name=findViewById(R.id.tvMedien_name);
        tvLast_name=findViewById(R.id.tvLast_name);
        tvPhone=findViewById(R.id.tvPhone);
        tvEmail=findViewById(R.id.tvEmail);
        tvAge=findViewById(R.id.tvAge);
        tvBirthDay=findViewById(R.id.tvBirthDay);
        tvGender=findViewById(R.id.tvGender);
        tvBlood=findViewById(R.id.tvBlood);
        tvHeight=findViewById(R.id.tvHeight);
        tvWeight=findViewById(R.id.tvWeight);
        tvAddress=findViewById(R.id.tvAddress);
        tvCity=findViewById(R.id.tvCity);
        tvCard=findViewById(R.id.tvCard);
        tvCardType=findViewById(R.id.tvCardType);
        tvCurrency=findViewById(R.id.tvCurrency);
        tvIban=findViewById(R.id.tvIban);
        tvExpire=findViewById(R.id.tvExpire);
        email_icon=findViewById(R.id.email_icon);

        //tvComAddress=findViewById(R.id.tvComAddress);
        //tvComCity=findViewById(R.id.tvComCity);

        if (MY_BITMAP!=null)tvImage.setImageBitmap(MY_BITMAP);

        tvFirst_name.setText(first_name);
        tvMedien_name.setText(maiden_name);
        tvLast_name.setText(last_name);
        tvAge.setText(age);
        tvGender.setText(gender);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvBirthDay.setText(birth_date);
        tvHeight.setText(hight);
        tvWeight.setText(weight);
        tvBlood.setText(blood_group);
        tvCard.setText(cardNumber);
        tvCardType.setText(cardType);
        tvCurrency.setText(currency);
        tvExpire.setText(cardExpire);
        tvIban.setText(iban);
        tvCity.setText(city);
        tvAddress.setText(address);

        //tvComAddress.setText(comAddress);
        //tvComCity.setText(comCity);
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>fav_Icon<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isImageVisible = !isImageVisible;
                if (isImageVisible) {
                    fav_icon.setVisibility(View.GONE);
                    fav_icon2.setVisibility(View.VISIBLE);

                    LayoutInflater inflater= getLayoutInflater();
                    View layout = inflater.inflate(R.layout.add_wishlist,
                            (ViewGroup) findViewById(R.id.customToast));

                    tvToast=layout.findViewById(R.id.tvToast);
                    tvToast.setText("added to favorite!");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 100);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();


                }else{
                    fav_icon2.setVisibility(View.GONE);
                    fav_icon.setVisibility(View.VISIBLE);

                    LayoutInflater inflater= getLayoutInflater();
                    View layout = inflater.inflate(R.layout.remove_wishlist,
                            (ViewGroup) findViewById(R.id.customToast2));

                    tvToast2=layout.findViewById(R.id.tvToast2);
                    tvToast2.setText("removed from favorite!");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 100);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                }


            }
        });

        String call_now = tvPhone.getText().toString();
        tvPhone.setText(call_now);

        call_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myInt = new Intent(Intent.ACTION_DIAL);
                myInt.setData(Uri.parse("tel:"+call_now));
                startActivity(myInt);

            }
        });

        call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myInt = new Intent(Intent.ACTION_DIAL);
                myInt.setData(Uri.parse("tel:"+call_now));
                startActivity(myInt);

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareApp(Contact_details.this);
            }
        });

        email_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                composeEmail();
            }
        });


    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Share App<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void shareApp (Context context){

        final String appPackagName = context.getPackageName();
        Intent myInt= new Intent();
        myInt.setAction(Intent.ACTION_SEND);
        myInt.putExtra(Intent.EXTRA_TEXT, "Download Now : https://play.google.com/store/apps/details?id=" + appPackagName);
        myInt.setType("text/plain");
        context.startActivity(myInt);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Email Send<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    public void composeEmail() {
        String[] recipients = {"recipient@example.com", "a@a.com"};
        String[] CC = {"recipient@example.com", "a@a.com"};
        String[] BCC = {"recipient@example.com", "a@a.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, CC);
        intent.putExtra(Intent.EXTRA_TEXT, BCC);

        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT,"Hello, this is the email body.");

        startActivity(Intent.createChooser(intent, "Choose one App"));

    }



}