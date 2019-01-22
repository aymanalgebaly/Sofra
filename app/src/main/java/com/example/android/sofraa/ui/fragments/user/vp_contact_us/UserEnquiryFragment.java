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
public class UserEnquiryFragment extends Fragment {


    @BindView(R.id.user_enquiry_name)
    EditText userEnquiryName;
    @BindView(R.id.user_enquiry_email)
    EditText userEnquiryEmail;
    @BindView(R.id.user_enquiry_phone)
    EditText userEnquiryPhone;
    @BindView(R.id.user_enquiry_details)
    EditText userEnquiryDetails;
    @BindView(R.id.user_enquiry_send)
    Button userEnquirySend;
    @BindView(R.id.text_follow_us_enquiry)
    TextView textFollowUsEnquiry;
    @BindView(R.id.img_face_enquiry)
    CircleImageView imgFaceEnquiry;
    @BindView(R.id.img_twitter_enquiry)
    CircleImageView imgTwitterEnquiry;
    @BindView(R.id.img_instagram_enquiry)
    CircleImageView imgInstagramEnquiry;
    Unbinder unbinder;
    private SharedPreferences preferences;
    private String email, password, facebook, twitter, instagram,name,email1,phone,content;

    public UserEnquiryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_enquiry, container, false);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        email = preferences.getString("email", "");
        password = preferences.getString("password", "");



        unbinder = ButterKnife.bind(this, view);

        SettingsMedia();
        ContactUs();

        return view;
    }

    private void ContactUs() {

        name = userEnquiryName.getText().toString();
        email1 = userEnquiryEmail.getText().toString();
        phone = userEnquiryPhone.getText().toString();
        content = userEnquiryDetails.getText().toString();

        if (TextUtils.isEmpty(name)) {
            userEnquiryName.setError("Name is required");
        } else if (TextUtils.isEmpty(email1)) {
            userEnquiryEmail.setError("Email is Required");
        } else if (TextUtils.isEmpty(phone)) {
            userEnquiryPhone.setError("Phone is Required");
        } else if (TextUtils.isEmpty(content)) {
            userEnquiryDetails.setError("Content is Required");
        } else {

            Retrofit retrofit = RetrofitClient.general();
            API api = retrofit.create(API.class);
            Call<ContactUsResponse> suggestion = api.getContactUsResponse(name, email1, phone, "inquiry", content);
            suggestion.enqueue(new Callback<ContactUsResponse>() {
                @Override
                public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_enquiry_send, R.id.img_face_enquiry, R.id.img_twitter_enquiry, R.id.img_instagram_enquiry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_enquiry_send:
                ContactUs();
                break;
            case R.id.img_face_enquiry:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(facebook));
                getActivity().startActivity(i);
                break;
            case R.id.img_twitter_enquiry:
                Intent j = new Intent(Intent.ACTION_VIEW);
                j.setData(Uri.parse(twitter));
                getActivity().startActivity(j);
                break;
            case R.id.img_instagram_enquiry:
                Intent k = new Intent(Intent.ACTION_VIEW);
                k.setData(Uri.parse(instagram));
                getActivity().startActivity(k);
                break;
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
}
