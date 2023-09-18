package com.example.smartcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String,String>hashMap;

    ProgressBar progBar;
    ListView listView;

    SearchView searchView;
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progBar=findViewById(R.id.progBar);
        listView=findViewById(R.id.listView);
        //searchView=findViewById(R.id.searchView);
        bottom_navigation=findViewById(R.id.bottom_navigation);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>user ArrayList<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        String url= "https://dummyjson.com/users";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progBar.setVisibility(View.GONE);
                        Log.d("ServerRs", response.toString());

                        try {
                            JSONArray jsonArray = response.getJSONArray("users");

                            for (int x = 0; x<jsonArray.length(); x++){

                                JSONObject jsonObject = jsonArray.getJSONObject(x);
                                String id = jsonObject.getString("id");
                                String first_name = jsonObject.getString("firstName");
                                String medain_name = jsonObject.getString("maidenName");
                                String last_name = jsonObject.getString("lastName");
                                String age = jsonObject.getString("age");
                                String gender = jsonObject.getString("gender");
                                String email = jsonObject.getString("email");
                                String phone = jsonObject.getString("phone");
                                String birth_date = jsonObject.getString("birthDate");
                                String image_url = jsonObject.getString("image");
                                String blood_group = jsonObject.getString("bloodGroup");
                                String hight = jsonObject.getString("height");
                                String weight = jsonObject.getString("weight");
                                String eye_color = jsonObject.getString("eyeColor");

                                JSONObject bankObject = jsonObject.getJSONObject("bank");
                                String cardExpire = bankObject.getString("cardExpire");
                                String cardNumber = bankObject.getString("cardNumber");
                                String cardType = bankObject.getString("cardType");
                                String currency = bankObject.getString("currency");
                                String iban = bankObject.getString("iban");

                                JSONObject addressObject = jsonObject.getJSONObject("address");
                                String address = addressObject.getString("address");
                                String city = addressObject.getString("city");

                                //JSONObject companyObject = jsonObject.getJSONObject("company");   //object_into_object
                               //JSONObject addObject = companyObject.getJSONObject("address");
                                //JSONObject coordinatesObject= addObject.getJSONObject("coordinates");
                                //String comAddress = coordinatesObject.getString("lat");
                                //String comCity = coordinatesObject.getString("lng");


                                hashMap=new HashMap<>();
                                hashMap.put("id", id);
                                hashMap.put("firstName", first_name);
                                hashMap.put("maidenName", medain_name);
                                hashMap.put("lastName", last_name);
                                hashMap.put("age", age);
                                hashMap.put("gender", gender);
                                hashMap.put("email", email);
                                hashMap.put("phone", phone);
                                hashMap.put("birthDate", birth_date);
                                hashMap.put("image", image_url);
                                hashMap.put("bloodGroup", blood_group);
                                hashMap.put("height", hight);
                                hashMap.put("weight", weight);
                                hashMap.put("eyeColor", eye_color);
                                hashMap.put("cardExpire", cardExpire);
                                hashMap.put("cardNumber", cardNumber);
                                hashMap.put("cardType", cardType);
                                hashMap.put("currency", currency);
                                hashMap.put("iban", iban);
                                hashMap.put("address", address);
                                hashMap.put("city", city);
                                //hashMap.put("lat", comAddress);
                                //hashMap.put("lng", comCity);
                                arrayList.add(hashMap);


                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        MyAdapter myAdapter = new MyAdapter();
                        listView.setAdapter(myAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);


    }
    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.item, viewGroup, false);

            ShapeableImageView imageView = myView.findViewById(R.id.imageView);
            TextView name_1= myView.findViewById(R.id.name_1);
            TextView name_2= myView.findViewById(R.id.name_2);
            TextView hiden_call= myView.findViewById(R.id.hiden_call);
            LinearLayout mainLayout = myView.findViewById(R.id.mainLayout);
            ImageView call= myView.findViewById(R.id.call);

            HashMap<String, String>myhashMap = arrayList.get(position);
            String id=myhashMap.get("id");
            String first_name = myhashMap.get("firstName");
            String maiden_name=myhashMap.get("maidenName");
            String last_name = myhashMap.get("lastName");
            String age = myhashMap.get("age");
            String gender=myhashMap.get("gender");
            String email=myhashMap.get("email");
            String phone= myhashMap.get("phone");
            String birth_date= myhashMap.get("birthDate");
            String hight= myhashMap.get("height");
            String blood_group= myhashMap.get("bloodGroup");
            String weight= myhashMap.get("weight");
            String eye_color= myhashMap.get("eyeColor");
            String cardExpire = myhashMap.get("cardExpire");
            String cardNumber = myhashMap.get("cardNumber");
            String cardType = myhashMap.get("cardType");
            String currency = myhashMap.get("currency");
            String iban = myhashMap.get("iban");
            String address = myhashMap.get("address");
            String city = myhashMap.get("city");
            String img_url=myhashMap.get("image");

            //String comAddress = myhashMap.get("lat");
            //String comCity = myhashMap.get("lng");

            Picasso.get().load(img_url).placeholder(R.drawable.loading).into(imageView);

            name_1.setText(first_name);
            name_2.setText(maiden_name);
            hiden_call.setText(phone);





            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Contact_details.first_name=first_name;
                    Contact_details.maiden_name=maiden_name;
                    Contact_details.last_name=last_name;
                    Contact_details.age=age;
                    Contact_details.gender=gender;
                    Contact_details.email=email;
                    Contact_details.phone=phone;
                    Contact_details.birth_date=birth_date;
                    Contact_details.hight=hight;
                    Contact_details.blood_group=blood_group;
                    Contact_details.cardExpire=cardExpire;
                    Contact_details.weight=weight;
                    Contact_details.cardNumber=cardNumber;
                    Contact_details.cardType=cardType;
                    Contact_details.currency=currency;
                    Contact_details.iban=iban;
                    Contact_details.city=city;
                    Contact_details.address=address;

                    //Contact_details.comAddress=comAddress;
                    //Contact_details.comCity=comCity;

                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    Contact_details.MY_BITMAP = bitmap;

                    startActivity(new Intent(getApplicationContext(), Contact_details.class));

                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent myInt = new Intent(Intent.ACTION_DIAL);
                    myInt.setData(Uri.parse("tel:"+phone));
                    startActivity(myInt);

                }
            });




            return myView;
        }
    }



}