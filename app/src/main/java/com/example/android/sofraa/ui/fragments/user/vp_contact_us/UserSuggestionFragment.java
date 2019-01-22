package com.example.android.sofraa.ui.fragments.user.vp_contact_us;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.contact_us.ContactUsResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.settings_media.SettingsMediaResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSuggestionFragment extends Fragment {


//    @BindView(R.id.user_complaint_name)
//    EditText userComplaintName;
//    @BindView(R.id.user_complaint_email)
//    EditText userComplaintEmail;
//    @BindView(R.id.user_complaint_phone)
//    EditText userComplaintPhone;
//    @BindView(R.id.user_complaint_details)
//    EditText userComplaintDetails;
//    @BindView(R.id.user_complaint_send)
//    Button userComplaintSend;
    @BindView(R.id.text_follow_us_suggestion)
    TextView textFollowUsSuggestion;
    @BindView(R.id.img_face_suggestion)
    CircleImageView imgFaceSuggestion;
    @BindView(R.id.img_twitter_suggestion)
    CircleImageView imgTwitterSuggestion;
    @BindView(R.id.img_instagram_suggestion)
    CircleImageView imgInstagramSuggestion;
    Unbinder unbinder;
    private String facebook, twitter, instagram,email,name,phone,content,password,email1;
    private SharedPreferences preferences;
    private EditText userComplaintName,userComplaintEmail,userComplaintPhone,userComplaintDetails;
    private Button send_btn;

    public UserSuggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_suggestion, container, false);

        userComplaintName = view.findViewById(R.id.user_suggestion_name);
        userComplaintEmail = view.findViewById(R.id.user_suggestion_email);
        userComplaintPhone = view.findViewById(R.id.user_suggestion_phone);
        userComplaintDetails = view.findViewById(R.id.user_suggestion_details);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        email = preferences.getString("email", "");
        password = preferences.getString("password","");

        send_btn = view.findViewById(R.id.user_suggestion_send);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactUs();
            }
        });


        unbinder = ButterKnife.bind(this, view);

        SettingsMedia();

        return view;
    }

    private void ContactUs() {

        name = userComplaintName.getText().toString();
        email1 = userComplaintEmail.getText().toString();
        phone = userComplaintPhone.getText().toString();
        content = userComplaintDetails.getText().toString();

        if (TextUtils.isEmpty(name)){
            userComplaintName.setError("Name is required");
        }else if (TextUtils.isEmpty(email1)){
            userComplaintEmail.setError("Email is Required");
        }else if (TextUtils.isEmpty(phone)){
            userComplaintPhone.setError("Phone is Required");
        }else if (TextUtils.isEmpty(content)){
            userComplaintDetails.setError("Content is Required");
        }else {

            Retrofit retrofit = RetrofitClient.general();
            API api = retrofit.create(API.class);
            Call<ContactUsResponse> suggestion = api.getContactUsResponse(name, email1, phone, "suggestion", content);
            suggestion.enqueue(new Callback<ContactUsResponse>() {
                @Override
                public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                    if (response.body() != null){
                        if (response.body().getStatus() == 1){
                            Toast.makeText(getActivity(), "sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ContactUsResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void SettingsMedia() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<SettingsMediaResponse> settingsMediaResponse = api.getSettingsMediaResponse(email, password);
        settingsMediaResponse.enqueue(new Callback<SettingsMediaResponse>() {
            @Override
            public void onResponse(Call<SettingsMediaResponse> call, Response<SettingsMediaResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        SettingsMediaResponse body = response.body();

                        facebook = body.getData().getFacebook();
                        instagram = body.getData().getInstagram();
                        twitter = body.getData().getTwitter();
                    }
                }
            }

            @Override
            public void onFailure(Call<SettingsMediaResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_face_suggestion, R.id.img_twitter_suggestion, R.id.img_instagram_suggestion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.user_complaint_send:
//                ContactUs();
//                break;
            case R.id.img_face_suggestion:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(facebook));
                getActivity().startActivity(i);
                break;
            case R.id.img_twitter_suggestion:
                Intent j = new Intent(Intent.ACTION_VIEW);
                j.setData(Uri.parse(twitter));
                getActivity().startActivity(j);
                break;
            case R.id.img_instagram_suggestion:
                Intent k = new Intent(Intent.ACTION_VIEW);
                k.setData(Uri.parse(instagram));
                getActivity().startActivity(k);
                break;
        }
    }
}
