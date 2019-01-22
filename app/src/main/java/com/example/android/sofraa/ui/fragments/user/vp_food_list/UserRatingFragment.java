package com.example.android.sofraa.ui.fragments.user.vp_food_list;


import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserShopRatingAdapter;
import com.example.android.sofraa.data.model.add_review.AddReviewResponse;
import com.example.android.sofraa.data.model.restaurant_reviews.Datum;
import com.example.android.sofraa.data.model.restaurant_reviews.RestaurantReviewsResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.helper.OnEndless;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserRatingFragment extends Fragment {

    public static Integer RestuarantId;
    public static String ResName;
    @BindView(R.id.user_addRate_btn)
    Button userAddRateBtn;
    //    @BindView(R.id.user_rating_rv)
//    RecyclerView recyclerView;
    Unbinder unbinder;
    Unbinder unbinder1;

    private String api_token;
    private RecyclerView recyclerView;
    private SharedPreferences preferences;
    private UserShopRatingAdapter adapter;
    private int maxPage;
    private ImageButton ImgDialogClose;
    private AlertDialog alertDialog;
    private String name;

    public UserRatingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_rating, container, false);

        recyclerView = view.findViewById(R.id.user_rating_rv);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");
        name = preferences.getString("name", "");

        setupRecycle();
        ViewData(1);

        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    private void ViewData(final int page) {
        Retrofit retrofit = RetrofitClient.getShopInstance();
        API api = retrofit.create(API.class);
        Call<RestaurantReviewsResponse> restaurantReviewsResponse = api.getRestaurantReviewsResponse(api_token, RestuarantId, page);
        restaurantReviewsResponse.enqueue(new Callback<RestaurantReviewsResponse>() {
            @Override
            public void onResponse(Call<RestaurantReviewsResponse> call, Response<RestaurantReviewsResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        RestaurantReviewsResponse body = response.body();
                        maxPage = body.getRestaurantReviewsData().getLastPage();
                        ViewResponseRate(body);

                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantReviewsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViewResponseRate(RestaurantReviewsResponse body) {
        List<Datum> data = body.getRestaurantReviewsData().getData();
        adapter.sendToAdapter(data);
        adapter.notifyDataSetChanged();
    }

    public void setupRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserShopRatingAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        final OnEndless onEndless = new OnEndless(layoutManager, 10) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {

                    ViewData(current_page);
                }
            }
        };
        recyclerView.addOnScrollListener(onEndless);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick(R.id.user_addRate_btn)
    public void onViewClicked() {
        AddComment();
    }

    private void AddComment() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog, null);

        dialogBuilder.setView(dialogView);
        final TextView txtResCommentName = dialogView.findViewById(R.id.txtResCommentName);

        final EditText txtCliCommentName = dialogView.findViewById(R.id.txtCliCommentName);

        final EditText txtResCommentdiscription = dialogView.findViewById(R.id.txtResCommentdiscription);

        final RatingBar CommentRating = dialogView.findViewById(R.id.CommentRating);

        ImgDialogClose = dialogView.findViewById(R.id.ImgDialogClose);

        ImgDialogClose.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });
        txtResCommentName.setText(ResName);

        Button btnCommentAdd = dialogView.findViewById(R.id.btnCommentAdd);

        btnCommentAdd.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String clientName = txtCliCommentName.getText().toString().trim();

                String ResRate = String.valueOf(CommentRating.getRating());
                String Discription = txtResCommentdiscription.getText().toString().trim();
                if (clientName.isEmpty()) {

                    txtCliCommentName.setError("Name is Required");
                    txtCliCommentName.requestFocus();

                    return;
                }if (Discription.isEmpty()) {

                    txtResCommentdiscription.setError("Description is Required");
                    txtResCommentdiscription.requestFocus();

                    return;
                }if (CommentRating.getRating() == 0) {

                    Toast.makeText(getContext(), "يرجى إختيار تقييم", Toast.LENGTH_SHORT).show();

                }else {
                    Retrofit retrofit = RetrofitClient.getUserInstance();
                    API api = retrofit.create(API.class);
                    Call<AddReviewResponse> addReviewResponse = api.getAddReviewResponse(api_token, ResRate, Discription, RestuarantId);
                    addReviewResponse.enqueue(new Callback<AddReviewResponse>() {
                        @Override
                        public void onResponse(Call<AddReviewResponse> call, Response<AddReviewResponse> response) {
                            if (response.body() != null){
                                if (response.body().getStatus() == 1){
                                    Toast.makeText(getActivity(),"Successfully Adding Comment", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AddReviewResponse> call, Throwable t) {

                        }
                    });
                }
                alertDialog.dismiss();
            }
        });
        alertDialog = dialogBuilder.create();

        alertDialog.show();
    }
}